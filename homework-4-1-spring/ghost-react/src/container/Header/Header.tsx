import {NavLink} from 'react-router-dom'
import logo from '../../logo.svg'
import { FC } from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';

export const Header: FC = () => {

    return (
        <header>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand>
                        <NavLink to='/' className="navbar-brand">
                            <img
                                src={logo}
                                height="30"
                                className="d-inline-block alight-top"
                                alt="logo"
                            />GHOST
                        </NavLink>
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="mr-auto">
                            <NavLink className="nav-link active" aria-current="page" to="/books">Books</NavLink>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header>
    )
}
