import {FC} from "react"
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
    TextField,
    Typography
} from "@mui/material"
import BG1 from '../img/1.jpeg'
import * as yup from "yup"
import {useForm} from "react-hook-form"
import {useDispatch} from "react-redux"
import {yupResolver} from '@hookform/resolvers/yup';
import {AuthData} from "../model/IUser"
import {authUser} from "../hook/creator/auth.creator";
import {useAppSelector} from "../hook/redux";

const schema = yup.object().shape({
    username: yup.string().required("Заполните поле логина!!!"),
    password: yup.string().required("Заполните поле пароля!!!")
})

export const Auth: FC = () => {

    const {register, handleSubmit} = useForm<AuthData>({
        mode: "all",
        resolver: yupResolver(schema)
    })
    const {isLoading} = useAppSelector(state => state.authReducer)
    const dispatch = useDispatch()

    const onSubmit = handleSubmit((authData: AuthData) => {
        dispatch(authUser(authData))
    })

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
                                Sign in
                            </Typography>
                        </Box>
                        <TextField type="search" margin="normal" required fullWidth id="username" label="Login"
                                   autoComplete="username" autoFocus {...register('username')} />
                        <TextField margin="normal" required fullWidth label="Password" type="password" id="password"
                                   autoComplete="current-password" {...register('password')} />
                        <Button type="submit" fullWidth variant="contained" sx={{mt: 3, mb: 2}}>
                            Sign In
                        </Button>
                    </Card>
                </Container>
            </Box>
        </>
    )
}