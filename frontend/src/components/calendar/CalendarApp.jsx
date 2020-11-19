import React, { useState, useEffect } from 'react';
import Paper from '@material-ui/core/Paper';
import { ViewState } from '@devexpress/dx-react-scheduler';
import './Calendar.css'
import * as Api from '../ApiRest'
import {
  Scheduler,
  WeekView,
  Toolbar,
  DateNavigator,
  Appointments,
  TodayButton,
} from '@devexpress/dx-react-scheduler-material-ui';
import { Modal,Button } from "react-bootstrap";
import { mock } from './mock'


export default function CalendarApp({ show, close }) {
  const [currentDate, setCurrentDate] = useState('2018-06-27')
  const [tasks, setTasks] = useState(mock);

  // useEffect(() => {
  //   document.body.style = "background-image: var(--img-background-notes)"
  //   tasksApi();
  // }, []);

  function changeTasksFormatAndSet(dataTasks) {
    let newTasks = [];
    dataTasks.map(task => {
      const newTask = {
        title: task.titulo,
        startDate: new Date(task.comienzo),
        endDate: new Date(task.fin),
        id: task.id,
        location: 'Room1',
      }
      newTasks.push(newTask);
    })
    setTasks(newTasks)
  }

  function tasksApi() {
    Api.getTasks()
      .then(response => {
        changeTasksFormatAndSet(response.data)
      })
      .catch(error => console.log(error))
  }


  return (
    <Modal show={show} onHide={() => close()}  size='lg'>
      <Modal.Header closeButton>
        <Modal.Title>Modal heading</Modal.Title>
      </Modal.Header>
      <Modal.Body>
          <Paper>
            <Scheduler
              data={tasks}
              height={550}
            >
              <ViewState
                currentDate={currentDate}
                onCurrentDateChange={date => setCurrentDate(date)}
              />
              <WeekView
                startDayHour={9}
                endDayHour={19}
              />
              <Toolbar />
              <DateNavigator />
              <TodayButton />
              <Appointments />
            </Scheduler>
          </Paper>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={() => close()}>
          Close
      </Button>
      </Modal.Footer>
    </Modal>



  );
}
