import React from "react";
import LoginForm from "../forms/LoginForm";

function LoginPage() {
    return (
        <div className={"row"}>
            <div className={"col s10 push-s1 m6 push-m3 xl4 push-xl4"}>
                <LoginForm />
            </div>
        </div>
    );
}

export default LoginPage;
