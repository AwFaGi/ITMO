import React from "react";
import Clock from "../common/Clock";

function StartPage() {
    return (
        <div className={"row"}>
            <div className={"col s6 push-s3 page-frame"}>
                <Clock />
            </div>
        </div>
    );
}

export default StartPage;
