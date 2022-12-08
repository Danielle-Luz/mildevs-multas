package br.com.mildevs.dao;

import br.com.mildevs.model.Multa;
import br.com.mildevs.model.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class VeiculoDAO {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mildevs-multas");

    public static void inserirVeiculo(Veiculo novoVeiculo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(novoVeiculo);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static Veiculo consultarVeiculo(String placa) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Veiculo veiculoEncontrado = entityManager.find(Veiculo.class, placa);

        entityManager.getTransaction().commit();

        entityManager.close();

        return veiculoEncontrado;
    }

    public static List<Multa> exibirMultas(String placa) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Veiculo veiculoEncontrado = entityManager.find(Veiculo.class, placa);

        List<Multa> multas = veiculoEncontrado.getMultas();

        multas.size();

        entityManager.getTransaction().commit();

        entityManager.close();

        return multas;
    }

    public static boolean removerVeiculo(String placa) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Veiculo veiculoEncontrado = entityManager.find(Veiculo.class, placa);

        if (veiculoEncontrado == null) {
            return false;
        }

        entityManager.remove(veiculoEncontrado);

        entityManager.getTransaction().commit();

        entityManager.close();

        return true;
    }

    public static void atualizarVeiculo(Veiculo veiculoAtualizado) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(veiculoAtualizado);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static List<Veiculo> exibirTodosVeiculos() {
        
    }
}
