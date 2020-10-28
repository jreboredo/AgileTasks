import React, { useState } from 'react'
import { Calendar } from 'react-nice-dates'
import 'react-nice-dates/build/style.css'
import CalenderModal from './CalendarModal'
import NavBar from './NavBar'
import './Calendar.css'


export default function CalendarApp(){
    const [date, setDate] = useState();
    const [show,setShow] = useState(false);

    const handleShow = date =>{
        setShow(true);
        setDate(date);
    }

    const handleClose = () => setShow(false);
    
    return(
        <> 
        <NavBar/>
        <div className="calendar">
            <Calendar date={date} onDayClick={handleShow}/>
      </div>
        <CalenderModal show={show} handleClose={handleClose} date={date}/>
        </>


    )
}
