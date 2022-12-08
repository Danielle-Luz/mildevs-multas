package br.com.mildevs.controller;

import br.com.mildevs.Scanner.lerDados;
import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.model.Condutor;
import br.com.mildevs.model.Multa;
import br.com.mildevs.model.Veiculo;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }

    public static void exibirMenuGenerico() {
        limparTela();

        while (true) {
            String texto = "O que você deseja gerenciar?\n1- Condutor\n2- Multas\n3- Veiculos\n4- Sair\n";

            int opcao = lerDados.lerIntComLimites(texto, 1, 4);

            switch (opcao) {
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
        limparTela();

        String texto =
            "Escolha uma opção:\n1- Inserir condutor\n2- Consultar condutor\n3- Listar todos os condutores\n4- Voltar\n";

        int opcao = lerDados.lerIntComLimites(texto, 1, 3);

        switch (opcao) {
            case 1:
                limparTela();

                criarCondutor();
                break;
            case 2:
                limparTela();

                exibirMenuCondutorConsulta();
                break;
            case 3:
                List<Condutor> condutores = CondutorDAO.exibirTodosCondutores();

                if (condutores.size() == 0) {
                    System.out.println("Não há nenhum condutor registrado.\n");
                } else {
                    System.out.println("--------------------------");

                    for (Condutor condutor : condutores) {
                        System.out.println(condutor);

                        System.out.println("--------------------------");
                    }
                }
                break;
            case 4:
                exibirMenuGenerico();
                break;
        }
    }

    public static void exibirMenuMulta() {
        limparTela();

        menu:while (true) {
            String texto =
                "Escolha uma opção:\n1- Inserir multa\n2- Consultar multa\n3- Exibir todas as multas\n4- Voltar\n";

            int opcao = lerDados.lerIntComLimites(texto, 1, 4);

            switch (opcao) {
                case 1:
                    limparTela();

                    criarMulta();
                    break;
                case 2:
                    limparTela();

                    exibirMenuMultaConsulta();
                    break;
                case 3:
                    List<Multa> todasMultas = MultaDAO.exibirTodasAsMultas();

                    if (todasMultas.size() == 0) {
                        System.out.println("Não há nenhuma multa registrada.\n");
                    } else {
                        System.out.println("--------------------------");

                        for (Multa multa : todasMultas) {
                            System.out.println(multa);

                            System.out.println("--------------------------");
                        }
                    }
                    break;
                case 4:
                    break menu;
            }
        }
        exibirMenuGenerico();
    }

    public static void exibirMenuVeiculo() {
        limparTela();

        String texto =
            "Escolha uma opção:\n1- Inserir veículo\n2- Consultar veículo\n3- Listar todos os veículos\n4- Voltar\n";

        int opcao = lerDados.lerIntComLimites(texto, 1, 3);

        switch (opcao) {
            case 1:
                limparTela();

                criarVeiculo();
                break;
            case 2:
                limparTela();

                exibirMenuVeiculoConsulta();
                break;
            case 3:
                List<Veiculo> veiculos = VeiculoDAO.exibirTodosVeiculos();

                if (veiculos.size() == 0) {
                    System.out.println("Não há nenhum veículo registrado.\n");
                } else {
                    System.out.println("--------------------------");

                    for (Veiculo veiculo : veiculos) {
                        System.out.println(veiculo);

                        System.out.println("--------------------------");
                    }
                }
                break;
            case 4:
                exibirMenuGenerico();
                break;
        }
    }

    public static void exibirMenuCondutorConsulta() {
        Condutor condutorEncontrado = buscarCondutor();

        menu:while (condutorEncontrado != null) {
            int opcao = lerDados.lerIntComLimites(
                "\nEscolha uma opção:\n1- Excluir condutor\n2- Atualizar condutor\n3- Ver dados do condutor\n4- Voltar\n",
                1,
                4
            );

            switch (opcao) {
                case 1:
                    limparTela();

                    CondutorDAO.removerCondutor(condutorEncontrado.getNumeroCnh());

                    condutorEncontrado = null;

                    System.out.println("Condutor removido com sucesso.");
                    break;
                case 2:
                    limparTela();

                    atualizarDadosCondutor(condutorEncontrado);

                    break;
                case 3:
                    limparTela();

                    System.out.println(condutorEncontrado);

                    break;
                case 4:
                    break menu;
            }
        }

        exibirMenuGenerico();
    }

    public static void exibirMenuVeiculoConsulta() {
        Veiculo veiculoEncontrado = buscarVeiculo();

        menu:while (veiculoEncontrado != null) {
            int opcao = lerDados.lerIntComLimites(
                "\nEscolha uma opção:\n1- Excluir veículo\n2- Atualizar veículo\n3- Ver dados do veículo\n4- Listar multas do veículo\n5- Voltar\n",
                1,
                5
            );

            switch (opcao) {
                case 1:
                    limparTela();

                    VeiculoDAO.removerVeiculo(veiculoEncontrado.getPlaca());

                    veiculoEncontrado = null;

                    System.out.println("Veículo removido com sucesso.");
                    break;
                case 2:
                    limparTela();

                    atualizarDadosVeiculo(veiculoEncontrado);

                    break;
                case 3:
                    limparTela();

                    System.out.println(veiculoEncontrado);

                    break;
                case 4:
                    limparTela();

                    List<Multa> listaMultas = VeiculoDAO.exibirMultas(veiculoEncontrado.getPlaca());

                    if (listaMultas.size() == 0) {
                        System.out.println("O veículo não possui nenhuma multa registrada.\n");
                    } else {
                        System.out.println("--------------------------");

                        for (Multa multa : listaMultas) {
                            System.out.println(multa);

                            System.out.println("--------------------------");
                        }
                    }

                    break;
                case 5:
                    break menu;
            }
        }

        exibirMenuGenerico();
    }

    public static void exibirMenuMultaConsulta() {
        Multa multaEncontrada = buscarMulta();

        if (multaEncontrada != null) {
            menu:while (true) {
                int opcao = lerDados.lerIntComLimites(
                    "\nEscolha uma opção:\n1- Excluir multa\n2- Atualizar multa\n3- Ver dados da multa\n4- Exibir todas as multas\n5- Voltar\n",
                    1,
                    5
                );

                switch (opcao) {
                    case 1:
                        limparTela();

                        MultaDAO.removerMulta(multaEncontrada.getCodigoMulta());

                        multaEncontrada = null;

                        System.out.println("Multa removida com sucesso.");
                        break;
                    case 2:
                        limparTela();

                        atualizarDadosMulta(multaEncontrada);

                        break;
                    case 3:
                        limparTela();

                        System.out.println(multaEncontrada);

                        break;
                    case 4:
                        List<Multa> todasMultas = MultaDAO.exibirTodasAsMultas();

                        if (todasMultas.size() == 0) {
                            System.out.println("Não há nenhuma multa registrada.\n");
                        } else {
                            System.out.println("--------------------------");

                            for (Multa multa : todasMultas) {
                                System.out.println(multa);

                                System.out.println("--------------------------");
                            }
                        }

                        break menu;
                    case 5:
                        break menu;
                }
            }
        }

        exibirMenuGenerico();
    }

    public static void atualizarDadosCondutor(Condutor condutor) {
        menu:while (true) {
            int opcao = lerDados.lerIntComLimites(
                "Escolha um dado para atualizar:\n1- Data de emissão da CNH\n2- Orgão emissor da CNH\n3- Pontuação da CNH\n4- Transferir veículo\n5- Voltar\n",
                1,
                5
            );

            String mensagemSucesso = "Dado atualizado com sucesso.";

            switch (opcao) {
                case 1:
                    LocalDate novaData = lerDataEmissaoCnh();

                    condutor.setDataEmissaoCnh(novaData);

                    break;
                case 2:
                    String novoOrgaoEmissor = lerDados.lerString(10, "Novo órgão emissor da CNH: ");

                    condutor.setOrgaoEmissor(novoOrgaoEmissor);

                    break;
                case 3:
                    int pontuacao = lerDados.lerInt("Nova pontuação atual da CNH: ");

                    condutor.setPontuacao(pontuacao);

                    break;
                case 4:
                    boolean veiculoFoiTransferido = transferirVeiculo(condutor);

                    if(!veiculoFoiTransferido) {
                      mensagemSucesso = "";
                    } else {
                      mensagemSucesso = "Veículo transferido com sucesso.";
                    }

                    break;
                case 5:
                    break menu;
            }

            if (opcao == 5) exibirMenuCondutor();

            CondutorDAO.atualizarCondutor(condutor);

            System.out.println(mensagemSucesso);
        }
    }

    public static void atualizarDadosVeiculo(Veiculo veiculo) {
        menu:while (true) {
            int opcao = lerDados.lerIntComLimites(
                "Escolha um dado para atualizar:\n1- Ano do veículo\n2- Modelo\n3- Marca\n4- Voltar\n",
                1,
                4
            );

            switch (opcao) {
                case 1:
                    int ano = lerDados.lerInt("Ano do veículo: ");

                    veiculo.setAno(ano);

                    break;
                case 2:
                    String modelo = lerDados.lerString(30, "Modelo do veículo: ");

                    veiculo.setModelo(modelo);

                    break;
                case 3:
                    String marca = lerDados.lerString(30, "Marca do veículo: ");

                    veiculo.setMarca(marca);

                    break;
                case 4:
                    break menu;
            }

            if (opcao == 4) exibirMenuVeiculo();

            VeiculoDAO.atualizarVeiculo(veiculo);

            System.out.println("Dado atualizado com sucesso.");
        }
    }

    public static void atualizarDadosMulta(Multa multa) {
        menu:while (true) {
            int opcao = lerDados.lerIntComLimites(
                "Escolha um dado para atualizar:\n1- Pontuação removida\n2- Valor\n3- Veiculo multado\n4- Voltar\n",
                1,
                4
            );

            Veiculo veiculoMultado = multa.getVeiculoMultado();
            Condutor condutorMultado = veiculoMultado.getCondutor();

            switch (opcao) {
                case 1:
                    int novaPontuacaoReduzida = lerDados.lerInt("Nova pontuação reduzida da CNH: ");

                    condutorMultado.setPontuacao(condutorMultado.getPontuacao() + multa.getPontuacao());
                    multa.setPontuacao(novaPontuacaoReduzida);
                    condutorMultado.setPontuacao(condutorMultado.getPontuacao() - novaPontuacaoReduzida);

                    CondutorDAO.atualizarCondutor(condutorMultado);

                    break;
                case 2:
                    double novoValor = lerDados.lerDouble("Valor da multa: ");

                    multa.setValor(novoValor);

                    break;
                case 3:
                    Veiculo veiculoEncontrado = buscarVeiculo();
                    Condutor condutorVeiculoEncontrado = veiculoEncontrado.getCondutor();

                    condutorMultado.setPontuacao(condutorMultado.getPontuacao() + multa.getPontuacao());
                    condutorVeiculoEncontrado.setPontuacao(
                        condutorVeiculoEncontrado.getPontuacao() - multa.getPontuacao()
                    );

                    VeiculoDAO.exibirMultas(veiculoMultado.getPlaca()).remove(multa);
                    VeiculoDAO.exibirMultas(veiculoEncontrado.getPlaca()).add(multa);

                    multa.setVeiculoMultado(veiculoEncontrado);

                    CondutorDAO.atualizarCondutor(condutorMultado);
                    CondutorDAO.atualizarCondutor(condutorVeiculoEncontrado);
                    VeiculoDAO.atualizarVeiculo(veiculoMultado);
                    VeiculoDAO.atualizarVeiculo(veiculoEncontrado);

                    break;
                case 4:
                    break menu;
            }

            if (opcao == 4) exibirMenuVeiculo();

            MultaDAO.atualizarMulta(multa);

            System.out.println("Dado atualizado com sucesso.");
        }
    }

    public static boolean transferirVeiculo(Condutor condutorAntigo) {
      if(condutorAntigo.getVeiculo() != null) {
        System.out.println("Insira os dados do comprador.");
        
        Condutor novoCondutor = buscarCondutor();

        Veiculo veiculoTransferido = condutorAntigo.getVeiculo();

        if (novoCondutor.getNumeroCnh() == condutorAntigo.getNumeroCnh()) {
            System.err.println("O novo condutor já é dono do veículo.");

            return false;
        }

        if (novoCondutor.getVeiculo() != null) {
            int opcao = lerDados.lerIntComLimites(
                "O comprador já possui um veículo, caso a compra prossiga, ele será removido.\nDeseja tranferir o veículo do comprador?\n1- Sim\n2- Não\n",
                1,
                2
            );

            if (opcao == 1) {
                transferirVeiculo(novoCondutor);
            } else {
                System.out.println("O veículo antigo do comprador será removido.");

                VeiculoDAO.removerVeiculo(novoCondutor.getVeiculo().getPlaca());
            }
        }

        condutorAntigo.setVeiculo(null);
        novoCondutor.setVeiculo(veiculoTransferido);
        veiculoTransferido.setCondutor(novoCondutor);

        CondutorDAO.atualizarCondutor(condutorAntigo);
        CondutorDAO.atualizarCondutor(novoCondutor);

        VeiculoDAO.atualizarVeiculo(veiculoTransferido);

        return true;
      } else {
        System.out.println("O condutor não possui nenhum veículo para transferir.");

        return false;
      }
    }


    public static Condutor buscarCondutor() {
        Condutor condutor = null;

        while (condutor == null) {
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

    public static Multa buscarMulta() {
        int codigoMulta = lerDados.lerInt("Código da multa: ");

        Multa multaEncontrada = MultaDAO.consultarMulta(codigoMulta);

        if (multaEncontrada == null) {
            System.out.println("Multa não encontrada");
        }

        return multaEncontrada;
    }

    public static Veiculo buscarVeiculo() {
        Veiculo veiculo = null;

        while (veiculo == null) {
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
        while (true) {
            try {
                int dia = lerDados.lerIntComLimites("Dia de emissão da CNH: ", 1, 31);
                int mes = lerDados.lerIntComLimites("Mês de emissão da CNH: ", 1, 12);
                int ano = lerDados.lerInt("Ano de emissão da CNH: ");

                LocalDate dataEmissao = LocalDate.of(ano, mes, dia);

                return dataEmissao;
            } catch (DateTimeException e) {
                System.out.println("Data inválida");
            }
        }
    }

    public static Condutor criarCondutor() {
        int numeroCnh;

        while (true) {
            numeroCnh = lerDados.lerInt("Número da CNH: ");

            Condutor condutorEncontrado = CondutorDAO.consultarCondutor(numeroCnh);

            if (condutorEncontrado == null) {
                break;
            }

            int opcao = lerDados.lerIntComLimites(
                "CNH já registrada. Deseja inserir outra CNH ou retornar o encontrado?\n1- Inserir nova CNH\n2- Retornar o encontrado\n",
                1,
                2
            );

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

        while (true) {
            placa = lerDados.lerString(7, "Placa do veículo: ");

            Veiculo veiculoEncontrado = VeiculoDAO.consultarVeiculo(placa);

            if (veiculoEncontrado == null) {
                break;
            }

            int opcao = lerDados.lerIntComLimites(
                "Placa já registrada. Deseja inserir outra placa ou retornar o veículo encontrado?\n1- Inserir nova placa\n2- Retornar o encontrado\n",
                1,
                2
            );

            if (opcao == 2) return veiculoEncontrado;
        }

        int ano = lerDados.lerInt("Ano do veículo: ");
        String modelo = lerDados.lerString(30, "Modelo do veículo: ");
        String marca = lerDados.lerString(30, "Marca do veículo: ");

        Condutor condutor;

        while (true) {
            condutor = buscarCondutor();

            if (condutor.getVeiculo() != null) {
                System.err.println("O condutor especificado já possui um veículo. Busque outro condutor.");

                continue;
            }

            break;
        }

        Veiculo novoVeiculo = new Veiculo();

        novoVeiculo.setAno(ano);
        novoVeiculo.setCondutor(condutor);
        novoVeiculo.setMarca(marca);
        novoVeiculo.setModelo(modelo);
        novoVeiculo.setPlaca(placa);

        condutor.setVeiculo(novoVeiculo);

        VeiculoDAO.inserirVeiculo(novoVeiculo);

        CondutorDAO.atualizarCondutor(condutor);

        System.out.println("Veículo registrado com sucesso.");

        return novoVeiculo;
    }

    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Menu.exibirMenuGenerico();
    }
}
