import {ICategory} from "./ICategory";
import {IAuthor} from "./IAuthor";

export interface IPost {
    id: number
    title: string
    text: string
    image: string
    author: IAuthor
    genre: ICategory
}