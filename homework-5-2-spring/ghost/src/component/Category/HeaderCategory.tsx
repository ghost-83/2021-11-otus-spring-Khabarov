import {ChangeEvent, FC, useState} from "react";
import {Divider, Grid, IconButton, MenuItem, Select, SelectChangeEvent, TextField} from "@mui/material";
import {AddCircle, Search} from "@mui/icons-material";
import {useDispatch} from "react-redux";
import {useAppSelector} from "../../hook/redux";
import {modalSlice, ModalType} from "../../hook/slice/modal.slice";
import {fetchPostCategory, fetchPosts, searchPost} from "../../hook/creator/book.creator";

interface Props {
    categories: string[] | null
    typeData: "post" | "book" | "file" | "movie" | "music"
}

export const HeaderCategory: FC<Props> = ({categories, typeData}) => {

    const [data, setData] = useState('all');
    const [search, setSearch] = useState('');
    const {userAuth} = useAppSelector(state => state.authReducer)
    const dispatch = useDispatch()

    const handleChangeSelect = (event: SelectChangeEvent) => {
        if (event.target.value !== 'all') {
            switch (typeData) {
                case "post":
                    dispatch(fetchPostCategory(event.target.value))
                    break
                default:
                    // dispatch(fetchIFilesCategory(event.target.value))
            }
        } else
            allData()
        setData(event.target.value)
    }

    const handleOpenCreate = () => {
        dispatch(modalSlice.actions.openModalWindow(ModalType.CREATE))
    }

    const allData = () => {

        switch (typeData) {
            case "post":
                dispatch(fetchPosts())
                break
            default:
                // dispatch(fetchIFiles())
        }
    }

    const handleSearch = () => {
        if (search.length > 0)
            switch (typeData) {
                case "post":
                    dispatch(searchPost(search))
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
                <Grid item sx={{width: '3rem', ml: 10}}>
                    {userAuth && userAuth.authorities.find(e => e.authority === "ADMIN") && (typeData === "book" || typeData === "file" || typeData === "music") &&
                        <IconButton
                            onClick={handleOpenCreate}
                            edge="start"
                            color="info"
                            aria-label="create">
                            <AddCircle/>
                        </IconButton>}
                </Grid>
            </Grid>
            <Divider sx={{mt: 1, mb: 2}}/>
        </>
    )
}