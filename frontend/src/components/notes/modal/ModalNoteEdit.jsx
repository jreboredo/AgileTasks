import React, {useState} from 'react';
import {Modal, Button} from 'react-bootstrap'
import * as methods from './ModalMethods'
import './Modal.css'

export default function ModalNoteEdit({note,editNote, showModalEditar, closeModalEditar}) {

    const [textNote, setText] = useState(note.descrpicion);
    const [titleNote, setTitle] = useState(note.titulo);
    const [colorNote, setColor] = useState(note.color);

    function editarNota() {
        editNote({
            title: titleNote,
            text: textNote,
            color: colorNote
        })
        methods.clearFields(setText,setTitle,setColor)
    }

    return (
        <>
            <Modal 
                show={showModalEditar}
                keyboard={false} 
                onHide={closeModalEditar} 
                backdrop="static" 
                centered
                onExited={() => methods.clearFields(setText,setTitle,setColor)}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Edita tu nota!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <div className="form-group">
                            <label className='font-weight-bolder d-block'>Title</label>
                            <input
                                className="form-control"
                                type="text" placeholder="Title"
                                value={titleNote}
                                onChange={(event) => methods.handleTitleChange(event,setTitle)}
                            />
                            <label className='font-weight-bolder d-block'>Content</label>
                            <textarea
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows="3" value={textNote}
                                onChange={(event) => methods.handleContentChange(event,setText)}
                                placeholder="Content"
                            />
                        </div>
                        <div>
                            <label className='font-weight-bolder'>Color</label>
                            {methods.showColors(colorNote, setColor)}
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
