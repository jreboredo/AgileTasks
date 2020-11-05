import React, {useState} from 'react';
import {Modal, Button} from 'react-bootstrap'
import * as methods from './ModalMethods'
import './Modal.css'

export default function ModalNote({addNote, showModalInsertar, closeModalInsertar}) {

    const [textNote, setText] = useState("");
    const [titleNote, setTitle] = useState("");
    const [colorNote, setColor] = useState("yellow");

    function agregarNota() {
        addNote({
            title: titleNote,
            text: textNote,
            color: colorNote
        })
        methods.clearFields(setText,setTitle,setColor)
    }

    return (
        <>
            <Modal show={showModalInsertar} 
                   keyboard={false} 
                   onHide={closeModalInsertar} 
                   backdrop="static" 
                   centered
                   onExited={() => methods.clearFields(setText,setTitle,setColor)}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Crea tu nueva nota!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <div className="form-group">
                            <label className='font-weight-bolder'>Title</label>
                            <input
                                className="form-control"
                                type="text" placeholder="Title"
                                value={titleNote}
                                onChange={(event) => methods.handleTitleChange(event,setTitle)}
                            />
                            <label className='font-weight-bolder'>Content</label>
                            <textarea
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows="3" value={textNote}
                                onChange={(event) => methods.handleContentChange(event,setText)}
                                placeholder="Content"
                            />
                        </div>
                        <div>
                            <label className='font-weight-bolder d-block'>Color</label>
                            {methods.showColors(colorNote, setColor)}
                        </div>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={closeModalInsertar}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={agregarNota}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}
