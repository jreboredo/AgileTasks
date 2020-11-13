import React, {useState} from 'react';
import {Modal, Button, Form} from 'react-bootstrap'
import * as methods from './ModalMethods'


export default function ModalNewTask({addTask, showModalInsertar, closeModalInsertar}) {
    const now = new Date()
    const tomorrow = new Date(now)
    tomorrow.setDate(tomorrow.getDate()+1)
    const getNow = now.toJSON().toString().slice(0,16)
    const getTomorrow = tomorrow.toJSON().toString().slice(0,16)

    const [textTask, setText] = useState("");
    const [titleTask, setTitle] = useState("");
    const [priority, setPriority] = useState('baja');
    const [beginDate, setBeginDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [error, setError] = useState('');

    function agregarTarea() {
        if(titleTask===''){
            setError('Titulo requerido!');
            console.log(error)
        }else {
            const prio = priority === "high" ? 0 : priority === "med" ? 1 : 2
            addTask({
                titulo: titleTask,
                descripcion: textTask,
                prioridad: prio,
                inicio: beginDate || getNow,
                fin: endDate || getTomorrow,
            })
            methods.clearFields(setTitle,setText,setPriority,setBeginDate,setEndDate)
        }
    }

    return (
        <>
            <Modal show={showModalInsertar}
                   keyboard={false}
                   onHide={closeModalInsertar}
                   backdrop="static"
                   onExited={() => methods.clearFields(setTitle, setText, setPriority, setBeginDate, setEndDate)}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Crea tu nueva tarea!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <Form.Group>
                            <Form.Label as='legend'>Título</Form.Label>
                            <Form.Control
                                className="form-control"
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
                                    key={prio}
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
                                value={beginDate || getNow}
                                onChange={(ev) => {
                                    setBeginDate(ev.target.value)
                                }}
                            >
                            </Form.Control>
                            <Form.Label as="legend">
                                Fecha de Fin
                            </Form.Label>
                            <Form.Control
                                type='datetime-local'
                                name='start'
                                value={endDate || getTomorrow}
                                onChange={(ev) => {
                                    setEndDate(ev.target.value)
                                }}
                            >
                            </Form.Control>

                        </div>
                    </form>

                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => {
                        setError('');
                        closeModalInsertar()
                    }}>
                        Cerrar
                    </Button>
                    <Button variant="primary" onClick={agregarTarea}>
                        Guardar Cambios
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}
