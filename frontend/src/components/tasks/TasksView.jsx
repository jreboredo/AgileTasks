import Task from './Task'
import React, { useState, useEffect} from 'react';
import add from '../../img/add.svg'
import calendar from '../../img/calendario.svg'
import './TaskView.css'
import NavBar from "../NavBar";
import '../home/Home.css'
import { Modal, ModalBody, ModalFooter } from "react-bootstrap";
import ModalNewTask from './modal/ModalNewTask';
import ModalEditTask from './modal/ModalEditTask';
import * as Api from '../ApiRest'
import CalendarApp from '../calendar/CalendarApp';

export default function TasksView() {
    const [tasks, setTasks] = useState([]);
    const [selectedTask, setSelectedTask] = useState(null)
    const [modalAgregar, setModalAgregar] = useState(false);
    const [modalEditar, setModalEditar] = useState(false);
    const [modalEliminar, setModalEliminar] = useState(false);
    const [showModalCalender,setShowModalCalender] = useState(false);

    useEffect(() => {
        document.body.style = "background-image: var(--img-background-notes)"
        tasksApi();
    }, []);

    function tasksApi() {
        Api.getTasks()
            .then(response => {
                const newTasks = response.data
                newTasks.reverse()
                newTasks.sort(function (prev, next) {return prev.prioridad - next.prioridad})
                setTasks(newTasks)
            })
            .catch(error => console.log(error))
    }

    function showCalender() {
        setShowModalCalender(true)
    }

    function closeModalCalender() {
        setShowModalCalender(false)
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
        Api.createTask(task.titulo, task.descripcion, task.prioridad, task.inicio, task.fin)
            .then(() => {
                tasksApi();
                const descripcion = task.descripcion || 'no has provisto una descripción!'
                const prioridad = task.prioridad===0 ? "Alta!" : task.prioridad===1 ? "Normal" : "Baja"
                const inicio = `${task.inicio.slice(8, 10)}/${task.inicio.slice(5, 7)}/${task.inicio.slice(0, 4)}, ${task.inicio.slice(11)}hs.`;
                const fin = `${task.fin.slice(8, 10)}/${task.fin.slice(5, 7)}/${task.fin.slice(0, 4)}, ${task.fin.slice(11)}hs.`;

                const asunto = "Has creado una nueva tarea!"
                const texto = `Estimado ${localStorage.getItem('userName')}:
                Este es un correo para notificarte acerca de una nueva tarea creada con la siguiente informacion:
                - Titulo: ${task.titulo}
                - Prioridad: ${prioridad}
                - Descripcion: ${descripcion}
                - Fecha de inicio: ${inicio}
                - Fecha de fin: ${fin}`

                Api.sendMail(asunto, texto)
                    .then(r => console.log(r.status))
                    .catch(e => console.log(e));
            })
            .catch(error => console.log(error))
        closeModalInsertar()
    }

    const editTask = task => {
        Api.modifyTask(task.id, task.titulo, task.descripcion, task.prioridad, task.inicio, task.fin, task.isCompletada)
            .then(() => tasksApi())
            .catch(error => console.log(error))
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
            <NavBar background={'bg-dark'} showTitle={true}/>
            <div className="container">
                {tasks.map((task) => (
                    <Task key={task.id} task={task} editTask={showEditTask} actualizar={editTask} showRemoveTask={showRemoveTask} />))}
            </div>
            <div className="btn-add-note pointer" onClick={() => setModalAgregar(true)}>
                <img src={add} alt="add new note" className="icon--add" />
            </div>
            <div className="btn-calendar-task" onClick={() => showCalender()}>
                <img src={calendar} alt="calendar" className="icon--calendar" />
            </div>

            <ModalNewTask addTask={addTask} showModalInsertar={modalAgregar}
                closeModalInsertar={closeModalInsertar} />

            {selectedTask &&
                <ModalEditTask task={selectedTask} editTask={editTask} showModalEditar={modalEditar}
                    closeModalEditar={closeModalEditar} />}
            
            <CalendarApp show={showModalCalender} close={closeModalCalender}/>


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
