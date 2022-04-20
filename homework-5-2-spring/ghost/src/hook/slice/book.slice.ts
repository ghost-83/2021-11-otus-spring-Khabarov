import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {IBook} from "../../model/IBook";

const initialState = {
    books: [] as IBook[]
}

export const bookSlice = createSlice({
    name: 'book',
    initialState,
    reducers: {
        postsFetchingSuccess(state, action: PayloadAction<IBook[]>) {
            state.books = action.payload
        }
    }
})

export default bookSlice.reducer