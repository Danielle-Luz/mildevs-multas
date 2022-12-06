package br.com.mildevs.controller;

import br.com.mildevs.Scanner.lerDados;

public class Menu {
  public static void exibirMenuGenerico() {
    String textoMenuGenerico = "O que você deseja gerenciar?\n1- Condutor\n2- Multas\n 3-Veiculos";

    int opcao = lerDados.lerIntComLimites(textoMenuGenerico, 1, 3);

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
    String textoMenuGenerico = "Escolha uma opção:\n1- Inserir condutor\n2- Consultar condutor\n 3- Exibir veículos\n4- Remover condutor\n5- Atualizar condutor";

    int opcao = lerDados.lerIntComLimites(textoMenuGenerico, 1, 5);

    switch(opcao) {
      case 1:

        break;
      case 2:

        break;
      case 3:

        break;
    }
  }
}
