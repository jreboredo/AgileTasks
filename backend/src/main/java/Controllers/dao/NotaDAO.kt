package Controllers.dao

import modelo.Nota

public class NotaDAO  : HibernateDAO<Nota>(Nota::class.java) {

}