import {FC, useEffect, useState} from "react"
import {Box, Card, CardActionArea, CardContent, CardMedia, Grid, Typography} from "@mui/material"
import {useAppSelector} from "../hook/redux"
import {useDispatch} from "react-redux"
import {fetchPosts} from "../hook/creator/post.creator"
import {IPost} from "../model/IPost";
import {modalSlice, ModalType} from "../hook/slice/modal.slice";
import {HeaderCategory} from "../component/Category/HeaderCategory";
import {PostDialog} from "../component/Post/PostDialog";

export const Posts: FC = () => {

    const {categories} = useAppSelector(state => state.categoryReducer)
    const {posts} = useAppSelector(state => state.postReducer)
    const {statusModal, typeModal} = useAppSelector(state => state.modalReducer)
    const [post, setPost] = useState<null | IPost>(null)
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(fetchPosts())
    }, [dispatch])


    const handleOpenPost = (post: IPost) => () => {
        dispatch(modalSlice.actions.openModalWindow(ModalType.READ))
        setPost(post)
    }

    return (
        <Box sx={{flexGrow: 1}}>
            <HeaderCategory categories={categories.map(e => e.name)} typeData="post"/>
            <Grid container spacing={1}>
                {posts && posts.length > 0 && posts.map(data =>
                    <Grid item xs={12} sm={6} md={4} lg={3} xl={2} key={'post' + data.id}>
                        <Card onClick={handleOpenPost(data)} sx={{maxWidth: 300, height: '100%'}}>
                            <CardActionArea>
                                <CardMedia
                                    component="img"
                                    height="140"
                                    image={data.image}
                                    alt="green iguana"
                                />
                                <CardContent>
                                    <Typography gutterBottom variant="h6" component="div" sx={{textAlign: 'center'}}>
                                        {data.title}
                                    </Typography>
                                </CardContent>
                            </CardActionArea>
                        </Card>
                    </Grid>
                )}
            </Grid>
            {statusModal && typeModal === ModalType.READ &&
                <PostDialog post={post} statusModal={statusModal}/>
            }
        </Box>
    )
}