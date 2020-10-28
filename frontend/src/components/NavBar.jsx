import React  from 'react';
import { useHistory } from 'react-router-dom';

export default function NavBar() {
    const history = useHistory();

    return (
        <>
        <nav className="navbar navbar-expand-lg bg-dark rounded justify-content-between">
            <div className='bg-dark rounded-lg mb-3'>
                <h1 className='text-light p-3 mx-5 '>
                    <span className='logo' onClick={() => history.push('/home')}> Agile Tasks</span></h1>
            </div>
            <div>
                <h3 className='text-light pointer' onClick={() => history.push('/')}> Log out</h3>
            </div>
        </nav>
        </>
    );
}
