import axios, {AxiosInstance} from "axios"
import {IBook} from "../model/IBook";

class BookApi {

    private instants: AxiosInstance

    constructor() {
        this.instants = axios.create({
            baseURL: "/api/v1"
        })
    }

    async findAll(): Promise<Array<IBook>> {
        return (await this.instants.get("/book")).data
    }

    async findAllByCategory(category: string): Promise<Array<IBook>> {
        return (await this.instants.get(`/category/${category}`)).data
    }

    async search(search: string): Promise<Array<IBook>> {
        return (await this.instants.get(`/book/search/${search}`)).data
    }

    async findById(id: number): Promise<IBook> {
        return (await this.instants.get(`/book/${id}`)).data
    }

    async save(data: IBook) {
        await this.instants.post("/book/", {...data})
    }

    async delete(id: number) {
        await this.instants.delete(`/book/${id}`)
    }
}

export const bookApi = new BookApi()