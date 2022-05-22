import axios, {AxiosInstance} from "axios"
import {IReferenceBook} from "../model/IReferenceBook";

class BookApi {

    private instants: AxiosInstance

    constructor() {
        this.instants = axios.create({
            baseURL: "/api/v1"
        })
    }

    async findAll(): Promise<Array<IReferenceBook>> {
        return (await this.instants.get("/book")).data
    }

    async findAllByCategory(genre: string): Promise<Array<IReferenceBook>> {
        return (await this.instants.get(`/book/genre/${genre}`)).data
    }

    async search(search: string): Promise<Array<IReferenceBook>> {
        return (await this.instants.get(`/book/search/${search}`)).data
    }

    async findById(id: number): Promise<IReferenceBook> {
        return (await this.instants.get(`/book/${id}`)).data
    }

    async save(data: IReferenceBook) {
        await this.instants.post("/book/", {...data})
    }

    async delete(id: number) {
        await this.instants.delete(`/book/${id}`)
    }
}

export const bookApi = new BookApi()