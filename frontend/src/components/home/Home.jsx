import React, { useEffect } from 'react'
import './Home.css'
import { Figure } from 'react-bootstrap'
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min'
import Notes from '../../img/Notes.jpg'
import Tasks from '../../img/Tasks.jpg'
import NavBar from '../NavBar'

export default function Home() {
    const history = useHistory();
    const images = [Tasks, Notes]

    useEffect(() => {
        document.body.style = "background-image: var(--img-background-home);" +
            "background-size: 145rem;"
    })


    return (
        <>
            <NavBar background={''} showTitle={false} />
            <div className="homeContainer">
                <h1 className="titleHome">¡Welcome to Agile Tasks!</h1>
                {['Notes', 'Tasks'].map((text) => (
                    <Figure>
                        <div className="figureContainer">
                            <Figure.Image
                                width={180}
                                height={180}
                                alt="180x180"
                                src={images.pop()}
                                onClick={() => history.push('/' + text.toLowerCase())}
                                className="figure"
                            />
                        </div>
                        <Figure.Caption>
                            <h2 className="figureTitle">{text}</h2>
                        </Figure.Caption>
                    </Figure>
                ))}
            </div>
        </>
    )
}
