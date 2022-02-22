import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import { IBook } from "../../model/IBook"
import { AppDispatch } from "../store"

interface ModalState {
    book: IBook | null
    show: boolean
}

const initialState: ModalState = {
    book: null,
    show: false
}

export const modalSlice = createSlice({
    name: 'modal',
    initialState,
    reducers: {
        modalOpen(state, action: PayloadAction<IBook | null>) {
            state.show = true
            state.book = action.payload
        },
        modalClose(state) {
            state.show = false
            state.book = null
        },
    }
})

export const openModal = (data: IBook | null) => async (dispatch: AppDispatch) => {
    dispatch(modalSlice.actions.modalOpen(data))
}

export const closeModal = () => async (dispatch: AppDispatch) => {
    dispatch(modalSlice.actions.modalClose())
}

export default modalSlice.reducer
