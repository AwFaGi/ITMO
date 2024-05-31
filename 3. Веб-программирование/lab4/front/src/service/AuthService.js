import axios from "axios";

const API_URL="http://localhost:27800/auth/"

class AuthService {
    login(username, password){
        return axios.post(API_URL + "login", {
            "username": username,
            "password": password
        })
    };

    register(username, password){
        return axios.post(API_URL + "register", {
            "username": username,
            "password": password
        })
    };

}

export default AuthService;