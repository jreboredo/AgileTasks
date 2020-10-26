import React, { useState } from 'react'
import './Note.css'
import edit from '../img/edit.svg'
import remove from '../img/delete.svg'
import ModalNote from './ModalNote';

export default function Note({note,removeNote,addNoteIfNotExist}) {
    const { title,id,text, color } = note;
    const [show,setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
      <div className={`note note--${color}`}>
         <div className="actions">
          <img src={edit} alt="edit" className="icon" onClick={handleShow}/>
          <img src={remove} alt="edit" className="icon" onClick={() => removeNote(id)}/>
        </div>
        <h3 className="title">{title}</h3>
        <p className="text">{text}</p>
        <ModalNote show={show} handleClose={handleClose} selectedNote={note} addNoteIfNotExist={addNoteIfNotExist}/>
      </div>
    );
  }
