import {AppDispatch} from "../store";
import {isLoadingSlice} from "../slice/load.slice";
import {categoryApi} from "../../api/category.api";
import {categorySlice} from "../slice/category.slice";
import {messageError} from "./message.creator";
import {AxiosError} from "axios";

export const fetchCategory = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const response = await categoryApi.findAll()
        dispatch(categorySlice.actions.genresFetchingSuccess(response))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}