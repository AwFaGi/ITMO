import React from "react";
import MainPageLink from "./MainPageLink";
import {connect} from "react-redux";
import LogoutLink from "./LogoutLink";

class LocalWhileInLinksBlock extends React.Component{

    render() {
        return (
            <div className={"row"}>
                {this.props.isLogged ?
                    <div className={"col s12 m12 xl6"}>
                        <MainPageLink />
                    </div>
                    : null
                }
                <div className={"col s12 m12 xl1"}>
                    <LogoutLink />
                </div>
            </div>
        )
    }
}

const mapStateToProps = function(state) {
    return {
        isLogged: state.user.isLogged,
    }
}

const WhileInLinksBlock = connect(
    mapStateToProps
)(LocalWhileInLinksBlock);

export default WhileInLinksBlock;