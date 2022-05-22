import {createSlice, PayloadAction} from "@reduxjs/toolkit";

const initialState = {
    statusModal: false as boolean,
    typeModal: 0 as ModalType
}

export enum ModalType {
    CREATE,
    UPDATE,
    READ
}

export const modalSlice = createSlice({
    name: 'isModal',
    initialState,
    reducers: {
        openModalWindow(state, action: PayloadAction<ModalType>) {
            state.statusModal = true
            state.typeModal = action.payload
        },
        closeModalWindow(state) {
            state.statusModal = false
        }
    }
})

export default modalSlice.reducer