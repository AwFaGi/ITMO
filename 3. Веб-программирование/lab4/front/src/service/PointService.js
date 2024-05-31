import axios from "axios";

const API_URL="http://localhost:27800/points/"

class PointService {
    getPoints(jwtToken, page){
        return axios.get(API_URL + "list", {
            params: {
                page: page
            },
            headers: {
                'Authorization': 'Bearer ' + jwtToken
            }
        })
    };

    sendPoint(x, y, r, jwtToken){
        return axios.post(API_URL + "add", {
                    'x': x,
                    'y': y,
                    'r': r
                },{
            headers: {
                'Authorization': 'Bearer ' + jwtToken
            }
        })
    };

    getPointCount(jwtToken){
        return axios.get(API_URL + "count", {
            headers: {
                'Authorization': 'Bearer ' + jwtToken
            }
        })
    }

}

export default PointService;