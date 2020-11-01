import Note from './Note'
import React, {useState, useEffect} from 'react';
import add from '../img/add.svg'
import NavBar from "./NavBar";
import './Home.css'
import * as Api from './ApiRest'
import {Modal, ModalBody, ModalFooter} from "react-bootstrap";
import ModalNote from "./ModalNote";
import ModalNoteEdit from "./ModalNoteEdit";


export default function NotesView() {
    const [notes, setNotes] = useState([]);
    const [selectedNote, setSelectedNote] = useState(null)
    const [modalAgregar, setModalAgregar] = useState(false);
    const [modalEditar, setModalEditar] = useState(false);
    const [modalEliminar, setModalEliminar] = useState(false)

    function notesApi() {
        Api.getNotes()
            .then(response => {
                const newNotes = response.data
                newNotes.reverse()
                setNotes(newNotes)
            })
            .catch(error => console.log(error))
    }

    function showRemoveNote(note) {
        setSelectedNote(note)
        setModalEliminar(true)
    }

    function showEditNote(note) {
        setSelectedNote(note)
        setModalEditar(true)
    }

    const removeNote = () => {
        Api.deleteNote(selectedNote.id)
            .then(() => notesApi())
            .catch(error => console.log(error))
        closeModalEliminar()
    }

    useEffect(() => {
        document.body.style = "background-image: var(--img-background-notes)"
        notesApi();
    }, []);

    const addNote = note => {
        Api.createNote(note.title, note.text, note.color)
            .then(() => notesApi())
            .catch(error => console.log(error))
        closeModalInsertar()
    }

    const editNote = note => {
        Api.modifyNote(selectedNote.id, note.title, note.text, note.color)
            .then(() => notesApi())
            .catch(e => console.log(e))
        closeModalEditar()
    }

    function closeModalInsertar() {
        setSelectedNote(undefined)
        setModalAgregar(false)
    }

    function closeModalEliminar() {
        setSelectedNote(undefined)
        setModalEliminar(false)
    }

    function closeModalEditar() {
        setModalEditar(false)
        setSelectedNote(undefined)
    }

    return (
        <>
            <div className={`body background--notes`}>
                <NavBar/>
                <div className="container">
                    {notes.map((note) => (
                        <Note key={note.id} note={note} editNote={showEditNote}
                              removeNote={showRemoveNote}/>))}
                </div>
                <div className="btn-add-note pointer" onClick={() => setModalAgregar(true)}>
                    <img src={add} alt="add new note" className="icon--add"/>
                </div>

                <ModalNote addNote={addNote} showModalInsertar={modalAgregar}
                           closeModalInsertar={closeModalInsertar}/>

                {selectedNote &&
                <ModalNoteEdit note={selectedNote} editNote={editNote} showModalEditar={modalEditar}
                                closeModalEditar={closeModalEditar}/>}

                <Modal show={modalEliminar}>
                    <ModalBody>
                        Estás Seguro que deseas eliminar la nota <span className="font-italic font-weight-bolder">"{selectedNote && selectedNote.titulo}" ? </span>
                    </ModalBody>
                    <ModalFooter>
                        <button className="btn btn-danger" onClick={removeNote}>
                            Sí
                        </button>
                        <button
                            className="btn btn-secondary"
                            onClick={closeModalEliminar}
                        >
                            No
                        </button>
                    </ModalFooter>
                </Modal>
            </div>
        </>
    )
}
