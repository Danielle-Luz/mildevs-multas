package br.com.mildevs.dao;

import br.com.mildevs.model.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class VeiculoDAO {
  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mildevs-multas");

  public static void inserirVeiculo (Veiculo novoVeiculo) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();

    entityManager.persist(novoVeiculo);

    entityManager.getTransaction().commit();

    entityManager.close();
  }
}
