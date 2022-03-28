import { FC, useEffect } from "react"
import { Container, NavLink, Row, Table } from "react-bootstrap"
import { BookModal } from "../component/BookModal"
import { TableLine } from "../component/table/TableLine"
import { useAppDispatch, useAppSelector } from "../hook/redux"
import { fetchBook } from "../store/reducer/book.reducer"
import { openModal } from "../store/reducer/modal.reducer"

export const Books: FC = () => {

    const dispatch = useAppDispatch()
    const { authors } = useAppSelector(stage => stage.authorReducer)
    const { genres } = useAppSelector(stage => stage.genreReducer)
    const { books } = useAppSelector(stage => stage.bookReducer)
    const { show } = useAppSelector(stage => stage.modalReducer)


    useEffect(() => {
        dispatch(fetchBook())
    }, [dispatch])

    return (
        <Container>
            <Row className="m-5 position-relative">
                <NavLink className="text-decoration-none position-absolute top-0 start-100" onClick={() => dispatch(openModal(null))}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" className="bi bi-plus-circle-fill" viewBox="0 0 16 16">
                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"></path>
                    </svg>
                </NavLink>
            </Row>
            <Row>
                <Table striped bordered hover variant="dark">
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">name</th>
                            <th scope="col">author</th>
                            <th scope="col">genre</th>
                            <th scope="col">edit</th>
                            <th scope="col">delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {books.length > 0 && books.map(e => <TableLine key={`${e.id}-book`} book={e} />)}
                    </tbody>
                </Table>
            </Row>
            {show && <BookModal authors={authors} genres={genres}/> }
        </Container>
    )
}