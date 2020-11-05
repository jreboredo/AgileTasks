import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap'
import { enGB } from 'date-fns/locale'
import { DateRangePicker, START_DATE, END_DATE } from 'react-nice-dates'
import 'react-nice-dates/build/style.css'

export default function ModalNote({ addTask, showModalInsertar, closeModalInsertar }) {

    const [textTask, setText] = useState("");
    const [titleTask, setTitle] = useState("");
    const [priority, setPriority] = useState('low');
    const [beginDate, setBeginDate] = useState(new Date());
    const [endDate, setEndDate] = useState();

    function agregarTarea() {
       addTask({
            titulo: titleTask,
            descripcion: textTask,
            prioridad: priority,
            inicio: beginDate.toJSON(),
            fin: endDate.toJSON(),
        })
    }

    const handleContentChange = (event) => setText(event.target.value);
    const handleTitleChange = (event) => setTitle(event.target.value);

    return (
        <>
            <Modal show={showModalInsertar}
                keyboard={false}
                onHide={closeModalInsertar}
                backdrop="static"
                centered
            //    onExited={() => methods.clearFields(setText,setTitle,setPrioridad)}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Crea tu nueva tarea!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <div className="form-group">
                            <label className='font-weight-bolder'>Title</label>
                            <input
                                className="form-control"
                                type="text" placeholder="Title"
                                value={titleTask}
                                onChange={(event) => handleTitleChange(event, setTitle)}
                            />
                            <label className='font-weight-bolder'>Content</label>
                            <textarea
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows="3" value={textTask}
                                onChange={(event) => handleContentChange(event, setText)}
                                placeholder="Content"
                            />
                        </div>
                        <Form.Group>
                            <Form.Label as="legend">
                                Priority
                            </Form.Label>
                            {['low', 'med', 'high'].map((prio) => (
                                <Form.Check
                                    type="radio"
                                    label={prio}
                                    inline
                                    name="formHorizontalRadios"
                                    id="formHorizontalRadios1"
                                    onClick={() => setPriority(prio)}
                                    selected={() => priority === prio}
                                />
                            ))}

                        </Form.Group>
                    </form>
                    <div>
                        <Form.Label as="legend">
                            Start-End Dates
                    </Form.Label>
                        <DateRangePicker
                            startDate={beginDate}
                            endDate={endDate}
                            onStartDateChange={setBeginDate}
                            onEndDateChange={setEndDate}
                            minimumDate={new Date()}
                            minimumLength={1}
                            format='dd MMM yyyy'
                            locale={enGB}
                        >
                            {({ startDateInputProps, endDateInputProps, focus }) => (
                                <div className='date-range'>
                                    <input
                                        className={'input' + (focus === START_DATE ? ' -focused' : '')} {...startDateInputProps}
                                        placeholder='Start date'
                                    />
                                    <span className='date-range_arrow' />
                                    <input
                                        className={'input' + (focus === END_DATE ? ' -focused' : '')}{...endDateInputProps}
                                        placeholder='End date'
                                    />
                                </div>
                            )}
                        </DateRangePicker>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={closeModalInsertar}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={agregarTarea}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}
