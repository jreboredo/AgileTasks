package Controllers;

import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class UsuarioDAO implements DAO<Usuario> {

    private EntityManager entityManager;


    @Override
    public Optional<Usuario> get(int id) {
        return Optional.ofNullable(entityManager.find(Usuario.class, id));
    }

    @Override
    public List<Usuario> getAll() {
        Query query = entityManager.createQuery("select e from Usuario e");
        return query.getResultList();
    }

    @Override
    public void save(Usuario usuario) {
        executeInsideTransaction(entityManager1 -> entityManager.persist(usuario));
    }

    @Override
    public void update(Usuario usuario, String[] params, LocalDateTime[] params2, Integer[] params3) {
        usuario.setUserName(Objects.requireNonNull(params[0], "UserName no puede ser null"));
        usuario.setPassword(Objects.requireNonNull(params[1], "Password no puede ser null"));
        /*
        Juan, me quedaria agregar el update para las notas. Prefiero verlo con vos que cagarla :D
         */

        executeInsideTransaction(entityManager1 -> entityManager.merge(usuario));
    }

    @Override
    public void delete(Usuario usuario) {
        executeInsideTransaction(entityManager1 -> entityManager.remove(usuario));

    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

}
