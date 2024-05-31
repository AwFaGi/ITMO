import React from "react";
import {connect, useDispatch} from "react-redux";
import XValueBlock from "./XValueBlock";
import RValueBlock from "./RValueBlock";
import PointService from "../../service/PointService";
import {setY} from "../../redux/formStateSlice";
import pointUpdater from "../../service/PointUpdater";

class LocalPointForm extends React.Component{

    constructor(props) {
        super(props);
        this.button = null;
        this.doSend = this.doSend.bind(this);
        this.validateY = this.validateY.bind(this);
        this.setBtnRef = this.setBtnRef.bind(this);
        this.isProcessing = false;
    }

    doSend(event){

        if (this.isProcessing){
            event.preventDefault();
            return;
        }

        this.isProcessing = true;

        this.props.pointService.sendPoint(this.props.x, this.props.y, this.props.r, this.props.jwtToken).then(
            result => {
                if (result.status === 200){
                    pointUpdater(this.props.dispatch, this.props.pointService, this.props.jwtToken, this.props.currentPage);
                }
            }
        )

        this.isProcessing = false;
        event.preventDefault();
    }

    setBtnRef(element){
        if (element == null){
            return;
        }
        this.button = element;
        this.button.classList.add("disabled");
    }

    validateY(event){
        const value = event.target.value;
        const number_text = Number(value);
        if ( value==="" || isNaN(number_text) || !isFinite(number_text) || number_text < -3 || number_text > 5){
            event.target.classList.remove("valid");
            event.target.classList.add("invalid");
            this.button.classList.add("disabled");

        } else {
            event.target.classList.remove("invalid");
            event.target.classList.add("valid");
            this.button.classList.remove("disabled");
            this.props.dispatch(setY(value));
        }
    }

    render() {
        return (
            <div className={"row "}>
                <form className={"col s12 m10 push-m1 l10 push-l1 xl8 push-xl2 center-align"} onSubmit={this.doSend}>
                    <div className={"row"}>
                        <div className={"col s6"}>
                            <XValueBlock />
                        </div>
                        <div className={"col s6"}>
                            <RValueBlock />
                        </div>
                    </div>
                    <div className={"row"}>
                        <div className="input-field col s8 push-s2">
                            <input id="last_name" type="text" onInput={this.validateY} maxLength={4}/>
                            <label htmlFor="last_name">Значение Y</label>
                        </div>
                    </div>

                    <div className={"row"}>
                        <button className="btn waves-light disabled"
                                type="submit" name="action"
                                ref={this.setBtnRef}>Отправить
                        </button>
                    </div>
                </form>

            </div>

        )
    }

}

const PatchedPointForm = props => {
    const dispatch = useDispatch();
    const pointService = new PointService();
    return <LocalPointForm dispatch={dispatch} pointService={pointService} {...props} />
}

const mapStateToProps = function(state) {
    return {
        jwtToken: state.user.jwtToken,
        x: state.formState.x,
        y: state.formState.y,
        r: state.formState.r,
        currentPage: state.point.currentPage,
    }
}

const PointForm = connect(
    mapStateToProps
)(PatchedPointForm);

export default PointForm;