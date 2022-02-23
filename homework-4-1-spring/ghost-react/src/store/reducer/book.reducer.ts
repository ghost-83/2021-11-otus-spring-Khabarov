import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import axios from "axios"
import { IBook } from "../../model/IBook"
import { AppDispatch } from "../store"

interface BookState {
    books: IBook[]
    isLoading: boolean
    error: string
}

const initialState: BookState = {
    books: [],
    isLoading: false,
    error: ''
}

export const bookSlice = createSlice({
    name: 'book',
    initialState,
    reducers: {
        bookFetching(state) {
            state.isLoading = true
        },
        bookFetchingSuccess(state, action: PayloadAction<IBook[]>) {
            state.isLoading = false
            state.books = action.payload
        },
        bookFetchingError(state, action: PayloadAction<string>) {
            state.isLoading = false
            state.error = action.payload
        }
    }
})

export const fetchBook = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(bookSlice.actions.bookFetching())
        const response = await axios.get<IBook[]>('/api/v1/book')
        dispatch(bookSlice.actions.bookFetchingSuccess(response.data))
    } catch (e) {
        dispatch(bookSlice.actions.bookFetchingError("Error!!!"))
    }
}

export const saveBook = (data: IBook) => async (dispatch: AppDispatch) => {
    try {
        await axios.post('/api/v1/book', { ...data })
        dispatch(bookSlice.actions.bookFetching())
        const response = await axios.get<IBook[]>('/api/v1/book')
        dispatch(bookSlice.actions.bookFetchingSuccess(response.data))
    } catch (e) {
        dispatch(bookSlice.actions.bookFetchingError("Error!!!"))
    }
}

export const deleteBook = (id: string) => async (dispatch: AppDispatch) => {
    try {
        await axios.delete(`/api/v1/book/${id}`)
        dispatch(bookSlice.actions.bookFetching())
        const response = await axios.get<IBook[]>('/api/v1/book')
        dispatch(bookSlice.actions.bookFetchingSuccess(response.data))
    } catch (e) {
        dispatch(bookSlice.actions.bookFetchingError("Error!!!"))
    }
}

export default bookSlice.reducer