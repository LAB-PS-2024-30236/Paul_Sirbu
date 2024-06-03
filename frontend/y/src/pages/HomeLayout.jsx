import React from 'react'
import { Outlet } from 'react-router-dom'
import { Footer, Header } from '../components/index.js'

const HomeLayout = () => {
  return (
    <>
    <Header />
    <Outlet />
    <Footer />
    </>
  )
}

export default HomeLayout