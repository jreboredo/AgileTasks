import React, {useState} from 'react';
import {Modal, Button} from 'react-bootstrap'

export default function ModalNoteEdit({note,editNote, showModalEditar, closeModalEditar}) {

    const colors = ["yellow", "pink", "green", "blue", "orange"]
    const [textNote, setText] = useState(note.descrpicion);
    const [titleNote, setTitle] = useState(note.titulo);
    const [colorNote, setColor] = useState(note.color);

    const handleContentChange = event => setText(event.target.value);
    const handleTitleChange = event => setTitle(event.target.value);

    const clearFields = () => {
        setText("");
        setTitle("");
        setColor("");
    }


    const isAColor = color => {
        return color.toLowerCase() === colorNote.toLowerCase()
    }

    function editarNota() {
        editNote({
            title: titleNote,
            text: textNote,
            color: colorNote
        })
        clearFields()
    }

    return (
        <>
            <Modal show={showModalEditar}>
                <Modal.Header closeButton>
                    <Modal.Title>Edita tu nueva!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form>
                        <div className="form-group">
                            <label>Title</label>
                            <input
                                className="form-control"
                                type="text" placeholder="Title"
                                value={titleNote}
                                onChange={handleTitleChange}
                            />
                            <label>Content</label>
                            <textarea
                                className="form-control"
                                id="exampleFormControlTextarea1"
                                rows="3" value={textNote}
                                onChange={handleContentChange}
                                placeholder="Content"
                            />
                        </div>
                        <div className="form-group">
                            <label>Colors</label>
                            <select className="form-control" id="exampleFormControlSelect1">
                                {colors.map((color) => (
                                    <option selected={isAColor(color)}
                                            onClick={() => setColor(color.toLowerCase())}>{color}</option>
                                ))}
                            </select>
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
