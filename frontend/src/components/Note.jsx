import React from 'react'
import './Note.css'
import edit from '../img/edit.svg'
import remove from '../img/delete.svg'
import pin from '../img/pin.png'

export default function Note({note, removeNote, editNote}) {
    const {titulo, color, descrpicion} = note;  // No "descrpicion" no es un error de otrografia, esta asi en el objeto

    return (
        <div className={`note note--${color}`}>
            <img src={pin} className="pin" alt='pin'/>
            <div className="actions">
                <img src={edit} alt="edit" className="icon" onClick={() => editNote(note)}/>
                <img src={remove} alt="edit" className="icon" onClick={() => removeNote(note)}/>
            </div>

            <h3 className="title">{titulo}</h3>
            <p className="text">{descrpicion}</p>
        </div>
    );
}
