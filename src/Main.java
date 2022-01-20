import Menu.*;
import Relatorios.Interface;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {
            String op = show();
            if (op.equals("1")) new MenuCursos().start();
            else if (op.equals("2")) new MenuProfessores().start();
            else if (op.equals("3")) new MenuEstudantes().start();
            else if (op.equals("4")) new MenuHistoricos().start();
            else if (op.equals("5")) new Interface();
            else if (op.equals("6")) exit();
            else System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public static String show(){
        System.out.print(
            "\n--- MENU --- \n" +
            "[1] Curso \n" +
            "[2] Professor \n" +
            "[3] Estudante \n" +
            "[4] Histórico \n" +
            "[5] Relatórios \n" +
            "[6] Sair \n" +
            "Opção: "
        );
        return new Scanner(System.in).next();
    }

    public static void exit(){
        System.out.println("Saindo....");
        System.exit(0);
    }
}
