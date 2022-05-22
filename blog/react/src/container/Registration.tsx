import {FC, SyntheticEvent} from "react"
import LockOutlinedIcon from '@mui/icons-material/LockOutlined'
import {
    Avatar,
    Backdrop,
    Box,
    Button,
    Card,
    CircularProgress,
    Container,
    CssBaseline,
    Snackbar,
    TextField,
    Typography
} from "@mui/material"
import BG1 from '../img/1.jpeg'
import * as yup from "yup"
import {useForm} from "react-hook-form"
import {useDispatch} from "react-redux"
import {yupResolver} from '@hookform/resolvers/yup';
import {registrationUser} from "../hook/creator/auth.creator";
import {useAppSelector} from "../hook/redux";
import {IRegistration} from "../model/IRegistration";
import MuiAlert from "@mui/material/Alert";
import {messageStop} from "../hook/creator/message.creator";
import {Link} from "react-router-dom";

const schema = yup.object().shape({
    username: yup.string().required("Заполните поле логина!!!"),
    password: yup.string().required("Заполните поле пароля!!!"),
    fullName: yup.string().required("Заполните поле пароля!!!"),
    email: yup.string().required("Заполните поле пароля!!!")
})

export const Registration: FC = () => {

    const {register, handleSubmit} = useForm<IRegistration>({
        mode: "all",
        resolver: yupResolver(schema)
    })
    const {isLoading} = useAppSelector(state => state.authReducer)
    const {message, type, status} = useAppSelector(state => state.messageReducer)
    const dispatch = useDispatch()

    const onSubmit = handleSubmit((registrationData: IRegistration) => {
        dispatch(registrationUser(registrationData))
    })

    const handleClose = (event: SyntheticEvent | Event, reason?: string) => {
        if (reason === 'clickaway') {
            return;
        }
        dispatch(messageStop())
    }

    return (
        <>
            <Backdrop
                sx={{color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1}}
                open={isLoading}
            >
                <CircularProgress color="inherit"/>
            </Backdrop>

            <Box component="main" maxWidth="xs" sx={{
                display: 'flex',
                backgroundImage: `url(${BG1})`,
                backgroundPosition: 'center',
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat',
                width: '100%',
                height: '100vh'
            }}>
                <CssBaseline/>
                <Container sx={{marginTop: '20vh', display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                    <Card component="form" onSubmit={onSubmit} noValidate
                          sx={{mt: 5, p: 3, backgroundColor: 'white', maxWidth: '45vh'}}>
                        <Box display="flex" justifyContent="center">
                            <Avatar sx={{m: 1, bgcolor: 'secondary.main'}}>
                                <LockOutlinedIcon/>
                            </Avatar>
                        </Box>
                        <Box display="flex" justifyContent="center">
                            <Typography component="h1" variant="h5">
                                Registration
                            </Typography>
                        </Box>
                        <TextField type="search" margin="normal" required fullWidth id="username" label="Login"
                                   autoComplete="username" autoFocus {...register('username')} />
                        <TextField margin="normal" required fullWidth label="Password" type="password" id="password"
                                   autoComplete="current-password" {...register('password')} />
                        <TextField type="search" margin="normal" required fullWidth id="fullName" label="FullName"
                                   autoComplete="fullName" autoFocus {...register('fullName')} />
                        <TextField type="search" margin="normal" required fullWidth id="email" label="Email"
                                   autoComplete="email" autoFocus {...register('email')} />
                        <Button type="submit" fullWidth variant="contained" sx={{mt: 3, mb: 2}}>
                            Registration
                        </Button>
                        <Box sx={{textAlign: 'center'}}>
                            <Link to="/">
                                Sign in!
                            </Link>
                        </Box>
                    </Card>
                </Container>
            </Box>
            <Snackbar
                open={status}
                autoHideDuration={4000}
                onClose={handleClose}
                anchorOrigin={{vertical: 'bottom', horizontal: 'right'}}
            >
                <MuiAlert elevation={6} onClose={handleClose} variant="filled" severity={type} sx={{width: '100%'}}>
                    {message}
                </MuiAlert>
            </Snackbar>
        </>
    )
}