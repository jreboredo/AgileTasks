import React from 'react';
import './Task.css'
import edit from '../../img/edit.svg'
import remove from '../../img/delete.svg'
import pin from '../../img/pin.png'


export default function Task({task, editTask,showRemoveTask}){
    const {titulo,descripcion,prioridad,comienzo,fin} = task;
  
    return (
        <div className={`task task--${prioridad}`}>
            <img src={pin} className="pin" alt='pin'/>
            <div className="actions">
                <img src={edit} alt="edit" className="icon" onClick={() => editTask(task)}/>
                <img src={remove} alt="edit" className="icon" onClick={() => showRemoveTask(task)}/>
            </div>
            <h3 className="title">{titulo}</h3>
            <p className="text">{descripcion}</p>
        </div>
    );

}