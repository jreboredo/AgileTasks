export const handleContentChange = (event,setText) => setText(event.target.value);

export const handleTitleChange = (event,setTitle) => setTitle(event.target.value);

export const clearFields = (title,text,priority,begin,end) => {
    title("");
    text("");
    priority("low");
    begin();
    end()
}
