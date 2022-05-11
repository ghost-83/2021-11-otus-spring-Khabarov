import {Dispatch, FC, SetStateAction, SyntheticEvent, useEffect} from "react"
import {Accordion, AccordionDetails, AccordionSummary, Grid, IconButton, Typography} from "@mui/material"
import ExpandMoreIcon from "@mui/icons-material/ExpandMore"
import {Delete} from "@mui/icons-material"
import {useDispatch} from "react-redux"
import {useAppSelector} from "../../hook/redux";
import {IReferenceBook} from "../../model/IReferenceBook";
import {deleteReferenceBook} from "../../hook/creator/reference.book.creator";
import 'highlight.js/styles/androidstudio.css'
import hljs from 'highlight.js'
import javascript from 'highlight.js/lib/languages/javascript'
import c from 'highlight.js/lib/languages/c'
import python from 'highlight.js/lib/languages/python'
import java from 'highlight.js/lib/languages/java'
import shell from 'highlight.js/lib/languages/shell'
import sql from 'highlight.js/lib/languages/sql'
import kotlin from 'highlight.js/lib/languages/kotlin'
import dockerfile from 'highlight.js/lib/languages/dockerfile'
import typescript from 'highlight.js/lib/languages/typescript'
import nginx from 'highlight.js/lib/languages/nginx'
import cpp from 'highlight.js/lib/languages/cpp'
import css from 'highlight.js/lib/languages/css'
import json from 'highlight.js/lib/languages/json'
import {IconCategory} from "../Category/IconCategory";

hljs.registerLanguage('javascript', javascript)
hljs.registerLanguage('c', c)
hljs.registerLanguage('python', python)
hljs.registerLanguage('java', java)
hljs.registerLanguage('shell', shell)
hljs.registerLanguage('sql', sql)
hljs.registerLanguage('kotlin', kotlin)
hljs.registerLanguage('dockerfile', dockerfile)
hljs.registerLanguage('typescript', typescript)
hljs.registerLanguage('nginx', nginx)
hljs.registerLanguage('cpp', cpp)
hljs.registerLanguage('css', css)
hljs.registerLanguage('json', json)

interface Props {
    keyData: number
    value: IReferenceBook
    setExpanded: Dispatch<SetStateAction<string | false>>
    expanded: string | false
}

export const ReferenceBookComp: FC<Props> = ({keyData, value, setExpanded, expanded}) => {

    const {userAuth} = useAppSelector(state => state.authReducer)
    const dispatch = useDispatch()

    useEffect(() => {
        hljs.initHighlighting();
    }, [])

    const handleChange =
        (panel: string) => (event: SyntheticEvent, isExpanded: boolean) => {
            setExpanded(isExpanded ? panel : false);
        }

    const handleDelete = () => () => {
        dispatch(deleteReferenceBook(value.id))
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
                <Typography sx={{color: 'text.secondary', width: '5%', flexShrink: 0}}>
                    <IconCategory category={value.genre.name}/>
                </Typography>
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
                {userAuth && userAuth.authorities.find(e => e.authority === "ROLE_ADMIN") &&
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