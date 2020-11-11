import axios from 'axios';

const url = 'http://localhost:8181/'

/* USER */
export const login = async (userName, password) => {
    const endpoint = 'users/validateUser'
    return axios.post(url + endpoint, {
        userName: userName,
        password: password
    })
}

export const register = async (userName, email, password) => {
    const endpoint = 'users/addUser';
    return axios.post(url + endpoint, {
        userName: userName,
        email: email,
        password: password
    });
}

export const changePassword = async (newPassword) => {
    const endpoint = 'users/modificarUser';
    const username = localStorage.getItem('userName')
    return axios.put(url + endpoint + `/${username}`,
        {
            id: localStorage.getItem('userid'),
            userName: username,
            password: newPassword,
            email: localStorage.getItem('email'),

        });
}

/* TASK */
export const getTasks = async () => {
    const endpoint = 'tasks/getByUser/'
    return axios.get(url + endpoint + localStorage.getItem('userid'));
}

export const createTask = async (titulo, descripcion, prioridad, inicio, fin) =>
    axios.post(url + 'tasks/NuevaTarea', {
        titulo: titulo,
        descripcion: descripcion,
        comienzo: inicio,
        fin: fin,
        prioridad: prioridad,
        vencimiento: fin,
        user: {
            userName: localStorage.getItem('userName'),
        }
    });

export const modifyTask = async (tareaId, titulo, descripcion, prioridad, inicio, fin) =>
    axios.put(url + 'tasks/editById/' + tareaId, {
        id: tareaId,
        titulo: titulo,
        descripcion: descripcion,
        comienzo: inicio,
        fin: fin,
        prioridad: prioridad,
        vencimiento: fin,
        user: {
            userName: localStorage.getItem('userName'),
        }
    });


export const deleteTask = async (taskId) => {
    const endpoint = 'tasks/DeleteTarea/';
    return axios.delete(url + endpoint + taskId);
}


/* NOTES */
export const getNotes = async () => {
    const endpoint = 'notes/getByUser/'
    return axios.get(url + endpoint + localStorage.getItem('userid'));
}

export const createNote = async (titulo, descripcion, color) =>
    axios.post(url + '/notes/NuevaNota', {
        titulo: titulo,
        descripcion: descripcion,
        color: color,
        user: {
            id: localStorage.getItem('userid'),
            userName: localStorage.getItem('userName'),
            password: localStorage.getItem('password')
        }
    });

export const modifyNote = async (noteId, titulo, descripcion, color) =>
    axios.put(url + '/notes/EditarNota/' + noteId, {
        noteId: noteId,
        titulo: titulo,
        descripcion: descripcion,
        color: color,
        user: {
            id: localStorage.getItem('userid'),
            userName: localStorage.getItem('userName'),
            password: localStorage.getItem('password')
        }
    });

export const deleteNote = async (noteId) => {
    const endpoint = 'notes/DeleteNota/';
    return axios.delete(url + endpoint + noteId);
}