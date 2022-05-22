import {FC, useEffect} from "react";
import {
    AppBar,
    Container,
    Dialog,
    Divider,
    Grid,
    IconButton,
    ImageList,
    ImageListItem,
    Toolbar,
    Typography
} from "@mui/material";
import {GhostIcon} from "../../icon/GhostIcon";
import {Close} from "@mui/icons-material";
import {modalSlice} from "../../hook/slice/modal.slice";
import {useDispatch} from "react-redux";
import {IPost} from "../../model/IPost";
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
    post: IPost | null
    statusModal: boolean
}

export const PostDialog: FC<Props> = ({post, statusModal}) => {

    const dispatch = useDispatch()

    useEffect(() => {
        hljs.initHighlighting();
    }, [])

    const handleClose = () => {
        dispatch(modalSlice.actions.closeModalWindow())
    };

    return (
        <Dialog
            fullScreen
            open={statusModal}
            onClose={handleClose}
        >
            <AppBar sx={{position: 'relative'}}>
                <Toolbar>
                    <Typography variant="h6" noWrap component="div" sx={{flexGrow: 1}}>
                        <GhostIcon fontSize="large"/>
                        GHOST
                    </Typography>
                    <IconButton
                        edge="start"
                        color="inherit"
                        onClick={handleClose}
                        aria-label="close"
                    >
                        <Close/>
                    </IconButton>
                </Toolbar>
            </AppBar>

            <Container sx={{pt: 3}}>
                <Grid container spacing={1} sx={{color: 'text.secondary'}}>
                    <Grid item md={12} lg={4} justifyContent="space-around"
                          alignItems="stretch">
                        <ImageList sx={{width: 300, height: 450, border: 1, borderRadius: 1, borderColor: 'secondary'}}
                                   cols={1}>
                            <ImageListItem>
                                <img
                                    src={post?.image}
                                    alt={post?.title}
                                    loading="lazy"
                                />
                            </ImageListItem>
                        </ImageList>
                    </Grid>
                    <Grid item xs={12} sm={8} justifyContent="space-around"
                          alignItems="stretch">
                        <Typography gutterBottom variant="h3" textAlign="center">
                            {post?.title}
                        </Typography>
                    </Grid>
                    <Grid item xs={12} mt={3} justifyContent="space-around"
                          alignItems="stretch">
                        {post &&
                            <Typography gutterBottom color="text.secondary" variant="body1"
                                        dangerouslySetInnerHTML={{__html: post.text}}/>}
                    </Grid>
                    <Divider/>
                    <Grid item xs={12} color="darkkhaki">
                        {`Category: ${post?.genre.name}`}
                    </Grid>
                    <Grid item mb={2} xs={12} color="darkkhaki">
                        {`Author: ${post?.author.firstName} ${post?.author.lastName}`}
                    </Grid>
                </Grid>
            </Container>
        </Dialog>
    )
}