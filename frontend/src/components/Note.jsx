import React from 'react'
import './Note.css'

export default function Note({ note}) {
    const { title,id,text, color } = note;
    return (
      <div className={`note note--${color}`}>
        <h3>{title}</h3>
        <p className="text">{text}</p>
      </div>
    );
  }
