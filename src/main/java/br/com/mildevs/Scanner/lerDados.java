package br.com.mildevs.Scanner;
import java.util.InputMismatchException;
import java.util.Scanner;

public class lerDados {
  public static String lerString(int tamanhoMaximo, String mensagem) {
    while(true) {
      Scanner scan = new Scanner(System.in);

      System.out.print(mensagem);

      String valor = scan.nextLine();

      if (valor.length() <= tamanhoMaximo) {
        return valor;
      }

      System.out.printf("Insira uma string com até %d caracteres\n", tamanhoMaximo);
    }
  }

  public static double lerDouble(String mensagem) {
    while(true) {
      Scanner scan = new Scanner(System.in);

      try {
        System.out.print(mensagem);
        
        double valor = scan.nextDouble();

        return valor;
      } catch (InputMismatchException e) {
        System.out.println("Insira um valor decimal válido");
      }
    }
  }

  public static int lerInt(String mensagem) {
    while(true) {
      Scanner scan = new Scanner(System.in);

      try {
        System.out.print(mensagem);
        
        int valor = scan.nextInt();

        return valor;
      } catch (InputMismatchException e) {
        System.out.println("Insira um valor inteiro válido");
      }
    }
  }

  public static int lerIntComLimites(String mensagem, int minimo, int maximo) {
    while(true) {
      Scanner scan = new Scanner(System.in);

      try {
        System.out.println(mensagem);
        
        int valor = scan.nextInt();

        if (valor < minimo || valor >  maximo) {
          System.out.printf("Valor inválido. Insira um valor entre %d e %d\n", minimo, maximo);

          continue;
        }

        return valor;
      } catch (InputMismatchException e) {
        System.out.println("Insira um valor inteiro válido");
      }
    }
  }
}
