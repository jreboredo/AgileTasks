 import React, { useState } from 'react';
 import { Modal,Button,Form } from 'react-bootstrap'
 
 export default function ModalNote({selectedNote,show,handleClose,addNoteIfNotExist}){
    const note = { 
        color: selectedNote ? selectedNote.color : 'yellow',
        text: selectedNote ? selectedNote.text : '',
        id: selectedNote ? selectedNote.id : undefined,
        title: selectedNote ? selectedNote.title: ''
    }
    const [text,setText] = useState(note.text);
    const [title,setTitle] = useState(note.title);
    const [color,setColor] = useState(note.color);

    const addNote = () => {
        addNoteIfNotExist({id:note.id,title:title,text:text,color:color});
        setText(note.text);
        setTitle(note.title);
        setColor(note.color);
        handleClose();
    }

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <Form>
            <Form.Group controlId="fromBasic">
                <Form.Label>Title</Form.Label>
                <Form.Control value={title} type="text" placeholder="Enter title" onChange={event => setTitle(event.target.value)} />
            </Form.Group>
            <Form.Group controlId="exampleForm.ControlTextarea1">
                <Form.Label>Content</Form.Label>
                <Form.Control as="textarea" placeholder="Content" rows={3} type="text" value={text} onChange={event => setText(event.target.value)}/>
            </Form.Group>
            <Form.Group controlId="exampleForm.ControlSelect1">
    <Form.Label>Colors</Form.Label>
    <Form.Control as="select">
      <option onClick={() => setColor('yellow')}>Yellow</option>
      <option onClick={() => setColor('pink')}>Pink</option>
      <option onClick={() => setColor('green')}>Green</option>
      <option onClick={() => setColor('orange')}>Orange</option>
    </Form.Control>
  </Form.Group>
            </Form>
            {/* <div>
            <div className={`textarea__container color--${color}`}>
              <textarea type="text" value="" maxLength="248" rows="7" placeholder="Content"/>
            </div> */}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose} onClick={addNote}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}