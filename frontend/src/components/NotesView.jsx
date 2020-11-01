import Note from './Note'
import React, { useState,useEffect } from 'react';
import ModalNote from './ModalNote'
import add from '../img/add.svg'
import NavBar from "./NavBar";
import './Home.css'
import * as Api from './ApiRest'


export default function App() {
    const [notes,setNotes] = useState([]);
    const [show, setShow] = useState(false);
    const [selectedNote,setSelectedNote] = useState(null)

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const removeNote = id => {
        Api.deleteNote(id)
        .then(() => window.location.reload())
        .catch( error => console.log(error))
    }

    useEffect( () => (
        document.body.style="background-image: var(--img-background-notes)"
     )) 

     useEffect(()=>{
        Api.getNotes()
        .then(response => setNotes(response.data))
        .catch(error => console.log(error))
    },[]);

    const editNote = note => {
        setSelectedNote(note);
        handleShow()
    }

    const addNoteIfNotExist = note => {
        Api.createNote(note.title,note.text,note.color)
        .then(response => setNotes(note => [...note,response.data]))
        .catch(error => console.log(error))
    }


    return (
        <>
            <div className={`body background--notes`}>
            <NavBar />
            <div className="container">
                {notes.map((note) => (<Note key={note.id} note={note} editNote={() => editNote(note)} removeNote={removeNote}/>))}
            </div>
            <div className="btn-add-note">
                <img src={add} alt="add new note" className="icon--add" onClick={handleShow}/>
            </div>
            <ModalNote selectedNote={selectedNote} show={show} handleClose={handleClose} addNoteIfNotExist={addNoteIfNotExist}/>
            </div>
        </>
    )
}