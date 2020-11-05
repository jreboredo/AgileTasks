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

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const removeNote = id => {
        const newNotes = notes.filter(note => note.id !== id);
        setNotes(newNotes);
    }

    useEffect( () => (
        document.body.style="background-image: var(--img-background-notes)"
     )) 

     useEffect(()=>{
        Api.getNotes()
        .then(response => {setNotes(response.data); console.log(response.data) })
        .catch(error => console.log(error))
    },[]);



    const addNoteIfNotExist = note => {
        let newNotes;

        if(note.id) {
            newNotes = notes.map(n => {
                if (n.id === note.id) {
                    return { ...note }
                }
                return n;
            });
        } else {
            newNotes = [{ ...note, id: notes.length + 1 }, ...notes];
        }
        setNotes(newNotes);
    }

    return (
        <>
            <div className={`body background--notes`}>
            <NavBar />
            <div className="container">
                {notes.map((note) => (<Note key={note.id} note={note} addNoteIfNotExist={addNoteIfNotExist} removeNote={removeNote}/>))}
            </div>
            <div className="btn-add-note">
                <img src={add} alt="add new note" className="icon--add" onClick={handleShow}/>
            </div>
            <ModalNote selectedNote={null} show={show} handleClose={handleClose} addNoteIfNotExist={addNoteIfNotExist}/>
            </div>
        </>
    )
}