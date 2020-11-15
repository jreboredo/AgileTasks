import React, {useState} from 'react';
import './Task.css'
import edit from '../../img/edit.svg'
import remove from '../../img/delete.svg'
import pin from '../../img/pin.png'
import Modal from "react-bootstrap/Modal";
import ModalHeader from "react-bootstrap/ModalHeader";
import {ModalFooter} from "react-bootstrap";


export default function Task({task, editTask, actualizar, showRemoveTask}){
    const {titulo,descripcion,prioridad, comienzo, fin} = task;
    const [mostrar, setMostrar] = useState(false);
    const [cumplida, setCumplida] = useState(task.completada);
    const [cumplidaInicial, setCumplidaInicial] = useState(task.completada);

    function actualizarTarea(){
        actualizar({
            id: task.id,
            titulo: titulo,
            descripcion: descripcion,
            prioridad: prioridad,
            inicio: comienzo,
            fin: fin,
            isCompletada: cumplida
        })
        setCumplidaInicial(cumplida);
    }

    return (
        <div className={`task task--${prioridad}${cumplidaInicial} `}>
            <img src={pin} className="pin" alt='pin'/>
            <div className="actions">
                <img src={edit} alt="edit" className="icon" onClick={() => editTask(task)}/>
                <img src={remove} alt="edit" className="icon" onClick={() => showRemoveTask(task)}/>
            </div>
            <p className="taskTitle pointer text-truncate" onClick={()=> setMostrar(!mostrar)}>{titulo}</p>

            <Modal className="modal-bg" show={mostrar} onHide={() => setMostrar(false)}>
                    <ModalHeader className={`fondo-${cumplida}`}>

                    <h1 className='taskTitle'>{titulo}</h1>
                    <div className="form-check">
                        <input type="checkbox" className="form-check-input" defaultChecked={cumplida}
                               onChange={()=>{ setCumplida(!cumplida) }}/>
                    </div>
                </ModalHeader>
                <div className='px-3'>
                    <h3>- Prioridad: {prioridad===0 ? "Alta!" : prioridad===1 ? "Normal" : "Baja"}</h3>
                    <h3>- ¿Qué tengo que hacer?</h3>
                    {(descripcion && <p className='taskDescription'>{descripcion}</p>) ||
                    <p className='font-italic'>(no hay una descripción provista)</p>}
                    <h3>- Tiempos</h3>
                    <h5> + Desde: {comienzo.toString().replace("T", ", ") + "hs"}</h5>
                    <h5> + Hasta: {fin.toString().replace("T", ", ") + "hs"}</h5>
                </div>
                <ModalFooter>
                    <button
                        className="btn btn-secondary"
                        onClick={() => {
                            if(cumplidaInicial!==cumplida){
                                actualizarTarea();
                            }
                            setMostrar(false);
                        }}
                    >
                        Genial!
                    </button>
                </ModalFooter>
            </Modal>

        </div>
    );

}
