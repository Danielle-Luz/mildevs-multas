package br.com.mildevs.controller;

import br.com.mildevs.Scanner.lerDados;

public class Menu {
  public static void exibirMenuGenerico() {
    String texto = "O que você deseja gerenciar?\n1- Condutor\n2- Multas\n 3-Veiculos";

    int opcao = lerDados.lerIntComLimites(texto, 1, 3);

    switch(opcao) {
      case 1:
        
        break;
      case 2:
        
        break;
      case 3:
        
        break;
    }
  }

  public static void exibirMenuCondutor() {
    String texto = "Escolha uma opção:\n1- Inserir condutor\n2- Consultar condutor";

    int opcao = lerDados.lerIntComLimites(texto, 1, 2);

    switch(opcao) {
      case 1:

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

        break;
      case 2:

        break;
    }
  }
}
