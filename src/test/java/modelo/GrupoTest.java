package modelo;

import Elementos.Ing.AgileTasks.modelo.Grupo;
import Elementos.Ing.AgileTasks.modelo.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrupoTest {

    Grupo grupo = new Grupo();
    Usuario usuario = new Usuario();

    @Test
    public void grupoTest(){
        grupo.setAdministrador(usuario);
        grupo.setIntegrantes(Collections.emptyList());

        assertTrue(grupo.getIntegrantes().isEmpty());
        assertEquals(grupo.getAdministrador(), usuario);
    }
}
