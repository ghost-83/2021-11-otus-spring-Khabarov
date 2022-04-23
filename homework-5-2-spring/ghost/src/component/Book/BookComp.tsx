import {Dispatch, FC, SetStateAction, SyntheticEvent} from "react"
import {Accordion, AccordionDetails, AccordionSummary, Grid, IconButton, Typography} from "@mui/material"
import ExpandMoreIcon from "@mui/icons-material/ExpandMore"
import {Delete} from "@mui/icons-material"
import {useDispatch} from "react-redux"
import {useAppSelector} from "../../hook/redux";
import {IBook} from "../../model/IBook";
import {deleteBook} from "../../hook/creator/book.creator";

interface Props {
    keyData: number
    value: IBook
    setBookModal: Dispatch<SetStateAction<IBook | null>>
    setExpanded: Dispatch<SetStateAction<string | false>>
    expanded: string | false
}

export const ReferenceBookComp: FC<Props> = ({keyData, value, setBookModal, setExpanded, expanded}) => {

    const {userAuth} = useAppSelector(state => state.authReducer)
    const dispatch = useDispatch()

    const handleChange =
        (panel: string) => (event: SyntheticEvent, isExpanded: boolean) => {
            setExpanded(isExpanded ? panel : false);
        }

    const handleDelete = () => () => {
        dispatch(deleteBook(value.id))
    }

    return (
        <Accordion expanded={expanded === `panel${keyData}`}
                   onChange={handleChange(`panel${keyData}`)} sx={{mb: 1}}>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon/>}
                aria-controls={`panel${keyData}bh-header`}
                id={`panel${keyData}bh-header`}
                sx={{mb: -1}}
            >
                <Typography sx={{color: 'text.secondary', width: '5%'}}>{value.id}</Typography>
                <Typography sx={{color: 'text.secondary', width: '55%'}}>{value.title}</Typography>
                <Typography sx={{
                    color: 'text.secondary',
                    width: '30%'
                }}>{`${value.author.lastName} ${value.author.firstName}`}</Typography>
                <Typography sx={{color: 'text.secondary', width: '10%'}}>{value.genre.name}</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography variant="subtitle1" color="text.secondary" dangerouslySetInnerHTML={{__html: value.text}}/>
                {/*{userAuth && userAuth.authorities.find(e => e.authority === "ROLE_ADMIN") && Кнопка удаления оставлена активной для user стобы можно было проверить работу Spring Security */}
                {userAuth &&
                    <Grid container
                          direction="row"
                          justifyContent="flex-end"
                          alignItems="center">
                        <Grid item>
                            <IconButton edge="start" color="error" onClick={handleDelete()} aria-label="update">
                                <Delete/>
                            </IconButton>
                        </Grid>
                    </Grid>}
            </AccordionDetails>
        </Accordion>
    )
}