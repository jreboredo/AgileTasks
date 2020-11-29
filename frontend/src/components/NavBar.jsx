import React from 'react';
import { useHistory } from 'react-router-dom';
import '../App.css'
import exit from '../img/salida.png'

export default function NavBar({ background, showTitle }) {
    const history = useHistory();

    const logOut = () => {
        localStorage.clear()
        history.push('/')
    };

    const changePassword = () => {
        history.push('/changePassword')
    }

    const changeEmail = () => {
        history.push('/changeEmail')
    }

    return (
        <>
            <nav className={`navbar navbar-expand-lg rounded justify-content-between ${background}`}>
                <span className="col-2" />
                {showTitle && <div className={`${background} col-3 mb-3`}>
                    <h1 className='text-light p-3 mx-5 '>
                        <span className='logo' onClick={() => history.push('/home')}> Agile Tasks</span></h1>
                </div>}
                <div>
                    <button className='btn btn-primary pl-4 pr-4 pt-3 pb-3 m-3' onClick={changeEmail}><span className="textHola">{localStorage.getItem('email')}</span></button>
                    <button className='btn btn-primary pl-4 pr-4 pt-3 pb-3 m-3' onClick={changePassword}><span className="textHola">{localStorage.getItem('userName')}</span></button>
                    <button className='btn btn-danger pl-4 pr-4 pt-3 pb-3 m-3' onClick={logOut}><img src={exit} alt="log out"/></button>
                </div>
            </nav>
        </>
    );
}
