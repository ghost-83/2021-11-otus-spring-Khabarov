import {AppDispatch} from "../store";
import {isLoadingSlice} from "../slice/load.slice";
import {AxiosError} from "axios";
import {messageError} from "./message.creator";
import {postApi} from "../../api/post.api";
import {postSlice} from "../slice/post.slice";

export const fetchPosts = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const responsePost = await postApi.findAll()
        dispatch(postSlice.actions.postsFetchingSuccess(responsePost))
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
        dispatch(postSlice.actions.postsFetchingSuccess(response))
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
        dispatch(postSlice.actions.postsFetchingSuccess(response))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}