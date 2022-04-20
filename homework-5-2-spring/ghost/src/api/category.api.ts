import axios, {AxiosInstance} from "axios"
import {ICategory} from "../model/ICategory"

class CategoryApi {

    private instants: AxiosInstance

    constructor() {
        this.instants = axios.create({
            baseURL: "/api/v1"
        })
    }

    async findAll(): Promise<Array<ICategory>> {
        return await (await this.instants.get("/genre")).data
    }

    async findById(id: number): Promise<ICategory> {
        return await (await this.instants.get(`/${id}`)).data
    }

    async save(data: ICategory) {
        await this.instants.post("/", {...data})
    }
}

export const categoryApi = new CategoryApi()