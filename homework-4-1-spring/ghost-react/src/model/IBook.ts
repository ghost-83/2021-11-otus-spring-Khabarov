import { IAuthor } from "./IAuthor";
import { IGenre } from "./IGenre";

export interface IBook {
    id: string
    name: string
    author: IAuthor
    genre: IGenre
}