import React from 'react';
import './Task.css'
import edit from '../../img/edit.svg'
import remove from '../../img/delete.svg'
import pin from '../../img/pin.png'


export default function Task({task, editTask,showRemoveTask}){
    const {titulo,descripcion,prioridad,comienzo,fin} = task;
    const prio = prioridad==="low" ? 2 : prioridad==="med" ? 1 : 0
    return (
        <div className={`task task--${prio}`}>
            <img src={pin} className="pin" alt='pin'/>
            <div className="actions">
                <img src={edit} alt="edit" className="icon" onClick={() => editTask(task)}/>
                <img src={remove} alt="edit" className="icon" onClick={() => showRemoveTask(task)}/>
            </div>
            <h1 className="title">[TASK]</h1>
            <h3 className="title">{titulo}</h3>
        </div>
    );

}
