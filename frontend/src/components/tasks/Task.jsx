import React, {useState} from 'react';
import './Task.css'
import edit from '../../img/edit.svg'
import remove from '../../img/delete.svg'
import pin from '../../img/pin.png'
// import Modal from "react-bootstrap/Modal";
// import ModalHeader from "react-bootstrap/ModalHeader";
// import {ModalFooter} from "react-bootstrap";


export default function Task({task, editTask,showRemoveTask}){
    const {titulo,descripcion,prioridad} = task;
    const prio = prioridad==="low" ? 2 : prioridad==="med" ? 1 : 0
    const [mostrar, setMostrar] = useState(false);

    return (
        <div className={`task task--${prio} descVisible--${mostrar}`}>
            <img src={pin} className="pin" alt='pin'/>
            <div className="actions">
                <img src={edit} alt="edit" className="icon" onClick={() => editTask(task)}/>
                <img src={remove} alt="edit" className="icon" onClick={() => showRemoveTask(task)}/>
            </div>
            <p className="taskTitle pointer text-truncate" onClick={()=> setMostrar(!mostrar)}>{titulo}</p>
            <p className={`taskDescription visible--${mostrar}`}>{descripcion}</p>
            
            {/* <Modal className="modal-bg" show={mostrar}>
                <ModalHeader><h1 className='taskTitle'>{titulo}</h1></ModalHeader>
                <div>
                    <h3 className='introDesc'>Who I need to do?</h3>
                    <p className='taskDescription'>{descripcion}</p>
                </div>
                <ModalFooter>
                    <button
                        className="btn btn-secondary"
                        onClick={() => setMostrar(false)}
                    >
                        Fine!
                    </button>
                </ModalFooter>
            </Modal> */}

        </div>
    );

}
