import React  from 'react';

export default function NavBar() {

    return (
        <>
        <nav className="navbar navbar-expand-sm bg-dark">
            <h1 className = "logo col-1">Agile Tasks</h1>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item text-light active">
                        <a className="col-4 text-warning" href="/"><h3 className="text-capitalize">Notas</h3></a>
                    </li>
                </ul>
            </div>
        </nav>
        </>
    );
}
