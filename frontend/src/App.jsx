import './App.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import React from 'react';
import NotesView from './components/NotesView'
import Home  from './components/Home'

export default function App() {

  return (
    <div className= "home">
      <BrowserRouter>
        <Switch>
            <Route exact path="/" component={Home}/>
            <Route path="/notes" component={NotesView}/>
            <Route path="/tasks"/>
            <Route path="/calender"/>
        </Switch>
      </BrowserRouter>
    </div>
  );
}


