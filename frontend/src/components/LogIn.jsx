import React, {useState} from 'react';
import {Link, useHistory} from 'react-router-dom';
import * as API from "../API";

export default function LogIn() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useHistory();

    const handleSubmit = (ev) => {
        ev.preventDefault();
        if (API.login(username, password)) {
            history.push('/home')
        } else {
            setError('Invalid email or password, please, try again.');
        }
    }

    return (
        <>
            <div className='bg-dark rounded-lg'>
                <h1 className='text-light p-3 mx-5 '>Simple, fast, funny. Welcome to
                    <span className='logo' onClick={() => history.push('/')}> Agile Tasks</span></h1>
            </div>
            <form className="card rounded-lg bg-light col-auto m-5 p-3 " onSubmit={handleSubmit}>
                <h1>Log in!</h1>
                <div>
                    <label htmlFor="username">Username</label>
                    <input type="text"
                           value={username}
                           className="form-control"
                           autoComplete="current-username"
                           placeholder="Username"
                           onChange={(ev) => {
                               setUsername(ev.target.value);
                               setError('')
                           }}/>
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input type="password"
                           value={password}
                           className="form-control"
                           placeholder="Password"
                           autoComplete="current-password"
                           onChange={(ev) => {
                               setPassword(ev.target.value);
                               setError('')
                           }}/>
                </div>
                <Link to='/register'> Unregister Yet?</Link>
                {error && <small className="font-weight-bolder alert alert-danger">{error}</small>}
                <div className="text-center">
                    <button className="btn btn-info m-3" onSubmit={handleSubmit}> Log in!</button>
                </div>
            </form>
        </>
    );
}
