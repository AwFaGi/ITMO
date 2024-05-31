import React from "react";
import 'material-design-icons-iconfont'
import DefaultTitle from "./DefaultTitle";
import HomePageLink from "../links/HomePageLink";
import {connect} from "react-redux";
import WhileOutLinksBlock from "../links/WhileOutLinksBlock";
import WhileInLinksBlock from "../links/WhileInLinksBlock";

class LocalHeader extends React.Component{

    render() {
        return (
            <div className={"row"}>
                <header className={"valign-wrapper"}>
                    <div className={"col s2 m3 xl2"}>
                        <HomePageLink />
                    </div>
                    <div className={"col s4 m4 xl4"}>
                        <DefaultTitle />
                    </div>

                    <div className={"col s6 m5 xl6 right-align "}>
                        <div>
                            {this.props.isLogged ? <WhileInLinksBlock /> : <WhileOutLinksBlock />}
                        </div>
                    </div>
                </header>
            </div>

        )
    }
}

const mapStateToProps = function(state) {
    return {
        isLogged: state.user.isLogged,
        username: state.user.username
    }
}

const Header = connect(
    mapStateToProps
)(LocalHeader);

export default Header;