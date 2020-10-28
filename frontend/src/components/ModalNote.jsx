import React, {useState} from 'react';
 import { Modal,Button } from 'react-bootstrap'

 export default function ModalNote({selectedNote,show,handleClose,addNoteIfNotExist}){
      let note = {
          color: selectedNote? selectedNote.color : "yellow",
          text: selectedNote? selectedNote.text : "",
          id: selectedNote? selectedNote.id : undefined,
          title: selectedNote? selectedNote.title: ""
      };

    const colors = ["Yellow","Pink","Green","Blue","Orange"]
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
    const isAColor = color => {
      return color.toLowerCase() === colorNote.toLowerCase()
    }

  return (
    <>
      <Modal show={show} onHide={clearFieldsAndClose}>
        <Modal.Header closeButton>
        <Modal.Title>{note.title}</Modal.Title>
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
                     <option selected={isAColor(color)}  onClick={() => setColor(color.toLowerCase())}>{color}</option>
                  ))}
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
