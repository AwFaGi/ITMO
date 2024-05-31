import React from "react";
import {RxMinus} from "react-icons/rx";
import {RiNumber0, RiNumber1, RiNumber2, RiNumber3, RiNumber4, RiNumber5} from "react-icons/ri";
import {connect, useDispatch} from "react-redux";
import {setX} from "../../redux/formStateSlice";

class LocalXValueBlock extends React.Component {

    constructor(props) {
        super(props);
        this.processBtn = this.processBtn.bind(this);
    }

    processBtn(event){
        let value;

        if (event.target.nodeName === "svg"){
            value = event.target.parentNode.value;
        }else if(event.target.nodeName === "path"){
            value = event.target.ownerSVGElement.parentNode.value;
        } else {
            value = event.target.value;
        }

        console.log(event);

        this.props.dispatch(setX(value));
        event.preventDefault();
    }

    render() {
        return (
            <div className={"input-field col s10 push-s1 xl12"}>
                <div className={"row"}>
                    Значение X: {this.props.x_value}
                </div>
                <div className={"row"}>
                    <button className="btn waves-light" value={-3} onClick={this.processBtn}>
                        <RxMinus />
                        <RiNumber3 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                    <button className="btn waves-light" value={-2} onClick={this.processBtn}>
                        <RxMinus />
                        <RiNumber2 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                    <button className="btn waves-light" value={-1} onClick={this.processBtn}>
                        <RxMinus />
                        <RiNumber1 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                </div>
                <div className={"row"}>
                    <button className="btn waves-light" value={0} onClick={this.processBtn}>
                        <RxMinus className={"custom-hide-elem"}/>
                        <RiNumber0 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                    <button className="btn waves-light" value={1} onClick={this.processBtn}>
                        <RxMinus className={"custom-hide-elem"}/>
                        <RiNumber1 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                    <button className="btn waves-light" value={2} onClick={this.processBtn}>
                        <RxMinus className={"custom-hide-elem"}/>
                        <RiNumber2 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                </div>
                <div className={"row"}>
                    <button className="btn waves-light" value={3} onClick={this.processBtn}>
                        <RxMinus className={"custom-hide-elem"}/>
                        <RiNumber3 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                    <button className="btn waves-light" value={4} onClick={this.processBtn}>
                        <RxMinus className={"custom-hide-elem"}/>
                        <RiNumber4 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                    <button className="btn waves-light" value={5} onClick={this.processBtn}>
                        <RxMinus className={"custom-hide-elem"}/>
                        <RiNumber5 />
                        <RxMinus className={"custom-hide-elem"}/>
                    </button>
                </div>
            </div>
        )
    }
}

const PatchedXValueBlock = (props) => {
    const dispatch = useDispatch();
    return <LocalXValueBlock dispatch={dispatch} {...props} />
}

const mapStateToProps = function(state) {
    return {
        x_value: state.formState.x
    }
}

const XValueBlock = connect(
    mapStateToProps
)(PatchedXValueBlock);

export default XValueBlock;