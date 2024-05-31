import React from "react";
import {Link} from "react-router-dom";

class RegisterLink extends React.Component{

    render() {
        return (
            <Link to={'/register'} replace={true}>Зарегистрироваться</Link>
        )
    }
}

export default RegisterLink;