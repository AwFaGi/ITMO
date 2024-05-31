import React from "react";
import {Link} from "react-router-dom";

class LoginLink extends React.Component{

    render() {
        return (
            <Link to={'/login'} replace={true}>Войти</Link>
        )
    }
}

export default LoginLink;