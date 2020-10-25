package Controllers;

import modelo.Tarea;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class TareaDAO implements DAO<Tarea> {

    private EntityManager entityManager;

    @Override
    public Optional<Tarea> get(int id) {
        return Optional.ofNullable(entityManager.find(Tarea.class, id));
    }

    @Override
    public List<Tarea> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Tarea e");
        return query.getResultList();
    }

    @Override
    public void save(Tarea tarea) {
        executeInsideTransaction(entityManager1 -> entityManager.persist(tarea));
    }

    @Override
    public void update(Tarea tarea, String[] params, LocalDateTime[] params2, Integer[] params3) {
        tarea.setTitulo(Objects.requireNonNull(params[0], "Titulo no puede ser null"));
        tarea.setDescripcion(Objects.requireNonNull(params[1], "Descripcion no puede ser null"));
        tarea.setComienzo(Objects.requireNonNull(params2[0], "Comienzo no puede ser null"));
        tarea.setFin(Objects.requireNonNull(params2[1], "Fin no puede ser null"));
        tarea.setPrioridad(Objects.requireNonNull(params3[0], "Prioridad no puede ser nulo"));
        executeInsideTransaction(entityManager1 -> entityManager.merge(tarea));
    }

    @Override
    public void delete(Tarea tarea) {
        executeInsideTransaction(entityManager1 -> entityManager.remove(tarea));
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
