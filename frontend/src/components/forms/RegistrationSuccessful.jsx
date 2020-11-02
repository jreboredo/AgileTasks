import React from 'react';
import { useHistory } from 'react-router-dom';

export default function RegisterSuccessful({match}){
    const history = useHistory();
    const {username} = match.params;

    const goToHome= () => history.push('/');
    return (
        <div className="container">
            <div className="container bg-secondary col rounded-lg mt-5 p-2 text-center">
                <h1 className="text-light font-weight-bolder">Registration successful!</h1>
                <h4 className="text-light font-weight-bold">Congratulations, you are now register in Agile Tasks!</h4>
                <h4 className="text-light">Your username to log in is:
                    <span className='font-italic'>{` ${username}`}</span>
                </h4>
                <button className="btn btn-success m-4 font-weight-bolder" onClick={goToHome}>Enjoy!</button>
            </div>
        </div>
    )
}
