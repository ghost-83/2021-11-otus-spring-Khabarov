import axios, {AxiosInstance} from "axios"
import {AuthData, AuthUser} from "../model/IUser"

class AuthApi {

    private instants: AxiosInstance

    constructor() {
        this.instants = axios.create({baseURL: "/auth"})
    }

    async getToken(data: AuthData): Promise<AuthUser> {
        return (await this.instants.post("/login", {...data})).data
    }

    async deleteToken() {
        (await this.instants.delete(`/logout`))
    }
}

export const authApi = new AuthApi()