import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {AlertColor} from "@mui/material";

const initialState = {
    message: '' as string,
    type: 'info' as AlertColor,
    status: false as boolean
}

export const messageSlice = createSlice({
    name: 'message',
    initialState,
    reducers: {
        messageError(state, action: PayloadAction<string>) {
            state.message = action.payload
            state.type = 'error'
            state.status = true
        },
        messageOk(state, action: PayloadAction<string>) {
            state.message = action.payload
            state.type = 'success'
            state.status = true
        },
        messageStop(state) {
            state.status = false
        }
    }
})

export default messageSlice.reducer