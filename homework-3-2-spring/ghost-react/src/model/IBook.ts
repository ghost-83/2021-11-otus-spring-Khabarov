import { IAuthor } from "./IAuthor";
import { IGenre } from "./IGenre";

export interface IBook {
    id: number
    name: string
    author: IAuthor
    genre: IGenre
}