import React from "react";
import {setUsername, setJwtToken, setIsLogged} from '../../redux/userSlice'
import {useNavigate} from "react-router";
import {useDispatch} from "react-redux";
import AuthService from "../../service/AuthService";
import RegisterLink from "../links/RegisterLink";
import createToast from "../../service/Toaster";


class LocalLoginForm extends React.Component{

    constructor(props) {
        super(props);
        this.doLogin = this.doLogin.bind(this);
    }

    doLogin(event){
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

        this.props.authService.login(username.value, password.value)
        .then(result => {
            if (result.status === 200){
                this.props.dispatch(setUsername(result.data.username));
                this.props.dispatch(setJwtToken(result.data.jwtToken));
                this.props.dispatch(setIsLogged(true));
                localStorage.setItem("token", result.data.jwtToken);
                localStorage.setItem("username", result.data.username);
                this.props.navigate('/main', {replace: true});
                createToast('Авторизация прошла успешно');
            }
        }).catch(reason => {
            if ( reason.response !== undefined && reason.response.status === 400){
                createToast(reason.response.data.message)
            } else {
                createToast('Ошибка авторизации!\nПопробуйте позже');
            }

        });
        event.preventDefault();
    }

    render() {
        return (
            <div className={"row page-frame"}>
                <form className={"col s12 m10 push-m1 l8 push-l2 xl8 push-xl2 center-align"} onSubmit={this.doLogin}>

                    <div className={"row"}>
                        <p className={"flow-text"}>Авторизация</p>
                    </div>

                    <div className={"row"}>
                        <div className={"input-field col s12"}>

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
                        <button className="btn waves-light" type="submit" name="action">Войти
                        </button>
                    </div>

                    Нет аккаунта? <RegisterLink />
                </form>

            </div>

        )
    }

}

const LoginForm = props => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const authService = new AuthService();
    return <LocalLoginForm navigate={navigate} dispatch={dispatch} authService={authService} {...props} />
}

export default LoginForm;