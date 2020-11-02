import check from "../../../img/check.svg";
import React from "react";

export function showColors(colorNote, setColor) {
    return(
        <div className="colors">
            {colors.map(c => (
                <div key={c} className={`color color--${c} pointer`} onClick={() => setColor(c)}>
                    <img className={`check ${isAColor(c,colorNote) && 'check--showIcon'}`} src={check} alt="check" />
                </div>
            ))}
        </div>
    )
}

export const colors = ["yellow", "pink", "green", "blue", "orange"]

export const handleContentChange = (event,setText) => setText(event.target.value);

export const handleTitleChange = (event,setTitle) => setTitle(event.target.value);

export  const isAColor = (colorACompare,colorToCompare) => {
    return colorACompare.toLowerCase() === colorToCompare.toLowerCase()
}

export const clearFields = (title,text,color) => {
    title("");
    text("");
    color("yellow");
}
