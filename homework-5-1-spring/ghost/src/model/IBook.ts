import {ICategory} from "./ICategory";
import {IAuthor} from "./IAuthor";

export interface IBook {
    id: number
    title: string
    text: string
    author: IAuthor
    genre: ICategory
}