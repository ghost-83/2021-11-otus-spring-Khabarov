import { FC, useEffect } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { FieldValues, useForm } from "react-hook-form";
import { useAppDispatch, useAppSelector } from "../hook/redux";
import { fetchAuthor } from "../store/reducer/author.reducer";
import { saveBook } from "../store/reducer/book.reducer";
import { fetchGenre } from "../store/reducer/genre.reducer";
import { closeModal } from "../store/reducer/modal.reducer";

export const BookModal: FC = () => {

    const dispatch = useAppDispatch()
    const { authors } = useAppSelector(stage => stage.authorReducer)
    const { genres } = useAppSelector(stage => stage.genreReducer)
    const { book, show } = useAppSelector(stage => stage.modalReducer)

    const { register, handleSubmit } = useForm()

    useEffect(() => {
        dispatch(fetchAuthor())
        dispatch(fetchGenre())
    }, [dispatch])

    const handleClose = () => dispatch(closeModal())

    const onSubmit = (data: FieldValues) => {
        dispatch(saveBook({
            id: Number(data.id),
            name: data.name,
            authorId: !!data.author ? Number(data.author) : 1,
            genreId: !!data.genre ? Number(data.genre) : 1
        }))
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