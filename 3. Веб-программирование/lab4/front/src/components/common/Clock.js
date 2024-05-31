import React from "react";

class Clock extends React.Component{
    constructor(props){
        super(props);
        this.state = { time: new Date() };
    }
    render(){
        return(
            <div className={"row center-align"}>
                <p id={"updating-clock"} className={"col s12"}> { this.state.time.toLocaleString("ru-RU", {
                    hour12: false
                }) }
                </p>
            </div>

        );
    }
    componentDidMount() {
        this.interval = setInterval(() => this.setState({ time: new Date() }), 12*1000);
    }
    componentWillUnmount() {
        clearInterval(this.interval);
    }
}

export default Clock;