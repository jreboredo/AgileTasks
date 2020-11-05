import Task from './Task'
import React, {useState, useEffect} from 'react';
import add from '../../img/add.svg'
import NavBar from "../NavBar";
import '../home/Home.css'
import ModalNewTask from './modal/ModalNewTask';
import * as Api from '../ApiRest'




export default function TasksView(){
    const [tasks, setTasks] = useState([]);
    const [selectedTask, setSelectedTask] = useState(null)
    const [modalAgregar, setModalAgregar] = useState(false);
    const [modalEditar, setModalEditar] = useState(false);
    const [modalEliminar, setModalEliminar] = useState(false)

    // function tasksApi() {
    //     Api.getTasks()
    //         .then(response => {
    //             const newTasks = response.data
    //             newTasks.reverse()
    //             setTasks(newTasks)
    //         })
    //         .catch(error => console.log(error))
    // }

    // function showRemoveTask(note) {
    //     setSelectedTask(note)
    //     setModalEliminar(true)
    // }

    // function showEditTask(note) {
    //     setSelectedTask(note)
    //     setModalEditar(true)
    // }

    // const removeTask = () => {
    //     Api.deleteTask(selectedTask.id)
    //         .then(() => tasksApi())
    //         .catch(error => console.log(error))
    //     closeModalEliminar()
    // }

    useEffect(() => {
        document.body.style = "background-image: var(--img-background-notes)"
        // notesApi();
    }, []);

    const addTask = task => {
        console.log(task)
        let newTasks = tasks 
        newTasks.push(task)
        newTasks.reverse()
        setTasks(newTasks)
        // Api.createTask(task.titulo,task.descripcion,task.prioridad,task.inicio,task.fin)
        //     .then(() => console.log("Todo bien"))
        //     .catch(error => console.log(error))
        closeModalInsertar()
    }

    // const editNote = task => {
    //     Api.modifyNote(selectedTask.id, task.titulo, task.descripcion, task.prioridad,task.inicio,task.fin)
    //         .then(() => notesApi())
    //         .catch(e => console.log(e))
    //     closeModalEditar()
    // }

    function closeModalInsertar() {
        setSelectedTask(undefined)
        setModalAgregar(false)
    }

    // function closeModalEliminar() {
    //     setSelectedTask(undefined)
    //     setModalEliminar(false)
    // }

    // function closeModalEditar() {
    //     setModalEditar(false)
    //     setSelectedTask(undefined)
    // }

    return(
        <>
          <NavBar/>
                <div className="container">
                    {tasks.map((task) => (
                        <Task key={task.id} task={task}/>))}
                </div>
                <div className="btn-add-note pointer" onClick={() => setModalAgregar(true)}>
                    <img src={add} alt="add new note" className="icon--add"/>
                </div>

                <ModalNewTask addTask={addTask} showModalInsertar={modalAgregar}
                           closeModalInsertar={closeModalInsertar}/>
                {/* 
                {selectedTask &&
                <ModalNoteEdit note={selectedTask} editNote={editNote} showModalEditar={modalEditar}
                                closeModalEditar={closeModalEditar}/>}

                <Modal show={modalEliminar}>
                    <ModalBody>
                        Estás Seguro que deseas eliminar la nota <span className="font-italic font-weight-bolder">"{selectedTask && selectedTask.titulo}" ? </span>
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
                </Modal> */}
        </>
    )


}
