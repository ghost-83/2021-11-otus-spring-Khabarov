import {AppDispatch} from "../store";
import {isLoadingSlice} from "../slice/load.slice";
import {AxiosError} from "axios";
import {postApi} from "../../api/book.api";
import {categoryApi} from "../../api/category.api";
import {categorySlice} from "../slice/category.slice";
import {bookSlice} from "../slice/book.slice";
import {messageError} from "./message.creator";

export const fetchPosts = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const responsePost = await postApi.findAll()
        dispatch(bookSlice.actions.postsFetchingSuccess(responsePost))
        const response = await categoryApi.findAll()
        dispatch(categorySlice.actions.genresFetchingSuccess(response))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}

export const fetchPostCategory = (category: string) => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const response = await postApi.findAllByCategory(category)
        dispatch(bookSlice.actions.postsFetchingSuccess(response))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}

export const searchPost = (search: string) => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const response = await postApi.search(search)
        dispatch(bookSlice.actions.postsFetchingSuccess(response))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}