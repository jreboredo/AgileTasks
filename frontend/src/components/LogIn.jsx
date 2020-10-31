import React, {useState,useEffect} from 'react';
import {Link, useHistory} from 'react-router-dom';
import * as API from "../API";

export default function LogIn() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useHistory();

    useEffect( () => {
        document.body.style="background-image: var(--img-background-home);" + 
                            "background-size: auto;"
    }) 
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
            <form className="card rounded-lg bg-light col-auto m-5 p-3 " onSubmit={handleSubmit}>
                <div className='bg-dark rounded-lg mb-3'>
                    <h1 className='text-light p-3 mx-5 '>Simple, fast, funny. Welcome to
                        <span className='logo' onClick={() => history.push('/')}> Agile Tasks</span></h1>
                </div>
                <h1 className='text-center font-italic font-weight-bold'>Log in!</h1>
                <div>
                    <label htmlFor="username" className='font-weight-bolder'>Username</label>
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
                    <label htmlFor="password" className='font-weight-bolder mt-4'>Password</label>
                    <input type="password"
                           value={password}
                           className="form-control mb-4"
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
