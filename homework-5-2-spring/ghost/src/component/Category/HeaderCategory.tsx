import {ChangeEvent, FC, useState} from "react";
import {Divider, Grid, IconButton, MenuItem, Select, SelectChangeEvent, TextField} from "@mui/material";
import {Search} from "@mui/icons-material";
import {useDispatch} from "react-redux";
import {useAppSelector} from "../../hook/redux";
import {modalSlice, ModalType} from "../../hook/slice/modal.slice";
import {fetchBookCategory, fetchBooks, searchBook} from "../../hook/creator/book.creator";

interface Props {
    categories: string[] | null
    typeData: "post" | "book" | "file" | "movie" | "music"
}

export const HeaderCategory: FC<Props> = ({categories, typeData}) => {

    const [data, setData] = useState('all');
    const [search, setSearch] = useState('');
    const dispatch = useDispatch()

    const handleChangeSelect = (event: SelectChangeEvent) => {
        if (event.target.value !== 'all') {
            switch (typeData) {
                case "post":
                    dispatch(fetchBookCategory(event.target.value))
                    break
                default:
                // dispatch(fetchIFilesCategory(event.target.value))
            }
        } else
            allData()
        setData(event.target.value)
    }
    //
    // const handleOpenCreate = () => {
    //     dispatch(modalSlice.actions.openModalWindow(ModalType.CREATE))
    // }

    const allData = () => {

        switch (typeData) {
            case "post":
                dispatch(fetchBooks())
                break
            default:
            // dispatch(fetchIFiles())
        }
    }

    const handleSearch = () => {
        if (search.length > 0)
            switch (typeData) {
                case "post":
                    dispatch(searchBook(search))
                    break
                default:
                // dispatch(searchFile(search))
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
                        labelId="reference-book-simple-select-standard-label"
                        id="reference-book-simple-select-standard"
                        value={data}
                        label="Age"
                        variant="standard"
                        onChange={handleChangeSelect}
                        sx={{color: 'text.secondary', width: '10rem'}}
                    >
                        <MenuItem value="all">
                            <em>all</em>
                        </MenuItem>
                        {typeData === "music" &&
                            <MenuItem value="favorites">
                                <em>favorites</em>
                            </MenuItem>}
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