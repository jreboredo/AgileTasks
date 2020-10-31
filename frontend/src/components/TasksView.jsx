import React, {useEffect} from 'react'
import NavBar from './NavBar'

export default function TasksView(){
    useEffect( () => (
        document.body.style="background-image: var(--img-background-notes)"
     )) 

    return(
        <>
        <NavBar/>
        </>
    )


}