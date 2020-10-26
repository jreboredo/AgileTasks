import './App.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import React from 'react';
import Home from './components/Home'

export default function App() {

  return (
    <div className= "home">
      <BrowserRouter>
        <Switch>
            <Route path="/" component={Home}/>
        </Switch>
      </BrowserRouter>
    </div>
  );
}


