import { FC } from "react"
import { Route, Routes } from "react-router-dom"
import { Books } from "./container/Books"
import { Header } from "./container/Header/Header"

export const AppRoute: FC = () => {

    return (
        <>
            <Header />
            <main>
                <Routes>
                    <Route path="/" element={<Books />} />
                    {/* <Route path="/file" element={<Book />} /> */}
                    <Route path="*" element={<Books />} />
                </Routes>
            </main>
        </>
    )
}