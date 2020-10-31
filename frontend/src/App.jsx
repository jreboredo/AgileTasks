import './App.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import React from 'react';
import NotesView from './components/NotesView'
import Home  from './components/Home'
import LogIn from "./components/LogIn";
import RegisterForm from "./RegisterForm";
import RegistrationSuccessful from "./components/RegistrationSuccessful";
import NotFound from "./components/NotFound";
import CalendarApp from './components/CalendarApp'
import TasksView from './components/TasksView';

export default function App() {

  return (
    <div className= "home">
      <BrowserRouter>
        <Switch>
            <Route exact path="/" component={LogIn}/>
            <Route path="/home" component={Home}/>
            <Route path="/register" component={RegisterForm}/>
            <Route path="/notes" component={NotesView}/>
            <Route path="/tasks" component={TasksView}/>
            <Route path="/calender" component={CalendarApp}/>
            <Route path="/calender"/>
            <Route path='/successful/:username' component={RegistrationSuccessful} />
            <Route path="*" component={NotFound} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}


