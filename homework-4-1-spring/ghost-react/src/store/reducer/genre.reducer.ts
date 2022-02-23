import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import axios from "axios"
import { IGenre } from "../../model/IGenre"
import { AppDispatch } from "../store"

interface GenreState {
    genres: IGenre[]
    isLoading: boolean
    error: string
}

const initialState: GenreState = {
    genres: [],
    isLoading: false,
    error: ''
}

export const genreSlice = createSlice({
    name: 'genre',
    initialState,
    reducers: {
        genreFetching(state) {
            state.isLoading = true
        },
        genreFetchingSuccess(state, action: PayloadAction<IGenre[]>) {
            state.isLoading = false
            state.genres = action.payload
        },
        genreFetchingError(state, action: PayloadAction<string>) {
            state.isLoading = false
            state.error = action.payload
        }
    }
})

export const fetchGenre = () => async (dispatch: AppDispatch) => {
    try {
        dispatch(genreSlice.actions.genreFetching())
        const response = await axios.get<IGenre[]>('/api/v1/genre')
        dispatch(genreSlice.actions.genreFetchingSuccess(response.data))
    } catch (e) {
        dispatch(genreSlice.actions.genreFetchingError("Error!!!"))
    }
}

export default genreSlice.reducer