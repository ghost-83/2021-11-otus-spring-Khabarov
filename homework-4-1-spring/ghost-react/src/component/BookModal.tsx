import { FC, useEffect } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { FieldValues, useForm } from "react-hook-form";
import { useAppDispatch, useAppSelector } from "../hook/redux";
import { IAuthor } from "../model/IAuthor";
import { IGenre } from "../model/IGenre";
import { fetchAuthor } from "../store/reducer/author.reducer";
import { saveBook } from "../store/reducer/book.reducer";
import { fetchGenre } from "../store/reducer/genre.reducer";
import { closeModal } from "../store/reducer/modal.reducer";
import { v4 as uuidv4 } from "uuid"

interface BookModalProps {
    authors: IAuthor[]
    genres: IGenre[]
}

export const BookModal: FC<BookModalProps> = ({ authors, genres }) => {

    const dispatch = useAppDispatch()
    const { book, show } = useAppSelector(stage => stage.modalReducer)
    const { register, handleSubmit } = useForm()

    useEffect(() => {
        dispatch(fetchAuthor())
        dispatch(fetchGenre())
    }, [dispatch])

    const handleClose = () => dispatch(closeModal())

    const onSubmit = (data: FieldValues) => {
        
        if (!!authors.length && !!genres.length){
            dispatch(saveBook({
                id: !!data.id ? data.id : uuidv4(),
                name: data.name,
                author: !!data.author ? authors.find(e => e.id === data.author)! : authors[0],
                genre: !!data.genre ? genres.find(e => e.id === data.genre)! : genres[0]
            }))}
        dispatch(closeModal())
    }

    return (
        <>
            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
                centered
            >
                <Form onSubmit={handleSubmit(onSubmit)} noValidate>

                    <Modal.Header closeButton>
                        <Modal.Title>Book</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Group className="mb-3">
                            <Form.Label>id</Form.Label>
                            <Form.Control {...register('id')}
                                name="id"
                                readOnly={true}
                                defaultValue={book == null ? "" : book.id} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>name</Form.Label>
                            <Form.Control {...register('name')}
                                name="name"
                                defaultValue={book == null ? "" : book.name} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Author</Form.Label>
                            <Form.Select {...register('author')}
                                name="author"
                                defaultValue={book == null ? "" : book.author!.id}>
                                {authors.length > 0 && authors.map(e => <option key={`${e.id}-author`} value={e.id}>{`${e.firstName} ${e.lastName}`}</option>)}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Genre</Form.Label>
                            <Form.Select {...register('genre')}
                                name="genre"
                                defaultValue={book == null ? "" : book.genre!.id}>
                                {genres.length > 0 && genres.map(e => <option key={`${e.id}-genre`} value={e.id}>{e.name}</option>)}
                            </Form.Select>
                        </Form.Group>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                        <Button variant="primary" type="submit">Save</Button>
                    </Modal.Footer>
                </Form>
            </Modal>
        </>
    )
}