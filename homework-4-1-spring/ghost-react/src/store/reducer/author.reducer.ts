import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import axios from "axios"
import { IAuthor } from "../../model/IAuthor"
import { AppDispatch } from "../store"

interface AuthorState {
    authors: IAuthor[]
    isLoading: boolean
    error: string
}

const initialState: AuthorState = {
    authors: [],
    isLoading: false,
    error: ''
}

export const authorSlice = createSlice({
    name: 'author',
    initialState,
    reducers: {
        authorFetching(state) {
            state.isLoading = true
        },
        authorFetchingSuccess(state, action: PayloadAction<IAuthor[]>) {
            state.isLoading = false
            state.authors = action.payload
        },
        authorFetchingError(state, action: PayloadAction<string>) {
            state.isLoading = false
            state.error = action.payload
        }

    }
})

export const fetchAuthor = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(authorSlice.actions.authorFetching)
        const response = await axios.get<IAuthor[]>('/api/v1/author')
        dispatch(authorSlice.actions.authorFetchingSuccess(response.data))
    } catch (e) {
        dispatch(authorSlice.actions.authorFetchingError("Error!!!"))
    }
}

export default authorSlice.reducer