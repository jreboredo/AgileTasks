import React, {useState} from 'react';
import {Modal, Button, Form} from 'react-bootstrap';
import 'react-nice-dates/build/style.css';
import * as methods from './ModalMethods';

export default function ModalEditTask({task, editTask, showModalEditar, closeModalEditar}) {

    const [textTask, setText] = useState(task.descripcion);
    const [titleTask, setTitle] = useState(task.titulo);
    const [priority, setPriority] = useState(numToPrio);
    const [beginDate, setBeginDate] = useState(task.comienzo);

    const [endDate, setEndDate] = useState(task.fin);

    function numToPrio() {
        return task.prioridad === 0 ? "high" : task.prioridad === 1 ? "med" : "low"
    }

    function editarNota() {
        const prio = priority === "high" ? 0 : priority === "med" ? 1 : 2
        editTask({
            id: task.id,
            titulo: titleTask,
            descripcion: textTask,
            prioridad: prio,
            inicio: beginDate,
            fin: endDate,
        })
        methods.clearFields(setTitle, setText, setPriority, setBeginDate, setEndDate)
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
                            <Form.Label as='legend'>Title</Form.Label>
                            <Form.Control
                                className="title form-control"
                                type="text" placeholder="Title"
                                value={titleTask}
                                onChange={(event) => methods.handleTitleChange(event, setTitle)}
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label as={'legend'}>Description</Form.Label>
                            <textarea
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows="3" value={textTask}
                                onChange={(event) => methods.handleContentChange(event, setText)}
                                placeholder="Describe your task (optional)"
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label as="legend">
                                Priority
                            </Form.Label>
                            {['low', 'med', 'high'].map((prio) => (
                                <Form.Check
                                    type="radio"
                                    label={prio}
                                    inline
                                    checked={methods.isAPriority(prio, priority)}
                                    name="formHorizontalRadios"
                                    id="formHorizontalRadios1"
                                    onClick={() => setPriority(prio)}
                                    selected={() => priority === prio}
                                />
                            ))}

                        </Form.Group>
                        <div>
                            <Form.Label as="legend">
                                Start Date
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
                                Expire Date
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
                    <Button variant="secondary" onClick={closeModalEditar}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={editarNota}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>

    );
}
