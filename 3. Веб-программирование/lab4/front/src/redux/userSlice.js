import {createSlice} from "@reduxjs/toolkit";

export const userSlice = createSlice({
    name: 'user',
    initialState: {
        username: localStorage.getItem("username") == null ? '': localStorage.getItem("username"),
        jwtToken: localStorage.getItem("token") == null ? '': localStorage.getItem("token"),
        isLogged: localStorage.getItem("username") != null,
    },
    reducers: {
        setIsLogged: (state, action) => {
            state.isLogged = action.payload;
        },
        setUsername: (state, action) => {
            state.username = action.payload;
        },
        setJwtToken: (state, action) => {
            state.jwtToken = action.payload;
        },
    }
})

export const {setIsLogged, setUsername, setJwtToken} = userSlice.actions;

const userReducer = userSlice.reducer

export default userReducer;