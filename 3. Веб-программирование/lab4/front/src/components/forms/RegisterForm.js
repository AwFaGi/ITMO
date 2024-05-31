import React from "react";
import {useNavigate} from "react-router";
import {useDispatch} from "react-redux";
import LoginLink from "../links/LoginLink";
import AuthService from "../../service/AuthService";
import createToast from "../../service/Toaster";

class LocalRegisterForm extends React.Component{

    constructor(props) {
        super(props);

        this.doRegister = this.doRegister.bind(this);
    }

    doRegister(event){
        const [username, password] = event.target;

        if(username.value.trim() === "" || password.value.trim() === ""){
            createToast('Поля не должны быть пустыми!');
            event.preventDefault();
            return;
        }

        if(username.value.indexOf(" ") >= 0 || password.value.indexOf(" ") >= 0){
            createToast('Поля не должны содержать пробелов!');
            event.preventDefault();
            return;
        }

        if( !username.value.match(/^[a-zA-Z]{3,10}$/) || !password.value.match(/^[a-zA-Z]{3,10}$/)){
            createToast('Можно использовать только<br/>латинские буквы (3-10)');
            event.preventDefault();
            return;
        }

        this.props.authService.register(username.value, password.value)
            .then(result => {
                if (result.status === 200 && !result.data.error){
                    createToast('Успешно!');
                    this.props.navigate("/login");
                }
            }).catch(reason => {
                if ( reason.response !== undefined && reason.response.status === 400){
                    createToast(reason.response.data.message)
                } else {
                    createToast('Ошибка регистрации!\nПопробуйте позже')
                }
        });
        event.preventDefault();
    }

    render() {
        return (
            <div className={"row page-frame"}>
                <form className={"col s12 m10 push-m1 l8 push-l2 xl8 push-xl2 center-align"} onSubmit={this.doRegister}>
                    <div className={"row"}>
                        <p className={"flow-text"}>Регистрация</p>
                    </div>

                    <div className={"row"}>
                        <div className={"col s2"}>

                        </div>

                        <div className="input-field col s12">

                            <input id="login" type="text"/>
                            <label htmlFor="login">Логин</label>
                        </div>
                    </div>
                    <div className={"row"}>
                        <div className="input-field col s12">
                            <input id="password" type="password"/>
                            <label htmlFor="password">Пароль</label>
                        </div>
                    </div>

                    <div className={"row"}>
                        <button className="btn waves-light" type="submit" name="action">
                            Зарегистрироваться
                        </button>
                    </div>

                    Есть аккаунт? <LoginLink />
                </form>
            </div>

        )
    }

}

const RegisterForm = props => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const authService = new AuthService();
    return <LocalRegisterForm navigate={navigate} dispatch={dispatch} authService={authService} {...props} />
}



export default RegisterForm;