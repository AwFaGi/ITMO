import {createSlice} from "@reduxjs/toolkit";

export const pointSlice = createSlice({
    name: 'point',
    initialState: {
        pointCount: 0,
        currentPage: 0,
        totalPages: 0,
        points: []
    },
    reducers: {
        setPointCount: (state, action) => {
            state.pointCount = action.payload;
        },
        addPoint: (state, action) => {
            state.points.push(action.payload);
        },
        setCurrentPage: (state, action) => {
            state.currentPage = action.payload;
        },
        setTotalPages: (state, action) => {
            state.totalPages = action.payload;
        },
        clearAll: (state) => {
            state.pointCount = 0;
            state.currentPage = 0;
            state.totalPages = 0;
            state.points = [];
        },
    }
})

export const {setPointCount, addPoint, setCurrentPage, setTotalPages, clearAll} = pointSlice.actions;

const pointReducer = pointSlice.reducer;

export default pointReducer;