import React, { FC } from 'react'
import { BrowserRouter } from 'react-router-dom'
import { AppRoute } from './routs'

export const App: FC = () => {
  return (
    <div className="App">
      <BrowserRouter>
        <AppRoute />
      </BrowserRouter>
    </div>
  )
}
