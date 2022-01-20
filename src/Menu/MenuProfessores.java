package Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import Entidades.Professor;
import CRUD.CRUD;
import CRUD.Banco;

public class MenuProfessores extends Menu {
    private CRUD<Professor> crud = new CRUD<>(Banco.professores);

    @Override
    void inserir() {
        System.out.println("Dados do Professor");

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Titulação: ");
        String titulacao = input.next();

        resultado(
            crud.inserir(new Professor(nome, titulacao)),
            "Um novo professor cadastrado com sucesso!",
            "Opss... Falha no cadastro do professor."
        );
    }

    @Override
    void listar() {
        System.out.println("Listagem dos Professores");
        ArrayList<Professor> professores = crud.listar();
        System.out.println("| ID | Nome | Titulação | Curso |");

        for (Professor p : professores) System.out.println(p);
    }

    @Override
    void alterar() {
        System.out.println("Alteração de Professor");
        System.out.print("Informe o ID do professor: ");
        try {
            int id = input.nextInt();
            Professor professor = crud.listar(id);

            if (professor != null) {
                System.out.println("Qual atributo deseja alterar?");
                System.out.print(
                    "[1] Nome \n" +
                    "[2] Titulação \n" +
                    "Opção: "
                );

                switch (input.next()){
                    case "1":
                        System.out.print("Novo nome: ");
                        professor.setNome(input.next());
                        showUpdates(professor);
                        break;
                    case "2":
                        System.out.print("Nova titulação: ");
                        professor.setTitulacao(input.next());
                        showUpdates(professor);
                        break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } else System.out.println("Professor com ID "+ id +" não encontrado");
        } catch (InputMismatchException e) {
            System.out.println("Informe um ID válido.");
        }
    }

    @Override
    void deletar() {
        System.out.println("Remoção de Professor");
        System.out.print("Informe o ID do professor: ");
        try {
            int id = input.nextInt();
            Professor professor = crud.listar(id);

            if (professor.getCurso() == null) {
                resultado(
                    crud.deletar(professor),
                    "Professor "+ id +" removido com sucesso!",
                    "Opss... Falha na remoção do professor."
                );
            } else System.out.println(
                "Este professor dá aula no curso "+ professor.getCurso().getNome() +
                " Altere o professor deste curso antes de excluir este professor."
            );
        } catch (InputMismatchException e){
            System.out.println("Informe um ID válido.");
        } catch (NullPointerException e){
            System.out.println("Professor não encontrado.");
        }
    }

    void showUpdates(Professor professor){
        resultado(
                crud.alterar(professor),
                "Professor alterado com sucesso!",
                "Opss... Falha na alteração do professor."
        );
    }
}
