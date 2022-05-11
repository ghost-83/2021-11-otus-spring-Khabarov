import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {IReferenceBook} from "../../model/IReferenceBook";

const initialState = {
    books: [] as IReferenceBook[]
}

export const referenceBookSlice = createSlice({
    name: 'book',
    initialState,
    reducers: {
        referenceBookFetchingSuccess(state, action: PayloadAction<IReferenceBook[]>) {
            state.books = action.payload
        }
    }
})

export default referenceBookSlice.reducer