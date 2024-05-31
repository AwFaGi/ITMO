import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

class LocalMainPageLink extends React.Component{

    render() {
        return (
            <Link to={'/main'} replace={true}>{this.props.username}</Link>
        )
    }
}

const mapStateToProps = function(state) {
    return {
        username: state.user.username
    }
}

const MainPageLink = connect(
    mapStateToProps
)(LocalMainPageLink);

export default MainPageLink;