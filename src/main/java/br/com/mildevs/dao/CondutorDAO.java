package br.com.mildevs.dao;

import java.time.LocalDate;
import java.util.List;

import br.com.mildevs.model.Condutor;
import br.com.mildevs.model.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CondutorDAO {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mildevs-multas");

    public static void inserirCondutor(Condutor novoCondutor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(novoCondutor);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static Condutor consultarCondutor(int numeroCnh) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Condutor condutorEncontrado = entityManager.find(Condutor.class, numeroCnh);

        entityManager.getTransaction().commit();

        entityManager.close();

        return condutorEncontrado;
    }

    public static boolean removerCondutor(int numeroCnh) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Condutor condutorEncontrado = entityManager.find(Condutor.class, numeroCnh);

        if (condutorEncontrado == null) {
            return false;
        }

        entityManager.remove(condutorEncontrado);

        entityManager.getTransaction().commit();

        entityManager.close();

        return true;
    }

    public static void atualizarCondutor(Condutor condutorAtualizado) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(condutorAtualizado);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static List<Condutor> exibirTodosCondutores() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Condutor> listaCondutores = entityManager.createQuery("SELECT c FROM Condutor c").getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();

        return listaCondutores;
    }
}
