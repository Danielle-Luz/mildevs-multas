package br.com.mildevs.Scanner;
import java.util.InputMismatchException;
import java.util.Scanner;

public class lerDados {
  public static String lerString(int tamanhoMaximo, String mensagem) {
    while(true) {
      Scanner scan = new Scanner(System.in);

      System.out.println(mensagem);

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
        System.out.println(mensagem);
        
        double valor = scan.nextDouble();

        return valor;
      } catch (InputMismatchException e) {
        System.out.println("Insira um valor decimal válido");
      }
    }
  }
}
