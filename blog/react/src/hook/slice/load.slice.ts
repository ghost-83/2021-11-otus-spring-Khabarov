import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    isLoading: false as boolean
}

export const isLoadingSlice = createSlice({
    name: 'isLoading',
    initialState,
    reducers: {
        isLoadingStart(state) {
            state.isLoading = true
        },
        isLoadingEnd(state) {
            state.isLoading = false
        }
    }
})

export default isLoadingSlice.reducer