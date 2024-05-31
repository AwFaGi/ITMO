import React from "react";
import {connect, useDispatch} from "react-redux";
import {Navigate} from "react-router";
import PointService from "../../service/PointService";
import Table from "../table/Table";
import CanvasComponent from "../common/CanvasElement";
import PointForm from "../forms/PointForm";
import pointUpdater from "../../service/PointUpdater";

function PreMainPage(props) {

    return (
        <div className={"row"}>
            { !props.isLogged && <Navigate to={"/"}/>}
            <div className={"col s12 push-s0 xl8 push-xl2"}>
                <div className={"row page-frame center-align"}>
                    <div className={"col s12"}>
                        <div className={"row"}>
                            <CanvasComponent />
                        </div>
                        <div className={"row"}>
                            <PointForm />
                        </div>
                    </div>
                </div>
                <div className={"row page-frame"}>
                    <Table />
                </div>
            </div>
        </div>
    );
}

const mapStateToProps = function(state) {
    return {
        username: state.user.username,
        isLogged: state.user.isLogged,
        jwtToken: state.user.jwtToken
    }
}

const MainPage = connect(
    mapStateToProps
)(PreMainPage);

export default MainPage;