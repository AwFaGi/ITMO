import React from "react";
import {connect} from "react-redux";
import pointUpdater from "../../service/PointUpdater";
import PointService from "../../service/PointService";


class LocalCanvasComponent extends React.Component {

    constructor(props) {
        super(props);
        this.canvas = null;
        this.setCanvasRef = this.setCanvasRef.bind(this);
        this.updateCanvas = this.updateCanvas.bind(this);
        this.getCursorPosition = this.getCursorPosition.bind(this);
    }

    setCanvasRef(element) {
        this.canvas = element;
        if (element == null){
            return;
        }
        this.context = element.getContext('2d');
        this.context.translate(this.canvas.width/2, this.canvas.height/2);
        this.context.scale(1,-1);
    };

    componentDidMount() {
        this.updateCanvas();
        this.interval = setInterval(() => this.updateCanvas(), 1000/2);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    updateCanvas() {
        const r_value = this.props.r_value;
        // canvas
        redraw_canvas(this.canvas, r_value);
        // axes
        redraw_axes(this.canvas);

        // labels
        redraw_labels(this.canvas, r_value);

        //points
        redraw_points(this.canvas, r_value, this.props.points);
    }

    getCursorPosition(event) {
        const rect = this.canvas.getBoundingClientRect();

        // calculate click place
        let x = (event.clientX - rect.left) - this.canvas.width/2;
        let y = this.canvas.height/2 - (event.clientY - rect.top);

        // normalise click place
        x = x / CANVAS_SCALE;
        y = y / CANVAS_SCALE;

        this.props.pointService.sendPoint(
            x.toFixed(2), y.toFixed(2), this.props.r_value, this.props.jwtToken
        ).then(r => {
            console.log("point sent");
            console.log("canvas try to update points");
            pointUpdater(this.props.dispatch, this.props.pointService, this.props.jwtToken, this.props.currentPage);
            this.updateCanvas();
        })

        event.preventDefault();

    }

    render() {
        return (
            <canvas ref={this.setCanvasRef} onClick={this.getCursorPosition} width={300} height={300}/>
        );
    }
}

const CANVAS_SCALE = 30;

function redraw_axes(canvas){
    const context = canvas.getContext('2d');
    context.fillStyle = "rgb(1, 1, 1)"

    // y - axe
    context.beginPath();
    context.moveTo(0, -canvas.height/2 + 1);
    context.lineTo(0, canvas.height/2 - 1);
    context.closePath();
    context.stroke();

    // x - axe
    context.beginPath();
    context.moveTo(-canvas.width/2 + 1, 0);
    context.lineTo(canvas.width/2 - 1, 0);
    context.closePath();
    context.stroke();
}
function redraw_labels(canvas, r_value){

    const context = canvas.getContext('2d');
    context.fillStyle = "rgb(1, 1, 1)"

    // left
    context.beginPath();
    context.moveTo(-Math.floor(r_value * CANVAS_SCALE), -5);
    context.lineTo(-Math.floor(r_value * CANVAS_SCALE), 5);
    context.stroke();

    // half-left
    context.beginPath();
    context.moveTo(-Math.floor(r_value/2 * CANVAS_SCALE), -5);
    context.lineTo(-Math.floor(r_value/2 * CANVAS_SCALE), 5);
    context.stroke();

    // right
    context.beginPath();
    context.moveTo(Math.floor(r_value * CANVAS_SCALE), -5);
    context.lineTo(Math.floor(r_value * CANVAS_SCALE), 5);
    context.stroke();

    // half-right
    context.beginPath();
    context.moveTo(Math.floor(r_value/2 * CANVAS_SCALE), -5);
    context.lineTo(Math.floor(r_value/2 * CANVAS_SCALE), 5);
    context.stroke();

    // top
    context.beginPath();
    context.moveTo(-5, Math.floor(r_value * CANVAS_SCALE));
    context.lineTo(5, Math.floor(r_value * CANVAS_SCALE));
    context.stroke();

    // half-top
    context.beginPath();
    context.moveTo(-5, Math.floor(r_value/2 * CANVAS_SCALE));
    context.lineTo(5, Math.floor(r_value/2 * CANVAS_SCALE));
    context.stroke();

    // bottom
    context.beginPath();
    context.moveTo(-5, -Math.floor(r_value * CANVAS_SCALE));
    context.lineTo(5, -Math.floor(r_value * CANVAS_SCALE));
    context.stroke();

    // half-bottom
    context.beginPath();
    context.moveTo(-5, -Math.floor(r_value/2 * CANVAS_SCALE));
    context.lineTo(5, -Math.floor(r_value/2 * CANVAS_SCALE));
    context.stroke();
}
function redraw_points(canvas, r_value, points){
    const context = canvas.getContext('2d');
    context.save();
    for (let point of points)
    {
        try{
            let point_x = Number(point.x);
            let point_y = Number(point.y);
            let point_r = Number(point.r);

            if(isNaN(point_x) || isNaN(point_y)){
                continue;
            }

            context.fillStyle = "#000000"
            context.beginPath(); //Start path
            context.arc(point_x * CANVAS_SCALE, point_y * CANVAS_SCALE,
                5, 0, Math.PI * 2
            );
            context.fill();

            if (point_r === Number(r_value)){
                if (point.status){
                    context.fillStyle = "#00ff00";
                }else {
                    context.fillStyle = "#ff0000";
                }
            }else {
                context.fillStyle = "#ffff00";
            }

            context.beginPath(); //Start path
            context.arc(point_x * CANVAS_SCALE, point_y * CANVAS_SCALE,
                3, 0, Math.PI * 2
            );
            context.fill();

        } catch (e){
            console.log(e);
        }

    }

    context.restore();
}
function redraw_canvas(canvas, r_value){

    const context = canvas.getContext('2d');
    context.clearRect(-canvas.width/2, -canvas.height/2, canvas.width, canvas.height);

    context.fillStyle = "rgb(51, 153, 255)";

    // 1 quarter
    context.beginPath();
    context.arc(0,0, Math.floor(r_value/2 * CANVAS_SCALE), 0, Math.PI/2);
    context.moveTo(0,0);
    context.lineTo(0, Math.floor(r_value/2 * CANVAS_SCALE));
    context.lineTo(Math.floor(r_value/2 * CANVAS_SCALE),0);
    context.closePath();
    context.fill();

    // 2 quarter
    context.beginPath();
    context.moveTo(0,0);
    context.lineTo(0, Math.floor(r_value * CANVAS_SCALE));
    context.lineTo(-Math.floor(r_value * CANVAS_SCALE),0);
    context.closePath();
    context.fill();

    // 3 quarter


    // 4 quarter
    context.beginPath();
    context.moveTo(0, -Math.floor(r_value/2 * CANVAS_SCALE));
    context.lineTo(Math.floor(r_value * CANVAS_SCALE), -Math.floor(r_value/2 * CANVAS_SCALE));
    context.lineTo(Math.floor(r_value * CANVAS_SCALE),0);
    context.lineTo(0,0);
    context.closePath();
    context.fill();
}

const PatchedCanvasComponent = (props) => {

    const pointService = new PointService();

    return <LocalCanvasComponent pointService={pointService} {...props} />
}


const mapStateToProps = function(state) {
    return {
        jwtToken: state.user.jwtToken,
        points: state.point.points,
        currentPage: state.point.currentPage,
        r_value: state.formState.r
    }
}

const CanvasComponent = connect(
    mapStateToProps
)(PatchedCanvasComponent);

export default CanvasComponent;