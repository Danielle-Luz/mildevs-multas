package br.com.mildevs.dao;

import br.com.mildevs.model.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CondutorDAO {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mildevs-multas");

    public static void inserirCondutor(Condutor novoCondutor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.persist(novoCondutor);

        entityManager.close();
    }

    public static Condutor consultarCondutor(int numeroCnh) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Condutor condutorEncontrado = entityManager.find(Condutor.class, numeroCnh);

        return condutorEncontrado;
    }

    public static boolean removerCondutor(int numeroCnh) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Condutor condutorEncontrado = consultarCondutor(numeroCnh);

        if (condutorEncontrado == null) {
            return false;
        }

        entityManager.remove(condutorEncontrado);

        return true;
    }
    
    public static void atualizarCondutor (Condutor condutorAtualizado) {
      EntityManager entityManager = entityManagerFactory.createEntityManager();

      entityManager.merge(condutorAtualizado);
    }
}
