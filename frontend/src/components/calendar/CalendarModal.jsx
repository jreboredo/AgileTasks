import React from 'react';
import { Modal,Button } from 'react-bootstrap'

 export default function CalendarModal({show,handleClose, date}){

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
        <Modal.Title>New Task</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <p>No hago nada</p>
        {console.log(date)}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => handleClose()}>
            Close
          </Button>
          <Button variant="primary" onClick={() => handleClose()}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
