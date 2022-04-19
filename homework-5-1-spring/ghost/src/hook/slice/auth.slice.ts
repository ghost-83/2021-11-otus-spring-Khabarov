import {createSlice, PayloadAction} from "@reduxjs/toolkit"
import {AuthUser} from "../../model/IUser"

const initialState = {

    userAuth: null as AuthUser | null,
    isLoading: false as boolean
}

export const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        authStart(state) {
            state.isLoading = true
        },
        authFetchingSuccess(state, action: PayloadAction<AuthUser | null>) {
            state.userAuth = action.payload
            state.isLoading = false
        },
        authError(state) {
            state.isLoading = false
        }
    }
})

export default authSlice.reducer