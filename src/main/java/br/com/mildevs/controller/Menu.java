package br.com.mildevs.controller;

import br.com.mildevs.Scanner.lerDados;
import br.com.mildevs.model.Condutor;

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

    return novoCondutor;
  }
}
