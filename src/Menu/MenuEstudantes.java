package Menu;

import CRUD.CRUD;
import CRUD.Banco;
import Entidades.Curso;
import Entidades.Estudante;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuEstudantes extends Menu {
    private CRUD<Estudante> crud = new CRUD<>(Banco.estudantes);

    @Override
    void inserir() {
        System.out.println("Dados do Estudante");

        System.out.print("Nome: ");
        String nome = input.nextLine();
        int matricula = (int) (Math.floor(Math.random() * 100000));

        try {
            System.out.print("Informe o ID do Curso: ");
            int id_curso = input.nextInt();

            Curso curso = new CRUD<>(Banco.cursos).listar(id_curso);

            if (curso != null){
                Estudante estudante = new Estudante(nome, matricula, curso);
                curso.addEstudante(estudante);

                resultado(
                        crud.inserir(estudante),
                        "Um novo estudante com matricula "+ matricula +" cadastrado com sucesso!",
                        "Opss... Falha no cadastro do estudante."
                );
            } else System.out.println("ID do curso não encontrado.");

        } catch (InputMismatchException e) {
            System.out.println("Informe um número.");
        }
    }

    @Override
    void listar() {
        System.out.println("Listagem dos Estudantes");
        ArrayList<Estudante> estudantes = crud.listar();
        System.out.println("| ID | Nome | Matrícula | Curso |");

        for (Estudante e : estudantes) System.out.println(e);
    }

    @Override
    void alterar() {
        System.out.println("Alteração de Estudante");
        System.out.print("Informe a matricula do estudante: ");
        try {
            int matricula = input.nextInt();
            Estudante estudante = crud.listar(matricula);

            if (estudante != null) {
                System.out.println("Qual atributo deseja alterar?");
                System.out.print(
                    "[1] Nome \n" +
                    "[2] Matrícula \n" +
                    "[3] Curso \n" +
                    "[4] Histórico \n" +
                    "Opção: "
                );

                switch (input.next()){
                    case "1":
                        System.out.print("Novo nome: ");
                        estudante.setNome(input.next());
                        showUpdates(estudante);
                        break;
                    case "2":
                        System.out.print("Nova matrícula: ");
                        estudante.setMatricula(input.nextInt());
                        showUpdates(estudante);
                        break;
                    case "3":
                        System.out.print("ID do novo curso: ");
                        try {
                            int id = input.nextInt();
                            Curso curso = new CRUD<>(Banco.cursos).listar(id);
                            if (curso != null) {
                                estudante.getCurso().removeEstudante(estudante);
                                estudante.setCurso(curso);
                                curso.addEstudante(estudante);
                                showUpdates(estudante);
                            }
                            else System.out.println("ID do curso não encontrado.");
                        } catch (InputMismatchException e){
                            System.out.println("Informe um ID válido.");
                        }
                        break;
                    case "4":
                        System.out.print("Nova matrícula: ");
                        estudante.setMatricula(input.nextInt());
                        showUpdates(estudante);
                        break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } else System.out.println("Estudante com matrícula "+ matricula +" não encontrado");
        } catch (InputMismatchException e) {
            System.out.println("Informe uma matrícula válida.");
        }
    }

    @Override
    void deletar() {
        System.out.println("Remoção de Estudante");
        System.out.print("Informe a matrícula do estudante: ");
        try {
            int matricula = input.nextInt();
            Estudante estudante = crud.listar(matricula);

            boolean rm_estudante_curso = estudante.getCurso().removeEstudante(estudante);
            boolean rm_estudante_Historico = new CRUD<>(Banco.historicos).deletar(estudante.getHistorico());
            boolean rm_estudante = crud.deletar(estudante);

            resultado(
                    rm_estudante_curso && rm_estudante_Historico && rm_estudante,
                    "Estudante com matricula "+ matricula +" removido com sucesso!",
                    "Opss... Falha na remoção do estudante."
            );
        } catch (InputMismatchException e){
            System.out.println("Informe uma matrícula válida.");
        } catch (NullPointerException e){
            System.out.println("Estudante não encontrado.");
        }
    }

    void showUpdates(Estudante estudante){
        resultado(
                crud.alterar(estudante),
                "Estudante alterado com sucesso!",
                "Opss... Falha na alteração do estudante."
        );
    }
}
