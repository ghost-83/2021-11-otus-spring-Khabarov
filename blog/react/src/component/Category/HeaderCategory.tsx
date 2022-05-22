import {ChangeEvent, FC, useEffect, useState} from "react";
import {Divider, Grid, IconButton, MenuItem, Select, SelectChangeEvent, TextField} from "@mui/material";
import {Search} from "@mui/icons-material";
import {useDispatch} from "react-redux";
import {fetchCategory} from "../../hook/creator/categort.creator";
import {fetchPostCategory, fetchPosts, searchPost} from "../../hook/creator/post.creator";
import {
    fetchReferenceBookCategory,
    fetchReferenceBooks,
    searchReferenceBook
} from "../../hook/creator/reference.book.creator";

interface Props {
    categories: string[] | null
    typeData: "post" | "referenceBook"
}

export const HeaderCategory: FC<Props> = ({categories, typeData}) => {

    const [data, setData] = useState('all');
    const [search, setSearch] = useState('');
    const dispatch = useDispatch()

    useEffect(() => {
        if (categories === null || categories.length === 0)
            dispatch(fetchCategory())
    }, [dispatch])

    const handleChangeSelect = (event: SelectChangeEvent) => {
        if (event.target.value !== 'all') {
            switch (typeData) {
                case "post":
                    dispatch(fetchPostCategory(event.target.value))
                    break
                default:
                dispatch(fetchReferenceBookCategory(event.target.value))
            }
        } else
            allData()
        setData(event.target.value)
    }

    const allData = () => {

        switch (typeData) {
            case "post":
                dispatch(fetchPosts())
                break
            default:
            dispatch(fetchReferenceBooks())
        }
    }

    const handleSearch = () => {
        if (search.length > 0)
            switch (typeData) {
                case "post":
                    dispatch(searchPost(search))
                    break
                default:
                dispatch(searchReferenceBook(search))
            }
        else
            allData()
    }

    const handleTextSearch = (text: ChangeEvent<HTMLInputElement>) => {
        setSearch(text.currentTarget.value)
    }

    return (
        <>
            <Grid container spacing={0} direction="row" justifyContent="flex-end" alignItems="flex-end">
                <Grid item sx={{color: 'text.secondary', width: '15rem'}}>
                    <Select
                        labelId="reference-post-simple-select-standard-label"
                        id="reference-post-simple-select-standard"
                        value={data}
                        label="Age"
                        variant="standard"
                        onChange={handleChangeSelect}
                        sx={{color: 'text.secondary', width: '10rem'}}
                    >
                        <MenuItem value="all">
                            <em>all</em>
                        </MenuItem>
                        {categories && categories.map((category) => (
                            <MenuItem key={'ReferenceBook' + category} value={category}
                                      sx={{textTransform: 'lowercase'}}>
                                {category}
                            </MenuItem>
                        ))}
                    </Select>
                </Grid>
                <Grid item sx={{color: 'text.secondary', flexGrow: 1}}>
                    <TextField type="search" onChange={handleTextSearch} fullWidth={true} id="standard-basic"
                               label="search" variant="standard"/>
                </Grid>
                <Grid item>
                    <IconButton onClick={handleSearch}>
                        <Search color="primary"/>
                    </IconButton>
                </Grid>
            </Grid>
            <Divider sx={{mt: 1, mb: 2}}/>
        </>
    )
}