import { configureStore } from '@reduxjs/toolkit'
import userReducer from './userSlice'
import pointReducer from "./pointSlice";
import formStateReducer from "./formStateSlice";

export default configureStore({
    reducer: {
        user: userReducer,
        point: pointReducer,
        formState: formStateReducer
    },
})