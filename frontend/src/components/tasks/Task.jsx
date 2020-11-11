import React, {useState} from 'react';
import './Task.css'
import edit from '../../img/edit.svg'
import remove from '../../img/delete.svg'
import pin from '../../img/pin.png'
import Modal from "react-bootstrap/Modal";
import ModalHeader from "react-bootstrap/ModalHeader";
import {ModalFooter} from "react-bootstrap";


export default function Task({task, editTask,showRemoveTask}){
    const {titulo,descripcion,prioridad, comienzo, fin} = task;
    const [mostrar, setMostrar] = useState(false);

    return (
        <div className={`task task--${prioridad}`}>
            <img src={pin} className="pin" alt='pin'/>
            <div className="actions">
                <img src={edit} alt="edit" className="icon" onClick={() => editTask(task)}/>
                <img src={remove} alt="edit" className="icon" onClick={() => showRemoveTask(task)}/>
            </div>
            <p className="taskTitle pointer text-truncate" onClick={()=> setMostrar(!mostrar)}>{titulo}</p>

            <Modal className="modal-bg" show={mostrar}>
                <ModalHeader><h1 className='taskTitle'>{titulo}</h1></ModalHeader>
                <div className='px-3'>
                    <h3>- Prioridad: {prioridad===0 ? "Alta!" : prioridad===1 ? "Normal" : "Baja"}</h3>
                    <h3>- ¿Qué tengo que hacer?</h3>
                    <p className='taskDescription'>{descripcion}</p>
                    <h3>- Tiempos</h3>
                    <h5> + Desde: {comienzo.toString().replace("T", ", ") + "hs"}</h5>
                    <h5> + Hasta: {fin.toString().replace("T", ", ") + "hs"}</h5>
                </div>
                <ModalFooter>
                    <button
                        className="btn btn-secondary"
                        onClick={() => setMostrar(false)}
                    >
                        Fine!
                    </button>
                </ModalFooter>
            </Modal>

        </div>
    );

}
