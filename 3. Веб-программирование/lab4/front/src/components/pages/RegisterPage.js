import React from "react";
import RegisterForm from "../forms/RegisterForm";

function RegisterPage() {
    return (
        <div className={"row"}>
            <div className={"col s10 push-s1 m6 push-m3 xl4 push-xl4"}>
                <RegisterForm />
            </div>
        </div>
    );
}

export default RegisterPage;
