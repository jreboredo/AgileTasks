package Controllers.dao

import modelo.Usuario

class UserDAO: HibernateDAO<Usuario>(Usuario::class.java){
}