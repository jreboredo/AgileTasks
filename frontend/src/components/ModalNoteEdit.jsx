import React, {useState} from 'react';
import {Modal, Button} from 'react-bootstrap'
import * as methods from './ModalMethods'

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

    const closeAndClean = () => {
        closeModalEditar()
        methods.clearFields(setText,setTitle,setColor)
    }


    return (
        <>
            <Modal show={showModalEditar}>
                <Modal.Header closeButton>
                    <Modal.Title>Edita tu nota!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <div className="form-group">
                            <label>Title</label>
                            <input
                                className="form-control"
                                type="text" placeholder="Title"
                                value={titleNote}
                                onChange={(event) => methods.handleTitleChange(event,setTitle)}
                            />
                            <label>Content</label>
                            <textarea
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows="3" value={textNote}
                                onChange={(event) => methods.handleContentChange(event,setText)}
                                placeholder="Content"
                            />
                        </div>
                        <div className="form-group">
                            <label>Colors</label>
                            <select className="form-control" id="exampleFormControlSelect1">
                                {methods.colors.map((color) => (
                                    <option selected={methods.isAColor(color,colorNote)}
                                            onClick={() => {setColor(color.toLowerCase());
                                                            console.log(color)}}>{color}</option>
                                ))}
                            </select>
                        </div>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={closeAndClean}>
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
