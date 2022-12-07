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
import java.util.List;

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
        exibirMenuCondutorConsulta();
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
        exibirMenuMultaConsulta();
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
        criarVeiculo();
        break;
      case 2:
        exibirMenuVeiculoConsulta();
        break;
      case 3:
        exibirMenuGenerico();
        break;
    }
  }


  public static void exibirMenuCondutorConsulta() {
    Condutor condutorEncontrado = buscarCondutor();

    menu:
    while (true) {
      
      int opcao = lerDados.lerIntComLimites("Escolha uma opção:\n1- Excluir condutor\n2- Atualizar condutor\n3- Ver dados do condutor\n4- Listar veículos\n5- Voltar\n", 1, 5);
  
      switch(opcao) {
        case 1:
          CondutorDAO.removerCondutor(condutorEncontrado.getNumeroCnh());
  
          System.out.println("Condutor removido com sucesso.");
          break;
        case 2:
  
        break;
        case 3:
          System.out.println(condutorEncontrado);
          break;
        case 4:
          List<Veiculo> listaVeiculos = condutorEncontrado.getVeiculos();

          for(Veiculo veiculo : listaVeiculos) {
            System.out.println(veiculo);
          }
        case 5:
          break menu;
      }
    }

    exibirMenuGenerico();
  }

  public static void exibirMenuVeiculoConsulta() {
    Veiculo veiculoEncontrado = buscarVeiculo();

    menu:
    while(true) {
      int opcao = lerDados.lerIntComLimites("Escolha uma opção:\n1- Excluir veículo\n2- Atualizar veículo\n3- Ver dados do veículo\n4- Listar multas do veículo\n5- Voltar\n", 1, 5);
  
      switch(opcao) {
        case 1:
          VeiculoDAO.removerVeiculo(veiculoEncontrado.getPlaca());
  
          System.out.println("Veículo removido com sucesso.");
          break;
        case 2:
  
        break;
        case 3:
          System.out.println(veiculoEncontrado);
          break;
        case 4:
          List<Multa> listaMultas = VeiculoDAO.exibirMultas(veiculoEncontrado.getPlaca());

          for(Multa multa : listaMultas) {
            System.out.println(multa);
          }
        case 5:
          break menu;
      }
      
      exibirMenuGenerico();
    }
  }

  public static void exibirMenuMultaConsulta() {
    Multa multaEncontrada = buscarMulta(true);

    menu:
    while (true) {
      int opcao = lerDados.lerIntComLimites("Escolha uma opção:\n1- Excluir multa\n2- Atualizar multa\n3- Ver dados da multa\n4- Voltar\n", 1, 4);
  
      switch(opcao) {
        case 1:
          MultaDAO.removerMulta(multaEncontrada.getCodigoMulta());
  
          System.out.println("Multa removida com sucesso.");
          break;
        case 2:
  
        break;
        case 3:
          System.out.println(multaEncontrada);
          break;
        case 4:
          break menu;
      }
    }

    exibirMenuGenerico();
  }


  public static Condutor buscarCondutor() {
    Condutor condutor = null;

    while(condutor == null) {
      int cnh = lerDados.lerInt("Número da CNH: ");

      condutor = CondutorDAO.consultarCondutor(cnh);

      if (condutor == null) {
        System.out.println("Condutor não encontrado");

        int opcao = lerDados.lerIntComLimites("Deseja cadastrar um condutor?\n1- Sim\n2- Não\n", 1, 2);

        if (opcao == 1) {
          condutor = criarCondutor();
        }
      }
    }

    return condutor;
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

  public static Veiculo buscarVeiculo() {
    Veiculo veiculo = null;

    while(veiculo == null) {
      String placa = lerDados.lerString(7, "Placa do veículo: ");

      veiculo = VeiculoDAO.consultarVeiculo(placa);

      if (veiculo == null) {
        System.out.println("Veículo não encontrado");

        int opcao = lerDados.lerIntComLimites("Deseja cadastrar um veiculo?\n1- Sim\n2- Não\n", 1, 2);

        if (opcao == 1) {
          veiculo = criarVeiculo();
        }
      }
    }

    return veiculo;
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
    int numeroCnh;

    while (true) {
      numeroCnh = lerDados.lerInt("Número da CNH: ");
      
      Condutor condutorEncontrado = CondutorDAO.consultarCondutor(numeroCnh);

      if(condutorEncontrado == null) {
        break;
      }
      
      int opcao = lerDados.lerIntComLimites("CNH já registrada. Deseja inserir outra CNH ou retornar o encontrado?\n1- Inserir nova CNH\n2- Retornar o encontrado\n", 1, 2);

      if (opcao == 2) return condutorEncontrado;
    }


    LocalDate dataEmissao = lerDataEmissaoCnh();
    
    String orgaoEmissor = lerDados.lerString(10, "Orgão emissor da CNH: ");

    int pontuacao = lerDados.lerInt("Pontuação atual da CNH: ");

    Condutor novoCondutor = new Condutor();

    novoCondutor.setNumeroCnh(numeroCnh);
    novoCondutor.setOrgaoEmissor(orgaoEmissor);
    novoCondutor.setPontuacao(pontuacao);
    novoCondutor.setDataEmissaoCnh(dataEmissao);

    CondutorDAO.inserirCondutor(novoCondutor);
    
    System.out.println("Condutor registrado com sucesso.");

    return novoCondutor;
  }

  public static void criarMulta() {
    double valor = lerDados.lerDouble("Valor da multa: ");
    int pontuacao = lerDados.lerInt("Pontuação reduzida da CNH: ");

    Veiculo veiculo = buscarVeiculo();

    Multa novaMulta = new Multa();

    novaMulta.setValor(valor);
    novaMulta.setPontuacao(pontuacao);
    novaMulta.setVeiculoMultado(veiculo);

    veiculo.getCondutor().setPontuacao(veiculo.getCondutor().getPontuacao() - pontuacao);

    List<Multa> listaMultas = VeiculoDAO.exibirMultas(veiculo.getPlaca());

    listaMultas.add(novaMulta);

    veiculo.setMultas(listaMultas);

    MultaDAO.inserirMulta(novaMulta);

    VeiculoDAO.atualizarVeiculo(veiculo);

    System.out.println("Multa registrada com sucesso.");
  }

  public static Veiculo criarVeiculo() {
    String placa;

    while(true) {
      placa = lerDados.lerString(7, "Placa do veículo: ");

      Veiculo veiculoEncontrado = VeiculoDAO.consultarVeiculo(placa);

      if(veiculoEncontrado == null) {
        break;
      }
      
      int opcao = lerDados.lerIntComLimites("Placa já registrada. Deseja inserir outra placa ou retornar o veículo encontrado?\n1- Inserir nova placa\n2- Retornar o encontrado\n", 1, 2);

      if (opcao == 2) return veiculoEncontrado;
    }


    int ano = lerDados.lerInt("Ano do veículo: ");
    String modelo = lerDados.lerString(30, "Modelo do veículo: ");
    String marca = lerDados.lerString(30, "Marca do veículo: ");

    Condutor condutor = buscarCondutor();

    Veiculo novoVeiculo = new Veiculo();

    novoVeiculo.setAno(ano);
    novoVeiculo.setCondutor(condutor);
    novoVeiculo.setMarca(marca);
    novoVeiculo.setModelo(modelo);
    novoVeiculo.setPlaca(placa);

    List<Veiculo> listaVeiculos = CondutorDAO.exibirVeiculos(condutor.getNumeroCnh());

    listaVeiculos.add(novoVeiculo);

    condutor.setVeiculos(listaVeiculos);

    VeiculoDAO.inserirVeiculo(novoVeiculo);
    
    CondutorDAO.atualizarCondutor(condutor);

    System.out.println("Veículo registrado com sucesso.");

    return novoVeiculo;
  }

  public static void main(String[] args) {
    Menu.exibirMenuGenerico();
  }
}
