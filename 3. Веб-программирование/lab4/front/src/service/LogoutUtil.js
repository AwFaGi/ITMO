import {setIsLogged, setJwtToken, setUsername} from "../redux/userSlice";
import {clearAll} from "../redux/pointSlice";

function clearStoreInLogout(dispatch){
    dispatch(setUsername(""));
    dispatch(setJwtToken(""));
    dispatch(setIsLogged(false));
    dispatch(clearAll());
    localStorage.clear();
}

export default clearStoreInLogout;