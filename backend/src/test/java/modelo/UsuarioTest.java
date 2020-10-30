package modelo;

import Elementos.Ing.AgileTasks.modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest {

    Usuario usuario = new Usuario();

    @Test
    public void usuarioTest(){
        usuario.setPassword("1234");
        usuario.setUserName("admin");

        assertEquals(usuario.getUserName(), "admin");
        assertEquals(usuario.getPassword(), "1234");
    }
}
