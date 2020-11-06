export const handleContentChange = (event,setText) => setText(event.target.value);

export const handleTitleChange = (event,setTitle) => setTitle(event.target.value);

export const clearFields = (title,text,priority,begin,end) => {
    title("");
    text("");
    priority("low");
    begin('');
    end('')
}

export const isAPriority = (prio1,prio2) => {
    return prio1 === prio2
}
