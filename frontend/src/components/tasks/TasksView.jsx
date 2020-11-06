import Task from './Task'
import React, { useState, useEffect } from 'react';
import add from '../../img/add.svg'
import NavBar from "../NavBar";
import '../home/Home.css'
import { Modal, ModalBody, ModalFooter } from "react-bootstrap";
import ModalNewTask from './modal/ModalNewTask';
import ModalEditTask from './modal/ModalEditTask';
import * as Api from '../ApiRest'

export default function TasksView() {
    const [tasks, setTasks] = useState([]);
    const [selectedTask, setSelectedTask] = useState(null)
    const [modalAgregar, setModalAgregar] = useState(false);
    const [modalEditar, setModalEditar] = useState(false);
    const [modalEliminar, setModalEliminar] = useState(false)

    useEffect(() => {
        document.body.style = "background-image: var(--img-background-notes)"
        tasksApi();
    }, []);

    function tasksApi() {
        Api.getTasks()
            .then(response => {
                const newTasks = response.data
                newTasks.reverse()
                setTasks(newTasks)
            })
            .catch(error => console.log(error))
    }

    function showRemoveTask(task) {
        setSelectedTask(task)
        setModalEliminar(true)
    }

    function showEditTask(task) {
        setSelectedTask(task)
        setModalEditar(true)
    }

    const removeTask = () => {
        Api.deleteTask(selectedTask.id)
            .then(() => tasksApi())
            .catch(error => console.log(error))
        closeModalEliminar()
    }


    const addTask = task => {
        console.log(task)
        let newTasks = tasks.push()
        newTasks = [{ ...task, id: tasks.length + 1 }, ...tasks];
        console.log(newTasks)
        newTasks.reverse()
        setTasks(newTasks)
        setSelectedTask(undefined)
        // Api.createTask(task.titulo, task.descripcion, task.prioridad, task.inicio, task.fin)
        //     .then(() => tasksApi())
        //     .catch(error => console.log(error))
        closeModalInsertar()
    }

    const editTask = task => {
        console.log(task);
        let newTasks
        newTasks = tasks.map(t => {
            if (t.id === task.id) {
                return { ...task }
            }
            return t;
        });
        newTasks.reverse()
        setTasks(newTasks)
        // Api.modifyTask(task.id, task.titulo, task.descripcion, task.prioridad, task.inicio, task.fin)
        //     .then(() => tasksApi())
        //     .catch(error => console.log(error))
        closeModalEditar();
    }

    function closeModalInsertar() {
        setSelectedTask(undefined)
        setModalAgregar(false)
    }

    function closeModalEliminar() {
        setSelectedTask(undefined)
        setModalEliminar(false)
    }

    function closeModalEditar() {
        setModalEditar(false)
        setSelectedTask(undefined)
    }

    return (
        <>
            <NavBar />
            <div className="container">
                {tasks.map((task) => (
                    <Task key={task.id} task={task} editTask={showEditTask} showRemoveTask={showRemoveTask} />))}
            </div>
            <div className="btn-add-note pointer" onClick={() => setModalAgregar(true)}>
                <img src={add} alt="add new note" className="icon--add" />
            </div>

            <ModalNewTask addTask={addTask} showModalInsertar={modalAgregar}
                closeModalInsertar={closeModalInsertar} />

            {selectedTask &&
                <ModalEditTask task={selectedTask} editTask={editTask} showModalEditar={modalEditar}
                    closeModalEditar={closeModalEditar} />}


            <Modal show={modalEliminar}>
                <ModalBody>
                    Estás Seguro que deseas eliminar la tarea <span className="font-italic font-weight-bolder">"{selectedTask && selectedTask.titulo}" ? </span>
                </ModalBody>
                <ModalFooter>
                    <button className="btn btn-danger" onClick={removeTask}>
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
        </>
    )


}
