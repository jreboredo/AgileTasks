package Controllers;

import modelo.Grupo;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class GrupoDAO implements DAO<Grupo> {

    private EntityManager entityManager;

    @Override
    public Optional<Grupo> get(int id) {
        return Optional.ofNullable(entityManager.find(Grupo.class, id));
    }

    @Override
    public List<Grupo> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Grupo e");
        return query.getResultList();
    }

    @Override
    public void save(Grupo grupo) {
        executeInsideTransaction(entityManager1 -> entityManager.persist(grupo));

    }

    @Override
    public void update(Grupo grupo, String[] params, LocalDateTime[] params2, Integer[] params3, Usuario[] params4) {
        grupo.setAdministrador(Objects.requireNonNull(params4[0], "Administrador no puede ser null"));
       // grupo.setIntegrantes(Objects.requireNonNull(params4[1], "integrantes no puede ser null")); en duda

        executeInsideTransaction(entityManager1 -> entityManager.merge(grupo));
    }

    @Override
    public void delete(Grupo grupo) {
        executeInsideTransaction(entityManager1 -> entityManager.remove(grupo));
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
