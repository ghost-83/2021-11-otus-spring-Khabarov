import {FC, SyntheticEvent} from "react"
import {AppBar, Box, IconButton, LinearProgress, Snackbar, Toolbar, Tooltip, Typography} from "@mui/material"
import {GhostIcon} from "../icon/GhostIcon"
import Logout from '@mui/icons-material/Logout'
import {useDispatch} from "react-redux"
import {useAppSelector} from "../hook/redux";
import {deleteToken, exitAuth} from "../hook/creator/auth.creator";
import {messageStop} from "../hook/creator/message.creator";
import MuiAlert from '@mui/material/Alert';

export const Header: FC = () => {

    const {isLoading} = useAppSelector(state => state.loadReducer)
    const {message, type, status} = useAppSelector(state => state.messageReducer)
    const dispatch = useDispatch()

    const changeHandler = () => {
        dispatch(exitAuth())
        dispatch(deleteToken())
    }

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
                <Box>
                    <Tooltip title="Logout">
                        <IconButton color="inherit" sx={{p: 0}} onClick={changeHandler}>
                            <Logout/>
                        </IconButton>
                    </Tooltip>
                </Box>
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