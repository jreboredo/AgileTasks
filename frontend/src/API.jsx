let users = [
    {
        username: 'chester',
        password:'123456789'
    },
    {
        username: 'nicooo',
        password:'987654321'
    }
]

export const login = (username, password) =>
    users.some(user => user.username===username && user.password ===password)

export const register = (username, password) =>{
    users = [{ username: username, password: password }, ...users];
}
