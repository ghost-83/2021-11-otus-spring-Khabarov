import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {IPost} from "../../model/IPost";

const initialState = {
    posts: [] as IPost[]
}

export const postSlice = createSlice({
    name: 'post',
    initialState,
    reducers: {
        postsFetchingSuccess(state, action: PayloadAction<IPost[]>) {
            state.posts = action.payload
        }
    }
})

export default postSlice.reducer