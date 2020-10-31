import React, { useState } from 'react'
import './Note.css'
import edit from '../img/edit.svg'
import remove from '../img/delete.svg'
import ModalNote from './ModalNote';
import pin from '../img/pin.png'

export default function Note({note,removeNote,addNoteIfNotExist}) {
    const { titulo,id,color,descrpicion } = note;  // No "descrpicion" no es un error de otrografia, esta asi en el objeto
    const [show,setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);


    return (
      <div className={`note note--${color}`}>
      <img src={pin} className="pin"/>
        <div className="actions">
          <img src={edit} alt="edit" className="icon" onClick={handleShow}/>
          <img src={remove} alt="edit" className="icon" onClick={() => removeNote(id)}/>
        </div>

        <h3 className="title">{titulo}</h3>
        <p className="text">{descrpicion}</p>
        <ModalNote show={show} handleClose={handleClose} selectedNote={note} addNoteIfNotExist={addNoteIfNotExist}/>
      </div>
    );
  }
