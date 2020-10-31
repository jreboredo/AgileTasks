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
        email : email,
        password : password
    });
}

export const changePassword = async (oldPassword, newPassword) => {
    const endpoint = 'users/modificarUser';
    return axios.post(url + endpoint, {
        userName : localStorage.getItem('userName'),
        oldPassword : oldPassword,
        newPassword : newPassword
    });
}

/* NOTES */
export const getNotes = async () => {
    const endpoint = 'notes/allNotes'
    return axios.get(url + endpoint,
        {
            params: {
                userName: localStorage.getItem('userName')
            }
        });
}

export const createNote = async (titulo, descripcion) =>
    axios.post(url + '/notes/NuevaNota', {
        titulo: titulo,
        descripcion: descripcion,
        userId: localStorage.getItem('id'),
    });

export const modifyNote = async (noteId, titulo, descripcion) =>
    axios.post(url + '/notes/EditarNota', {
        noteId: noteId,
        titulo: titulo,
        descripcion: descripcion,
        userId: localStorage.getItem('id'),
    });

export const deleteNote = async (noteId) => {
    const endpoint = 'notes/deleteNote';
    return axios.delete(url + endpoint,
        {
            params: {
                noteId: noteId
            }
        });
}

