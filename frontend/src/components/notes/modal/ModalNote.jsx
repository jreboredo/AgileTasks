import React, {useState} from 'react';
import {Modal, Button} from 'react-bootstrap'
import * as methods from './ModalMethods'
import check from '../../../img/check.svg'
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

    const closeAndClean = () => {
        closeModalInsertar()
        methods.clearFields(setText,setTitle,setColor)
    }

    return (
        <>
            <Modal show={showModalInsertar}>
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
                            <div className="colors">
                                {methods.colors.map(c => (
                                    <div key={c} className={`color color--${c}`} onClick={() => setColor(c)}>
                                        <img className={`check ${methods.isAColor(c,colorNote) && 'check--showIcon'}`} src={check} alt="check" />
                                    </div>
                                ))}
                            </div>
                        </div>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={closeAndClean}>
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
