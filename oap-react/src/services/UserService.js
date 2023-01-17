import { baseUrl, config } from "../util/AppConstants";
import axios from 'axios';

export function registerUser(user){
    return axios.post(baseUrl + "/api/user/register/", user)
}

export function userLogin(userName, password){
    return axios.get(baseUrl + `/api/user/login/?value=${userName}&password=${password}`)
}

export function getUserById(userId){
    return axios.get(baseUrl + `/api/user/get-by-id/?id=${userId}`)
}

export function getUserByName(userName){
    return axios.get(baseUrl + `/api/user/get-by-username/?username=${userName}`)
}