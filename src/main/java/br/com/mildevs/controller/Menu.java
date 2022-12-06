package br.com.mildevs.controller;

import br.com.mildevs.Scanner.lerDados;
import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.model.Condutor;
import br.com.mildevs.model.Multa;
import br.com.mildevs.model.Veiculo;

import java.time.LocalDate;

public class Menu {
  public static void exibirMenuGenerico() {
    String texto = "O que você deseja gerenciar?\n1- Condutor\n2- Multas\n 3-Veiculos";

    int opcao = lerDados.lerIntComLimites(texto, 1, 3);

    switch(opcao) {
      case 1:
        exibirMenuCondutor();
        break;
      case 2:
        exibirMenuMulta();
        break;
      case 3:
        exibirMenuVeiculo();
        break;
    }
  }

  public static void exibirMenuCondutor() {
    String texto = "Escolha uma opção:\n1- Inserir condutor\n2- Consultar condutor";

    int opcao = lerDados.lerIntComLimites(texto, 1, 2);

    switch(opcao) {
      case 1:
        criarCondutor();
        break;
      case 2:

        break;
    }
  }

  public static void exibirMenuMulta() {
    String texto = "Escolha uma opção:\n1- Inserir multa\n2- Consultar multa";

    int opcao = lerDados.lerIntComLimites(texto, 1, 2);

    switch(opcao) {
      case 1:
        criarMulta();
        break;
      case 2:

        break;
    }
  }

  public static void exibirMenuVeiculo() {
    String texto = "Escolha uma opção:\n1- Inserir veículo\n2- Consultar veículo";

    int opcao = lerDados.lerIntComLimites(texto, 1, 2);

    switch(opcao) {
      case 1:
        criarCondutor();
        break;
      case 2:

        break;
    }
  }


  public static Condutor buscarCondutor(boolean buscarEmLoop) {
    while(true) {
      int cnh = lerDados.lerInt("Número da CNH: ");

      Condutor condutorEncontrado = CondutorDAO.consultarCondutor(cnh);

      if (condutorEncontrado == null) {
        System.out.println("Condutor não encontrado");

        if (buscarEmLoop) continue;
      }
      
      return condutorEncontrado;
    }
  }

  public static Multa buscarMulta(boolean buscarEmLoop) {
    while(true) {
      int codigoMulta = lerDados.lerInt("Código da multa: ");

      Multa multaEncontrada = MultaDAO.consultarMulta(codigoMulta);

      if (multaEncontrada == null) {
        System.out.println("Multa não encontrada");

        if (buscarEmLoop) continue;
      }
      
      return multaEncontrada;
    }
  }

  public static Veiculo buscarVeiculo(boolean buscarEmLoop) {
    while(true) {
      String placa = lerDados.lerString(7, "Placa do veículo: ");

      Veiculo veiculoEncontrado = VeiculoDAO.consultarVeiculo(placa);

      if (veiculoEncontrado == null) {
        System.out.println("Veículo não encontrado");

        if (buscarEmLoop) continue;
      }
      
      return veiculoEncontrado;
    }
  }


  public static Condutor criarCondutor() {
    int numeroCnh = lerDados.lerInt("Número da CNH: ");

    int dia = lerDados.lerInt("Dia de emissão da CNH: ");
    int mes = lerDados.lerInt("Mês de emissão da CNH: ");
    int ano = lerDados.lerInt("Ano de emissão da CNH: ");

    String orgaoEmissor = lerDados.lerString(10, "Orgão emissor da CNH: ");

    int pontuacao = lerDados.lerInt("Pontuação atual da CNH: ");

    Condutor novoCondutor = new Condutor();

    novoCondutor.setNumeroCnh(numeroCnh);
    novoCondutor.setOrgaoEmissor(orgaoEmissor);
    novoCondutor.setPontuacao(pontuacao);
    novoCondutor.setDataEmissaoCnh(LocalDate.of(ano, mes, dia));

    System.out.println("Condutor registrado com sucesso.");

    return novoCondutor;
  }

  public static void criarMulta() {
    double valor = lerDados.lerDouble("Valor da multa: ");
    int pontuacao = lerDados.lerInt("Pontuação reduzida da CNH: ");

    Veiculo veiculo = null;

    while(veiculo == null) {
      veiculo = buscarVeiculo(false);

      if (veiculo == null) {
        int opcao = lerDados.lerIntComLimites("Deseja cadastrar um veiculo?\n1- Sim\n2- Não", 1, 2);

        if (opcao == 1) {
          veiculo = criarVeiculo();
        }
      }
    }

    Multa novaMulta = new Multa();

    novaMulta.setValor(valor);
    novaMulta.setPontuacao(pontuacao);
    novaMulta.setVeiculoMultado(veiculo);

    veiculo.getCondutor().setPontuacao(veiculo.getCondutor().getPontuacao() - pontuacao);

    veiculo.getMultas().add(novaMulta);

    VeiculoDAO.atualizarVeiculo(veiculo);

    System.out.println("Multa registrada com sucesso.");
  }

  public static Veiculo criarVeiculo() {
    String placa = lerDados.lerString(7, "Placa do veículo: ");
    int ano = lerDados.lerInt("Ano do veículo: ");
    String modelo = lerDados.lerString(30, "Modelo do veículo: ");
    String marca = lerDados.lerString(30, "Marca do veículo: ");

    Condutor condutor = null;

    while(condutor == null) {
      condutor = buscarCondutor(false);

      if (condutor == null) {
        int opcao = lerDados.lerIntComLimites("Deseja cadastrar um condutor?\n1- Sim\n2- Não", 1, 2);

        if (opcao == 1) {
          condutor = criarCondutor();
        }
      }
    }

    Veiculo novoVeiculo = new Veiculo();

    novoVeiculo.setAno(ano);
    novoVeiculo.setCondutor(condutor);
    novoVeiculo.setMarca(marca);
    novoVeiculo.setModelo(modelo);
    novoVeiculo.setPlaca(placa);

    condutor.getVeiculos().add(novoVeiculo);

    CondutorDAO.atualizarCondutor(condutor);

    System.out.println("Veículo registrado com sucesso.");

    return novoVeiculo;
  }
}
