import axios, {AxiosInstance} from "axios"
import {IPost} from "../model/IPost";

class PostApi {

    private instants: AxiosInstance

    constructor() {
        this.instants = axios.create({
            baseURL: "/api/v1"
        })
    }

    async findAll(): Promise<Array<IPost>> {
        return (await this.instants.get("/post")).data
    }

    async findAllByCategory(genre: string): Promise<Array<IPost>> {
        return (await this.instants.get(`/post/genre/${genre}`)).data
    }

    async search(search: string): Promise<Array<IPost>> {
        return (await this.instants.get(`/post/search/${search}`)).data
    }
}

export const postApi = new PostApi()