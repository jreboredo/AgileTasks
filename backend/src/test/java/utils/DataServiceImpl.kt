package utils

import Controllers.dao.NotaDAO
import modelo.Nota

class DataServiceImpl {
    var NotaDAO: NotaDAO = NotaDAO()
    fun crearNotasDummy(){
        var nota1 = Nota()
        nota1.setTitulo("Dummy1")
        nota1.setDescripcion("Test de Nota")
        NotaDAO.guardar(nota1)
    }
}