package Menu;

import CRUD.CRUD;
import CRUD.Banco;
import Entidades.Estudante;
import Entidades.Historico;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuHistoricos extends Menu{
    private CRUD<Historico> crud = new CRUD<>(Banco.historicos);

    @Override
    void inserir() {
        System.out.println("Dados do Histórico");
        try {
            System.out.print("Informe a matrícula do Estudante: ");
            int matricula = input.nextInt();

            int[] notas = new int[4];
            System.out.print("Nota 1: ");
            notas[0] = input.nextInt();

            System.out.print("Nota 2: ");
            notas[1] = input.nextInt();

            System.out.print("Nota 3: ");
            notas[2] = input.nextInt();

            System.out.print("Nota 4: ");
            notas[3] = input.nextInt();

            System.out.print("Frequência: ");
            int frequencia = input.nextInt();

            Estudante estudante = null;
            for (Estudante e : new CRUD<>(Banco.estudantes).listar())
                if (e.getMatricula() == matricula) estudante = e;

            if (estudante != null){
                if (estudante.getHistorico() == null) {
                    Historico historico = new Historico(notas, frequencia, estudante);
                    estudante.setHistorico(historico);

                    resultado(
                            crud.inserir(historico),
                            "Um novo histórico cadastrado com sucesso!",
                            "Opss... Falha no cadastro do histórico."
                    );
                } else System.out.println("Este estudante já possui um histórico vinculado.");
            } else System.out.println("Histórico não encontrado.");

        } catch (InputMismatchException e) {
            System.out.println("Informe um número inteiro.");
        }
    }

    @Override
    void listar() {
        System.out.println("Listagem dos Históricos");
        ArrayList<Historico> historicos = crud.listar();
        System.out.println("| ID | Média | Frequência | Estudante |");

        for (Historico h : historicos) System.out.println(h);
    }

    @Override
    void alterar() {
        System.out.println("Alteração de Histórico");
        System.out.print("Informe o ID do histórico: ");
        try {
            int id = input.nextInt();
            Historico historico = crud.listar(id);

            if (historico != null) {
                System.out.println("Qual atributo deseja alterar?");
                System.out.print(
                        "[1] Nota 1 \n" +
                        "[2] Nota 2 \n" +
                        "[3] Nota 3 \n" +
                        "[4] Nota 4 \n" +
                        "[5] Frequência \n" +
                        "[6] Estudante \n" +
                        "Opção: "
                );

                String op = input.next();
                switch (op){
                    case "1": case "2": case "3": case "4":
                        System.out.printf("Nova nota %s: ", op);
                        int idx = Integer.parseInt(op) -1;
                        historico.setNota(idx, input.nextInt());
                        showUpdates(historico);
                        break;
                    case "5":
                        System.out.print("Nova frequência: ");
                        historico.setFrequencia(input.nextInt());
                        showUpdates(historico);
                        break;
                    case "6":
                        System.out.print("Matrícula do novo estudante: ");
                        try {
                            int matricula = input.nextInt();
                            Estudante estudante = null;
                            for (Estudante e : new CRUD<>(Banco.estudantes).listar())
                                if (e.getMatricula() == matricula) estudante = e;

                            if (estudante != null) {
                                historico.getEstudante().setHistorico(null);
                                historico.setEstudante(estudante);
                                estudante.setHistorico(historico);
                                showUpdates(historico);
                            }
                            else System.out.println("Estudante não encontrado.");
                        } catch (InputMismatchException e){
                            System.out.println("Informe um ID válido.");
                        }
                        break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } else System.out.println("Histórico com ID "+ id +" não encontrado");
        } catch (InputMismatchException e) {
            System.out.println("Informe um valor numérico.");
        }
    }

    @Override
    void deletar() {
        System.out.println("Remoção de Histórico");
        System.out.print("Informe o ID do histórico: ");
        try {
            int id = input.nextInt();
            Historico historico = crud.listar(id);

            historico.getEstudante().setHistorico(null);
            boolean rm_historico = crud.deletar(historico);

            resultado(
                    rm_historico,
                    "Histórico com ID "+ id +" removido com sucesso!",
                    "Opss... Falha na remoção do Histórico."
            );
        } catch (InputMismatchException e){
            System.out.println("Informe um ID válido.");
        } catch (NullPointerException e){
            System.out.println("Histórico não encontrado.");
        }
    }

    void showUpdates(Historico historico){
        resultado(
                crud.alterar(historico),
                "Histórico alterado com sucesso!",
                "Opss... Falha na alteração do Histórico."
        );
    }
}
