import './App.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import React from 'react';
import NotesView from './components/notes/noteView/NotesView'
import Home  from './components/home/Home'
import LogIn from "./components/forms/LogIn";
import RegisterForm from "./components/forms/RegisterForm";
import RegistrationSuccessful from "./components/forms/RegistrationSuccessful";
import NotFound from "./components/NotFound";
import CalendarApp from './components/calendar/CalendarApp'
import TasksView from './components/tasks/TasksView';
import ChangePassword from "./components/forms/ChangePassword";
import ChangeEmail from "./components/forms/ChangeEmail";

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
            <Route path="/changePassword" component={ChangePassword}/>
            <Route path="/changeEmail" component={ChangeEmail}/>
            <Route path="/calender" component={CalendarApp}/>
            <Route path='/successful/:username' component={RegistrationSuccessful} />
            <Route path="*" component={NotFound} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}


