import {FC, useEffect} from "react"
import {Box, CssBaseline, Toolbar} from '@mui/material'
import {Header} from "./container/Header"
import {SidePanel} from "./container/SidePanel"
import {Route, Routes} from "react-router-dom"
import {Auth} from "./container/Auth"
import {useDispatch} from "react-redux"
import {AuthUser} from "./model/IUser"
import {useAppSelector} from "./hook/redux"
import {Book} from "./container/Book";
import {authSlice} from "./hook/slice/auth.slice";
import {Settings} from "./container/Settings";
import {Home} from "./container/Home";
import {NotFound} from "./container/NotFound";

export const AppRoute: FC = () => {

    const {userAuth} = useAppSelector(state => state.authReducer)
    const dispatch = useDispatch()

    useEffect(() => {
        if (userAuth !== undefined && userAuth === null) {
            if (!!localStorage.getItem("authData")) {
                const authData: AuthUser = JSON.parse(localStorage.getItem("authData")!)
                if (authData)
                    dispatch(authSlice.actions.authFetchingSuccess(authData))
            }
        }
    }, [dispatch, userAuth])

    if (userAuth !== undefined && userAuth !== null) {
        return (
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <Header/>
                <SidePanel/>
                <Box component="main" sx={{flexGrow: 1, p: 3}}>
                    <Toolbar/>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/book" element={<Book/>}/>
                        <Route path="/settings" element={<Settings/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Routes>
                </Box>
            </Box>
        )
    } else {
        return (
            <Auth/>
        )
    }
}