import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import axios from "axios"
import { IComment } from "../../model/IComment"
import { AppDispatch } from "../store"

interface CommentState {
    comments: IComment[]
    isLoading: boolean
    error: string
}

const initialState: CommentState = {
    comments: [],
    isLoading: false,
    error: ''
}

export const commentSlice = createSlice({
    name: 'comment',
    initialState,
    reducers: {
        commentFetching(state) {
            state.isLoading = true
        },
        commentFetchingSuccess(state, action: PayloadAction<IComment[]>) {
            state.isLoading = false
            state.comments = action.payload
        },
        commentFetchingError(state, action: PayloadAction<string>) {
            state.isLoading = false
            state.error = action.payload
        }
    }
})

export const fetchComment = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(commentSlice.actions.commentFetching())
        const response = await axios.get<IComment[]>('/api/v1/book/{bookId}/comments')
        dispatch(commentSlice.actions.commentFetchingSuccess(response.data))
    } catch (e) {
        dispatch(commentSlice.actions.commentFetchingError("Error!!!"))
    }
}

export default commentSlice.reducer