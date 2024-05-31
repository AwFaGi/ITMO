import React from "react";
import {Link} from "react-router-dom";
import 'material-design-icons-iconfont'

class HomePageLink extends React.Component{
    render() {
        return (
            <Link to={"/"} replace={true}>
                <p className="waves-light btn ">
                    <i className="material-icons s0 m4 l4 show-on-medium-and-down hide-on-med-and-up">home</i>
                    <i className="material-icons s0 m4 l4 left hide-on-med-and-down">home</i>
                    <span className={"hide-on-med-and-down"}>На главную</span>
                </p>
            </Link>

        )
    }
}

export default HomePageLink;