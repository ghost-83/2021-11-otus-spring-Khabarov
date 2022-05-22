import {FC, useEffect} from "react"
import {Box, CssBaseline, Toolbar} from '@mui/material'
import {Header} from "./container/Header"
import {SidePanel} from "./container/SidePanel"
import {Route, Routes} from "react-router-dom"
import {Auth} from "./container/Auth"
import {useDispatch} from "react-redux"
import {AuthUser} from "./model/IUser"
import {useAppSelector} from "./hook/redux"
import {ReferenceBook} from "./container/ReferenceBook";
import {authSlice} from "./hook/slice/auth.slice";
import {Home} from "./container/Home";
import {NotFound} from "./container/NotFound";
import {Posts} from "./container/Posts";
import {Registration} from "./container/Registration";

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
                        <Route path="/post" element={<Posts/>}/>
                        <Route path="/reference-book" element={<ReferenceBook/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Routes>
                </Box>
            </Box>
        )
    } else {
        return (
            <Routes>
                <Route path="/" element={<Auth/>}/>
                <Route path="/registration" element={<Registration/>}/>
                <Route path="*" element={<Auth/>}/>
            </Routes>
        )
    }
}