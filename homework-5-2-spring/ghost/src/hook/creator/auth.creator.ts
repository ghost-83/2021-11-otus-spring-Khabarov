import {AuthData} from "../../model/IUser";
import {AppDispatch} from "../store";
import {authApi} from "../../api/auth.api";
import {AxiosError} from "axios";
import {authSlice} from "../slice/auth.slice";
import {messageError} from "./message.creator";

export const authUser = (data: AuthData) => async (dispatch: AppDispatch) => {
    try {
        dispatch(authSlice.actions.authStart())
        const response = await authApi.getToken(data)
        localStorage.setItem("authData", JSON.stringify(response))
        dispatch(authSlice.actions.authFetchingSuccess(response))
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(authSlice.actions.authError())
    }
}

export const deleteToken = () => async (dispatch: AppDispatch) => {
    try {
        await authApi.deleteToken()
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
    }
}

export const exitAuth = () => async (dispatch: AppDispatch) => {
    localStorage.removeItem("authData")
    dispatch(authSlice.actions.authFetchingSuccess(null))
}