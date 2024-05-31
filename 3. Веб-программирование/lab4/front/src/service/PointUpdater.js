import {addPoint, clearAll, setPointCount, setTotalPages} from "../redux/pointSlice";
import clearStoreInLogout from "./LogoutUtil";
import createToast from "./Toaster";

function pointUpdater(dispatch, pointService, jwtToken, page){
    dispatch(clearAll());
    console.log("я вызвался pu");
    pointService.getPoints(jwtToken, page).then(
        result => {
            for (let d of result.data){
                dispatch(addPoint(d));
            }
            updatePointCount(dispatch, pointService, jwtToken);
        }
    ).catch(reason => {
        console.log("slovil", reason);
        if (reason.response.status === 401){
            clearStoreInLogout(dispatch);
            createToast("Сессия истекла<br>Нужно войти заново");
        }
    })


}

function updatePointCount(dispatch, pointService, jwtToken){
    pointService.getPointCount(jwtToken).then(
        result => {
            console.log("updater count ", result.data);
            dispatch(setPointCount(result.data));
            dispatch(setTotalPages(Math.ceil(result.data / 10)));
        }
    )
}

export default pointUpdater;