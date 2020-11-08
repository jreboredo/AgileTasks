import React, { useState } from 'react'
import { Calendar } from 'react-nice-dates'
import 'react-nice-dates/build/style.css'
import ModalNewTask from '../tasks/modal/ModalNewTask'
import NavBar from '../NavBar'
import './Calendar.css'


export default function CalendarApp(){
    const [date, setDate] = useState();
    const [show,setShow] = useState(false);
    const [tasks,setTasks] = useState([])

    const handleShow = date =>{
        setShow(true);
        setDate(date);
    }

    const addTask = task => {
        console.log(task)
        let newTasks = tasks.push()
        newTasks = [{ ...task, id: tasks.length + 1 }, ...tasks];
        console.log(newTasks)
        newTasks.reverse()
        setTasks(newTasks)
        // Api.createTask(task.titulo, task.descripcion, task.prioridad, task.inicio, task.fin)
        //     .then(() => tasksApi())
        //     .catch(error => console.log(error))
        handleClose()
    }

    const handleClose = () => setShow(false);
    
    return(
        <> 
        <NavBar/>
        <div className="calendar">
            <Calendar date={date} onDayClick={handleShow}/>
      </div>
        <ModalNewTask addTask={addTask} showModalInsertar={show} closeModalInsertar={handleClose}/>
        </>

    )
}