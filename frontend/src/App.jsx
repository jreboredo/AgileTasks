import './App.css';
import mockNotes from './mock-notes.json'
import Note from './components/Note'
import { useState } from 'react';
import ModalNote from './components/ModalNote'
import add from './img/add.svg'


export default function App() {
  const [notes,setNotes] = useState(mockNotes);
  const [show, setShow] = useState(false);
  const [selectedNote, setSelectedNote] = useState(null);

  const handleClose = () => { setShow(false); setSelectedNote(null)};
  const handleShow = () => setShow(true);

  const removeNote = id => {
    const newNotes = notes.filter(note => note.id !== id);
    setNotes(newNotes);
  }

  const editNote = note => {
     setSelectedNote(note);
     handleShow();
  }

  const addNoteIfNotExist = note => {
    let newNotes;

    if(note.id != undefined) {
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
      {notes.map(note => <Note key={note.id} note={note} addNoteIfNotExist={addNoteIfNotExist} removeNote={removeNote}/>)}
    </div>
    <div className="btn-add-note">
      <img src={add} alt="add new note" className="icon--add" onClick={handleShow}/>
    </div>
    <ModalNote selectedNote={selectedNote} show={show} handleClose={handleClose} addNoteIfNotExist={addNoteIfNotExist}/>
  </>
  )
}

