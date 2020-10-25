import './App.css';
import mockNotes from './mock-notes.json'
import Note from './components/Note'
import { useState } from 'react';
import ModalNote from './components/ModalNote'


export default function App() {
  const [notes,setNotes] = useState(mockNotes);
  const [show, setShow] = useState(false);
  const [selectedNote, setSelectedNote] = useState(null);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const editNote = note => {
     setSelectedNote(note);
     handleShow();
  }


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
    setSelectedNote(null);
  }

  return (
    <>
    <div className="container">
      {notes.map(note => <Note key={note.id} note={note}/>)}
    </div>
    <div className="btn-add-note">
      <img src="./img/add.svg" alt="add new note" className="icon--add" onClick={handleShow}/>
    </div>
    <ModalNote selectedNote={selectedNote} show={show} handleClose={handleClose} addNoteIfNotExist={addNoteIfNotExist}/>
  </>
  )
}

