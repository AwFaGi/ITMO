import React from "react";
import RegisterLink from "./RegisterLink";
import LoginLink from "./LoginLink";

class WhileOutLinksBlock extends React.Component{

    render() {
        return (
            <div className={"row"}>
                <div className={"col s12 m12 xl6"}>
                    <RegisterLink />
                </div>
                <div className={"col s12 m12 xl1"}>
                    <LoginLink />
                </div>
            </div>
        )
    }
}

export default WhileOutLinksBlock;