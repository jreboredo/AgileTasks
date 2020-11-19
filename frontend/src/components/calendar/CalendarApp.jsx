import React, {useState, useEffect} from 'react';
import Paper from '@material-ui/core/Paper';
import {ViewState} from '@devexpress/dx-react-scheduler';
import './Calendar.css'
import * as Api from '../ApiRest'
import {
    Scheduler,
    MonthView,
    Toolbar,
    Appointments,
} from '@devexpress/dx-react-scheduler-material-ui';
import {useHistory} from "react-router-dom";


export default function CalendarApp() {
    const [mydates, setMyDates] = useState([]);
    const history = useHistory();
    const now = new Date()
    const today = now.toJSON()

    useEffect(() => {
        document.body.style = "background-color: grey";
        Api.getTasks()
            .then(response => {
                const newDates = response.data.map(function (task) {
                    return {
                        title: task.titulo,
                        startDate: task.comienzo,
                        endDate: task.vencimiento,
                    }
                });
                setMyDates(newDates);
            })
            .catch(error => console.log(error))
    }, []);

    return (
        <>
            <nav className={`bg-dark navbar navbar-expand-lg rounded justify-content-between`}>
                <span className="col-2"/>
                <button className='btn btn-outline-light' onClick={() => history.push('/tasks')}>Volver a tareas</button>
                <span
                    className={'text-light font-italic'}>Hoy es {today.slice(8, 10) + '/' + today.slice(5, 7) + '/' + today.slice(0, 4)}</span>
                <div className={` col-3 mb-3`}>
                    <h1 className='text-light p-3 mx-5 '>
                        <span className='logo' onClick={() => history.push('/home')}> Agile Tasks</span></h1>
                </div>
            </nav>
            <Paper>
                <Scheduler data={mydates}>
                    <ViewState currentDate={today}/>
                    <MonthView/>
                    <Toolbar/>
                    <Appointments/>
                </Scheduler>
            </Paper>
        </>
    );
}
