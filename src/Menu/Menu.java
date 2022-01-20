package Menu;

import java.util.Scanner;

abstract class Menu {
    protected Scanner input = new Scanner(System.in);

    public void start(){
        String op = show();
        if (op.equals("1")) inserir();
        else if (op.equals("2")) listar();
        else if (op.equals("3")) alterar();
        else if (op.equals("4")) deletar();
        else System.out.println("Opção inválida. Tente novamente.");
    }

    public String show(){
        System.out.print(
            "\n--- MENU --- \n" +
            "[1] Inserir \n" +
            "[2] Listar \n" +
            "[3] Alterar \n" +
            "[4] Deletar \n" +
            "Opção: "
        );
        String op = new Scanner(System.in).next();
        System.out.println();

        return op;
    }

    void resultado(boolean condition, String txtTrue, String txtFalse){
        System.out.println(
            condition
            ? txtTrue
            : txtFalse
        );
    }

    abstract void inserir();
    abstract void listar();
    abstract void alterar();
    abstract void deletar();
}
