import React from 'react';
import { createRoot } from 'react-dom/client';
import store from "./redux/store";
import { Provider } from "react-redux";
import App from "./App";
import 'materialize-css/dist/js/materialize.min.js'
import 'materialize-css/dist/css/materialize.min.css'
import './index.css'


const container = document.getElementById('container');
const root = createRoot(container);

root.render(
    <Provider store={store}>
        <App />
    </Provider>
);