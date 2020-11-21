import React, {useState} from 'react';
import {Modal, Button, Form} from 'react-bootstrap';
import * as methods from './ModalMethods';

export default function ModalEditTask({task, editTask, showModalEditar, closeModalEditar}) {

    const [textTask, setText] = useState(task.descripcion);
    const [titleTask, setTitle] = useState(task.titulo);
    const [priority, setPriority] = useState(numToPrio);
    const [beginDate, setBeginDate] = useState(task.comienzo);
    const [endDate, setEndDate] = useState(task.fin);
    const [error, setError] = useState('');


    function numToPrio() {
        return task.prioridad === 0 ? "high" : task.prioridad === 1 ? "med" : "low"
    }

    function editarNota() {
        if(titleTask===''){
            setError('Titulo requerido!');
            console.log(error)
        }else {
            const prio = priority === "high" ? 0 : priority === "med" ? 1 : 2
            editTask(task,{
                id: task.id,
                titulo: titleTask,
                descripcion: textTask,
                prioridad: prio,
                comienzo: beginDate,
                fin: endDate,
            })
            methods.clearFields(setTitle, setText, setPriority, setBeginDate, setEndDate)

        }
    }

    return (
        <>

            <Modal show={showModalEditar}
                   keyboard={false}
                   onHide={closeModalEditar}
                   backdrop="static"
                   onExited={() => methods.clearFields(setTitle, setText, setPriority, setBeginDate, setEndDate)}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Edita tu tarea!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <Form.Group>
                            <Form.Label as='legend'>Título</Form.Label>
                            <Form.Control
                                className="title form-control"
                                type="text" placeholder="Título"
                                value={titleTask}
                                onChange={(event) => {
                                    setError('');
                                    methods.handleTitleChange(event, setTitle)
                                }}
                            />
                        </Form.Group>
                        {error && <small className="font-weight-bolder alert alert-danger">{error}</small>}

                        <Form.Group className={'mt-2'}>
                            <Form.Label as={'legend'}>Descripción</Form.Label>
                            <textarea
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows="3" value={textTask}
                                onChange={(event) => methods.handleContentChange(event, setText)}
                                placeholder="Describe tu tarea (opcional)"
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label as="legend">
                                Prioridad
                            </Form.Label>
                            {['low', 'med', 'high'].map((prio) => (
                                <Form.Check
                                    type="radio"
                                    label={prio}
                                    inline
                                    checked={methods.isAPriority(prio, priority)}
                                    name="formHorizontalRadios"
                                    id="formHorizontalRadios1"
                                    onChange={() => setPriority(prio)}
                                    selected={() => priority === prio}
                                />
                            ))}

                        </Form.Group>
                        <div>
                            <Form.Label as="legend">
                                Fecha de comienzo
                            </Form.Label>
                            <Form.Control
                                type='datetime-local'
                                name='start'
                                value={beginDate}
                                placeholder='Start date'
                                onChange={(ev) => {
                                    setBeginDate(ev.target.value)
                                }}
                            />
                            <Form.Label as="legend">
                                Fecha de Fin
                            </Form.Label>
                            <Form.Control
                                type='datetime-local'
                                name='start'
                                value={endDate}
                                placeholder='Start date'
                                onChange={(ev) => {
                                    setEndDate(ev.target.value)
                                }}
                            />
                        </div>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => {
                        setError('');
                        closeModalEditar()
                    }}>
                        Cerrar
                    </Button>
                    <Button variant="primary" onClick={editarNota}>
                        Guardar Cambios
                    </Button>
                </Modal.Footer>
            </Modal>
        </>

    );
}
