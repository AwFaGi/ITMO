import {createSlice} from "@reduxjs/toolkit";

export const formStateSlice = createSlice({
    name: 'formState',
    initialState: {
        x: -3,
        y: -3,
        r: 1
    },
    reducers: {
        setX: (state, action) => {
            state.x = action.payload;
        },
        setY: (state, action) => {
            state.y = action.payload;
        },
        setR: (state, action) => {
            state.r = action.payload;
        }

    }
})

export const {setX, setY, setR} = formStateSlice.actions;

const formStateReducer = formStateSlice.reducer;

export default formStateReducer;