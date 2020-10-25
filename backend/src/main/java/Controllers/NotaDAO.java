package Controllers;

import modelo.Nota;
import modelo.Usuario;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;


public class NotaDAO implements DAO<Nota> {

    private EntityManager entityManager;


    @Override
    public Optional<Nota> get(int id) {
        return Optional.ofNullable(entityManager.find(Nota.class, id));
    }

    @Override
    public List<Nota> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Nota e");
        return query.getResultList();
    }

    @Override
    public void save(Nota nota) {
        executeInsideTransaction(entityManager1 -> entityManager.persist(nota));
    }

    @Override
    public void update(Nota nota, String[] params, LocalDateTime[] params2, Integer[] params3, Usuario[] params4) {
        public void update(Nota nota, String[] params, LocalDateTime[] params2, Integer[] params3) {
            nota.setTitulo(Objects.requireNonNull(params[0], "Titulo no puede ser null"));
            nota.setDescripcion(Objects.requireNonNull(params[1], "Descripcion no puede ser null"));
            executeInsideTransaction(entityManager -> entityManager.merge(nota));

    }

    @Override
    public void delete(Nota nota) {
        executeInsideTransaction(entityManager1 -> entityManager.remove(nota));
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
