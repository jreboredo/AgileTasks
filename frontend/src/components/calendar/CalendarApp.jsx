import React, { useState, useEffect } from 'react';
import Paper from '@material-ui/core/Paper';
import { ViewState } from '@devexpress/dx-react-scheduler';
import './Calendar.css'
import * as Api from '../ApiRest'
import {
  Scheduler,
  MonthView,
  Appointments,
} from '@devexpress/dx-react-scheduler-material-ui';
import { Modal, Button } from "react-bootstrap";


export default function CalendarApp({ show, close }) {
  const currentDate = new Date();
  const [tasks, setTasks] = useState([]);

  function changeTasksFormatAndSet(dataTasks) {
    let newTasks = [];
    dataTasks.map(task => {
      const { titulo, comienzo, fin } = task;
      newTasks.push({
        title: titulo,
        startDate: new Date(comienzo),
        endDate: new Date(fin)
      })
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
    <Modal show={show} onHide={() => close()} size='lg' onEntered={() => tasksApi()}>
      <Modal.Header closeButton>
        <Modal.Title>My Calender</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Paper>
          <Scheduler
            data={tasks}
            height={500}
          >
            <ViewState
              defaultCurrentDate={currentDate}
            />
            <MonthView />
            <Appointments />
          </Scheduler>
        </Paper>
      </Modal.Body>
      <Modal.Footer>
        <p className="modalFooterText">
          Today is {currentDate.toJSON().slice(8, 10) +
            '/' + currentDate.toJSON().slice(5, 7) +
            '/' + currentDate.toJSON().slice(0, 4)}
        </p>
        <Button variant="secondary" onClick={() => close()}>
          Close
      </Button>
      </Modal.Footer>
    </Modal>
  );
}
