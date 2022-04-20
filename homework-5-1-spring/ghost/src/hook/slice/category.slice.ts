import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {ICategory} from "../../model/ICategory";

const initialState = {
    categories: [] as ICategory[]
}

export const categorySlice = createSlice({
    name: 'category',
    initialState,
    reducers: {
        genresFetchingSuccess(state, action: PayloadAction<ICategory[]>) {
            state.categories = action.payload
        }
    }
})

export default categorySlice.reducer