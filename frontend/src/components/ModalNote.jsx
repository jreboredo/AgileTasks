import React, { useState} from 'react';
 import { Modal,Button } from 'react-bootstrap'
 
 export default function ModalNote({selectedNote,show,handleClose,addNoteIfNotExist}){
      let note = { 
          color: selectedNote? selectedNote.color : "yellow",
          text: selectedNote? selectedNote.text : "",
          id: selectedNote? selectedNote.id : undefined,
          title: selectedNote? selectedNote.title: ""
      };  
    const [textNote,setText] = useState(note.text);
    const [titleNote,setTitle] = useState(note.title);
    const [colorNote,setColor] = useState(note.color);
    
    const handleContentChange = event => setText(event.target.value);
    const handleTitleChange = event => setTitle(event.target.value);

      const clearFieldsAndClose = () => {
        setText("");
        setTitle("");
        setColor("");
        handleClose();
      }

    const addNote = () => {
        addNoteIfNotExist({id:note.id,title:titleNote,text:textNote,color:colorNote});
        clearFieldsAndClose()
    }

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <form>
              <div class="form-group">
                <label>Title</label>
                <input 
                  class="form-control" 
                  type="text" placeholder="Title" 
                  value={titleNote} 
                  onChange={handleTitleChange}
                  />
                <label for="exampleFormControlTextarea1">Content</label>
                <textarea 
                class="form-control" 
                id="exampleFormControlTextarea1" 
                rows="3" value={textNote} 
                onChange={handleContentChange}
                placeholder="Content"
                />
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect1">Colors</label>
                <select class="form-control" id="exampleFormControlSelect1">
                  <option onClick={() => setColor('yellow')}>Yellow</option>
                  <option onClick={() => setColor('pink')}>Pink</option>
                  <option onClick={() => setColor('green')}>Green</option>
                  <option onClick={() => setColor('orange')}>Orange</option>
                </select>
              </div>
        </form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => clearFieldsAndClose()}>
            Close
          </Button>
          <Button variant="primary" onClick={addNote}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}