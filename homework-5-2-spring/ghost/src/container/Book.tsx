import {FC, useEffect, useState} from "react"
import {Box} from "@mui/material"
import {useAppSelector} from "../hook/redux"
import {useDispatch} from "react-redux"
import {fetchBooks} from "../hook/creator/book.creator"
import {IBook} from "../model/IBook";
import {HeaderCategory} from "../component/Category/HeaderCategory";
import {ReferenceBookComp} from "../component/Book/BookComp";

export const Book: FC = () => {

    const {categories} = useAppSelector(state => state.categoryReducer)
    const {books} = useAppSelector(state => state.postReducer)
    const [bookModal, setBookModal] = useState<null | IBook>(null)
    const [expanded, setExpanded] = useState<string | false>(false);
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(fetchBooks())
    }, [dispatch])

    return (
        <Box sx={{flexGrow: 1}}>
            <HeaderCategory categories={categories.map(e => e.name)} typeData="book"/>
            {books && books.length > 0 && books.map((data, key) =>
                <ReferenceBookComp key={`panel${key}bh-header`} setBookModal={setBookModal}
                                   setExpanded={setExpanded} expanded={expanded} value={data} keyData={key}/>
            )}
        </Box>
    )
}