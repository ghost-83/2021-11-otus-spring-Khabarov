import {AppDispatch} from "../store";
import {isLoadingSlice} from "../slice/load.slice";
import {AxiosError} from "axios";
import {bookApi} from "../../api/book.api";
import {referenceBookSlice} from "../slice/reference.book.slice";
import {messageError} from "./message.creator";

export const fetchReferenceBooks = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const responsePost = await bookApi.findAll()
        dispatch(referenceBookSlice.actions.referenceBookFetchingSuccess(responsePost))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}

export const fetchReferenceBookCategory = (category: string) => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const response = await bookApi.findAllByCategory(category)
        dispatch(referenceBookSlice.actions.referenceBookFetchingSuccess(response))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}

export const searchReferenceBook = (search: string) => async (dispatch: AppDispatch) => {
    try {
        dispatch(isLoadingSlice.actions.isLoadingStart())
        const response = await bookApi.search(search)
        dispatch(referenceBookSlice.actions.referenceBookFetchingSuccess(response))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}

export const deleteReferenceBook = (id: number) => async (dispatch: AppDispatch) => {
    try {
        await bookApi.delete(id)
        dispatch(fetchReferenceBooks())
    } catch (error) {
        dispatch(messageError({code: (error as AxiosError).request.status, message: (error as AxiosError).message}))
        dispatch(isLoadingSlice.actions.isLoadingEnd())
    }
}