import { combineReducers, configureStore } from "@reduxjs/toolkit"
import authorReducer from "./reducer/author.reducer"
import bookReducer from "./reducer/book.reducer"
import commentReducer from "./reducer/comment.reducer"
import genreReducer from "./reducer/genre.reducer"
import modalReducer from "./reducer/modal.reducer"

const rooReducer = combineReducers({
    authorReducer,
    commentReducer,
    genreReducer,
    bookReducer,
    modalReducer
})

export const setupStore = () => {
    return configureStore({
        reducer: rooReducer
    })
}

export type RootState = ReturnType<typeof rooReducer>
export type AppStore = ReturnType<typeof setupStore>
export type AppDispatch = AppStore['dispatch']