package br.com.mildevs.controller;

import br.com.mildevs.Scanner.lerDados;
import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.model.Condutor;
import br.com.mildevs.model.Multa;
import br.com.mildevs.model.Veiculo;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Menu {
  public static void exibirMenuGenerico() {
    while(true) {
      String texto = "O que você deseja gerenciar?\n1- Condutor\n2- Multas\n3-Veiculos\n4- Sair\n";
  
      int opcao = lerDados.lerIntComLimites(texto, 1, 4);
  
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
        case 4:
          System.exit(0);
      }
    }
  }

  public static void exibirMenuCondutor() {
    String texto = "Escolha uma opção:\n1- Inserir condutor\n2- Consultar condutor\n3- Voltar\n";

    int opcao = lerDados.lerIntComLimites(texto, 1, 3);

    switch(opcao) {
      case 1:
        criarCondutor();
        break;
      case 2:

        break;
      case 3:
        exibirMenuGenerico();
        break;
    }
  }

  public static void exibirMenuMulta() {
    String texto = "Escolha uma opção:\n1- Inserir multa\n2- Consultar multa\n3- Voltar\n";

    int opcao = lerDados.lerIntComLimites(texto, 1, 3);

    switch(opcao) {
      case 1:
        criarMulta();
        break;
      case 2:

        break;
      case 3:
        exibirMenuGenerico();
        break;
    }
  }

  public static void exibirMenuVeiculo() {
    String texto = "Escolha uma opção:\n1- Inserir veículo\n2- Consultar veículo\n3- Voltar\n";

    int opcao = lerDados.lerIntComLimites(texto, 1, 3);

    switch(opcao) {
      case 1:
        criarCondutor();
        break;
      case 2:

        break;
      case 3:
        exibirMenuGenerico();
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


  public static LocalDate lerDataEmissaoCnh() {
    while(true) {
      try {
        int dia = lerDados.lerIntComLimites("Dia de emissão da CNH: ", 1, 31);
        int mes = lerDados.lerIntComLimites("Mês de emissão da CNH: ", 1, 12);
        int ano = lerDados.lerInt("Ano de emissão da CNH: ");

        LocalDate dataEmissao = LocalDate.of(ano, mes, dia);

        return dataEmissao;
      } catch(DateTimeException e) {
        System.out.println("Data inválida");
      }
    }
  }

  public static Condutor criarCondutor() {
    int numeroCnh = lerDados.lerInt("Número da CNH: ");
    LocalDate dataEmissao = lerDataEmissaoCnh();
    
    String orgaoEmissor = lerDados.lerString(10, "Orgão emissor da CNH: ");

    int pontuacao = lerDados.lerInt("Pontuação atual da CNH: ");

    Condutor novoCondutor = new Condutor();

    novoCondutor.setNumeroCnh(numeroCnh);
    novoCondutor.setOrgaoEmissor(orgaoEmissor);
    novoCondutor.setPontuacao(pontuacao);
    novoCondutor.setDataEmissaoCnh(dataEmissao);

    System.out.println("Condutor registrado com sucesso.");

    CondutorDAO.inserirCondutor(novoCondutor);

    return novoCondutor;
  }

  public static void criarMulta() {
    double valor = lerDados.lerDouble("Valor da multa: ");
    int pontuacao = lerDados.lerInt("Pontuação reduzida da CNH: ");

    Veiculo veiculo = null;

    while(veiculo == null) {
      veiculo = buscarVeiculo(false);

      if (veiculo == null) {
        int opcao = lerDados.lerIntComLimites("Deseja cadastrar um veiculo?\n1- Sim\n2- Não\n", 1, 2);

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
        int opcao = lerDados.lerIntComLimites("Deseja cadastrar um condutor?\n1- Sim\n2- Não\n", 1, 2);

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

  public static void main(String[] args) {
    Menu.exibirMenuGenerico();
  }
}
