package br.com.mildevs.dao;

import br.com.mildevs.model.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CondutorDAO {
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mildevs-multas");

  public static void inserirCondutor (Condutor novoCondutor) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.persist(novoCondutor);

    entityManager.close();
  }
}
