import './App.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import React from 'react';
import NotesView from './components/NotesView'
import Home  from './components/Home'
import LogIn from "./components/LogIn";
import CalendarApp from './components/CalendarApp'

export default function App() {

  return (
    <div className= "home">
      <BrowserRouter>
        <Switch>
            <Route exact path="/" component={LogIn}/>
            <Route path="/home" component={Home}/>
            <Route path="/notes" component={NotesView}/>
            <Route path="/tasks"/>
            <Route path="/calender" component={CalendarApp}/>
        </Switch>
      </BrowserRouter>
    </div>
  );
}


