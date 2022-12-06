package br.com.mildevs.dao;

import br.com.mildevs.model.Multa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MultaDAO {
  static private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mildevs-multas");

  public static void inserirMulta(Multa novaMulta) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();

    entityManager.persist(novaMulta);

    entityManager.getTransaction().commit();

    entityManager.close();
  }

  public static Multa consultarMulta(int codigoMulta) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();

    Multa multaEncontrada = entityManager.find(Multa.class, codigoMulta);

    entityManager.getTransaction().commit();

    entityManager.close();

    return multaEncontrada;
  }

  public static boolean removerMulta(int codigoMulta) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();

    Multa multaEncontrada = entityManager.find(Multa.class, codigoMulta);

    if (multaEncontrada == null) {
      return false;
    }

    entityManager.remove(multaEncontrada);

    entityManager.getTransaction().commit();

    entityManager.close();

    return true;
  }

  public static void atualizarMulta(Multa multaAtualizada) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();

    entityManager.merge(multaAtualizada);

    entityManager.getTransaction().commit();

    entityManager.close();
  }
}
