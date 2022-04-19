import {AppDispatch} from "../store";
import {messageSlice} from "../slice/message.slice";
import {exitAuth} from "./auth.creator";
import {IMessage} from "../../model/IMessage";

export const messageError = (data: IMessage) => async (dispatch: AppDispatch) => {
    console.log(data)
    if (data.code === 401 || data.code === 302 || data.code === 0)
        dispatch(exitAuth())
    else
        dispatch(messageSlice.actions.messageError(data.message))
}

export const messageOk = (message: string) => async (dispatch: AppDispatch) => {
    dispatch(messageSlice.actions.messageOk(message))
}

export const messageStop = () => async (dispatch: AppDispatch) => {
    dispatch(messageSlice.actions.messageStop())
}