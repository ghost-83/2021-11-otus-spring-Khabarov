import {FC, useEffect, useState} from "react"
import {Box} from "@mui/material"
import {useAppSelector} from "../hook/redux"
import {useDispatch} from "react-redux"
import {HeaderCategory} from "../component/Category/HeaderCategory";
import {ReferenceBookComp} from "../component/Book/ReferenceBookComp";
import {fetchReferenceBooks} from "../hook/creator/reference.book.creator";

export const ReferenceBook: FC = () => {

    const {categories} = useAppSelector(state => state.categoryReducer)
    const {books} = useAppSelector(state => state.referenceBookReducer)
    const [expanded, setExpanded] = useState<string | false>(false);
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(fetchReferenceBooks())
    }, [dispatch])

    return (
        <Box sx={{flexGrow: 1}}>
            <HeaderCategory categories={categories.map(e => e.name)} typeData="referenceBook"/>
            {books && books.length > 0 && books.map((data, key) =>
                <ReferenceBookComp key={`panel${key}bh-header`} setExpanded={setExpanded} expanded={expanded}
                                   value={data} keyData={key}/>
            )}
        </Box>
    )
}