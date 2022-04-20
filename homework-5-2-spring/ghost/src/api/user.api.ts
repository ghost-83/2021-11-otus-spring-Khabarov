import axios, {AxiosInstance} from "axios"
// import {IUser} from "../model/IUser"

class UserApi {

    private instants: AxiosInstance

    constructor() {
        this.instants = axios.create({
            baseURL: "/api/v1/user"
        })
    }

    // async findAll(): Promise<IUser> {
    //     return (await this.instants.get("/")).data
    // }
    //
    // async findById(id: number): Promise<IUser> {
    //     return (await this.instants.get(`/${id}`)).data
    // }
    //
    // async save(data: IUser) {
    //     await this.instants.post("/", {data})
    // }

    async delete(id: number) {
        await this.instants.delete(`/${id}`)
    }
}

export const userApi = new UserApi