package br.com.mildevs.dao;

import java.time.LocalDate;
import java.util.List;

import br.com.mildevs.exceptions.RegistroJaInseridoException;
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

    public static List<Veiculo> exibirVeiculos(int numeroCnh) {
      EntityManager entityManager = entityManagerFactory.createEntityManager();

      entityManager.getTransaction().begin();

      Condutor condutorEncontrado = entityManager.find(Condutor.class, numeroCnh);

      List<Veiculo> listaCarros = condutorEncontrado.getVeiculos();

      listaCarros.size();

      entityManager.getTransaction().commit();

      entityManager.close();

      return listaCarros;
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

    public static void registrarVeiculo(Condutor condutor, Veiculo veiculo) throws RegistroJaInseridoException {
      boolean veiculoJaInserido = condutor.getVeiculos().contains(veiculo);

      if(veiculoJaInserido){
        throw new RegistroJaInseridoException("O condutor já possui esse veículo");
      }

      condutor.getVeiculos().add(veiculo);

      veiculo.setCondutor(condutor);

      atualizarCondutor(condutor);

      VeiculoDAO.atualizarVeiculo(veiculo);
    }
}
