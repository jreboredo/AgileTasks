import React from 'react';
import {useHistory} from "react-router-dom";

export default function NotFound(){
    const history = useHistory();

    return(
        <div className="">
            <div className='bg-dark rounded-lg mb-3'>
                <h1 className='text-light p-3 mx-5 '> Go to
                    <span className='logo' onClick={() => history.push('/')}> Agile Tasks</span> and log/sign in!</h1>
            </div>
            <h1 className="text-dark font-bolder">404 Not Found.</h1>
        </div>
    )
}
