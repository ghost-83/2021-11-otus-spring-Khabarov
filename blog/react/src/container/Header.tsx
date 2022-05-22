import {FC, SyntheticEvent} from "react"
import {AppBar, LinearProgress, Snackbar, Toolbar, Typography} from "@mui/material"
import {GhostIcon} from "../icon/GhostIcon"
import {useDispatch} from "react-redux"
import {useAppSelector} from "../hook/redux";
import {messageStop} from "../hook/creator/message.creator";
import MuiAlert from '@mui/material/Alert';

export const Header: FC = () => {

    const {isLoading} = useAppSelector(state => state.loadReducer)
    const {message, type, status} = useAppSelector(state => state.messageReducer)
    const dispatch = useDispatch()

    const handleClose = (event: SyntheticEvent | Event, reason?: string) => {
        if (reason === 'clickaway') {
            return;
        }
        dispatch(messageStop())
    }

    return (
        <AppBar position="fixed" sx={{zIndex: (theme) => theme.zIndex.drawer + 1}}>
            <Toolbar>
                <Typography variant="h6" noWrap component="div" sx={{flexGrow: 1}}>
                    <GhostIcon fontSize="large"/>
                    GHOST
                </Typography>
            </Toolbar>
            {isLoading && <LinearProgress color="inherit"/>}
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
        </AppBar>
    )
}