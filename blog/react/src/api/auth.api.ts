import axios, {AxiosInstance} from "axios"
import {AuthData, AuthUser} from "../model/IUser"
import {IRegistration} from "../model/IRegistration";

class AuthApi {

    private instants: AxiosInstance

    constructor() {
        this.instants = axios.create({baseURL: "/api/v1/auth"})
    }

    async getToken(data: AuthData): Promise<AuthUser> {
        return (await this.instants.post("/login", {...data})).data
    }

    async saveUser(data: IRegistration): Promise<AuthUser> {
        return (await this.instants.post("/registration", {...data})).data
    }

    async deleteToken() {
        (await this.instants.delete(`/logout`))
    }
}

export const authApi = new AuthApi()