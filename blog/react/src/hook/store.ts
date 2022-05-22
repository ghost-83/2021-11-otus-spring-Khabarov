import {combineReducers, configureStore} from "@reduxjs/toolkit"
import authReducer from "./slice/auth.slice"
import loadReducer from "./slice/load.slice";
import messageReducer from "./slice/message.slice";
import referenceBookReducer from "./slice/reference.book.slice";
import postReducer from "./slice/post.slice";
import categoryReducer from "./slice/category.slice";
import modalReducer from "./slice/modal.slice";

const rooReducer = combineReducers({
    authReducer,
    loadReducer,
    messageReducer,
    postReducer,
    referenceBookReducer,
    categoryReducer,
    modalReducer,
})

export const setupStore = () => {
    return configureStore({
        reducer: rooReducer
    })
}

export type RootState = ReturnType<typeof rooReducer>
export type AppStore = ReturnType<typeof setupStore>
export type AppDispatch = AppStore['dispatch']