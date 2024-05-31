import React from "react";
import {Link} from "react-router-dom";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router";
import clearStoreInLogout from "../../service/LogoutUtil";

class LocalLogoutLink extends React.Component{

    constructor(props) {
        super(props);

        this.doLogout = this.doLogout.bind(this);
    }

    doLogout(event){
        clearStoreInLogout(this.props.dispatch);
        this.props.navigate("/", {replace: true});
        // window.location.reload();
        event.preventDefault();
    }

    render() {
        return (
            <Link to={"/"} replace={true} onClick={this.doLogout} >Выйти</Link>
        )
    }
}

const LogoutLink = props => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    return <LocalLogoutLink dispatch={dispatch} navigate={navigate} {...props} />
}

export default LogoutLink;