package Menu;

import CRUD.CRUD;
import CRUD.Banco;
import Entidades.Curso;
import Entidades.Professor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;

public class MenuCursos extends Menu {
    private CRUD<Curso> crud = new CRUD<>(Banco.cursos);
    private CRUD<Professor> crud_proffs = new CRUD<>(Banco.professores);

    @Override
    void inserir() {
        System.out.println("Dados do Curso");

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Período: ");
        String periodo = input.next();

        System.out.print("Data de inicio (dd/mm/aaaa): ");
        Calendar data_inicio = formatDate(input.next());

        System.out.print("Data de fim (dd/mm/aaaa): ");
        Calendar data_fim = formatDate(input.next());

        try {
            System.out.print("Número de aulas: ");
            int aulas = input.nextInt();

            System.out.print("Número de vagas: ");
            int vagas = input.nextInt();

            System.out.print("Informe o ID do Professor: ");
            int id_proff = input.nextInt();

            Professor professor = crud_proffs.listar(id_proff);

            if (professor != null) {
                if (professor.getCurso() == null) {
                    Curso curso = new Curso(nome, periodo, data_inicio, data_fim, aulas, vagas, professor);
                    professor.setCurso(curso);

                    resultado(
                            crud.inserir(curso),
                            "Um novo curso cadastrado com sucesso!",
                            "Opss... Falha no cadastro do curso."
                    );
                } else System.out.println("Este professor já dá aula em um curso");
            } else System.out.println("ID professor não encontrado.");
        } catch (InputMismatchException e){
            System.out.println("Informe um número.");
        }
    }

    @Override
    void listar() {
        System.out.println("Listagem dos Cursos");
        ArrayList<Curso> cursos = crud.listar();
        System.out.println("| ID | Nome | Periodo | Data de Início | Data de Fim | N° Vagas | Professor |");

        for (Curso c : cursos) System.out.println(c);
    }

    @Override
    void alterar() {
        System.out.println("Alteração de Curso");
        System.out.print("Informe o ID do curso: ");
        try {
            int id = input.nextInt();
            Curso curso = crud.listar(id);

            if (curso != null) {
                System.out.println("Qual atributo deseja alterar?");
                System.out.print(
                        "[1] Nome \n" +
                        "[2] Periodo \n" +
                        "[3] Data de Início \n" +
                        "[4] Data de Fim \n" +
                        "[5] N° Vagas \n" +
                        "[6] Professor \n" +
                        "Opção: "
                );

                switch (input.next()){
                    case "1":
                        System.out.print("Novo nome: ");
                        curso.setNome(input.next());
                        showUpdates(curso);
                        break;
                    case "2":
                        System.out.print("Novo período: ");
                        curso.setPeriodo(input.next());
                        showUpdates(curso);
                        break;
                    case "3":
                        System.out.print("Nova data de início (dd/mm/aaaa): ");
                        curso.setInicio( formatDate(input.next()) );
                        showUpdates(curso);
                        break;
                    case "4":
                        System.out.print("Nova data de fim (dd/mm/aaaa): ");
                        curso.setFim( formatDate(input.next()) );
                        showUpdates(curso);
                        break;
                    case "5":
                        System.out.print("Novo número de vagas: ");
                        try {
                            curso.setVagas(input.nextInt());
                            showUpdates(curso);
                        } catch (InputMismatchException e) {
                            System.out.println("Informe um número.");
                        }
                        break;
                    case "6":
                        System.out.print("ID do novo professor: ");
                        int id_proff = input.nextInt();

                        Professor professor = crud_proffs.listar(id_proff);

                        if (professor != null){
                            curso.setProfessor(professor);
                            professor.setCurso(curso);
                            showUpdates(curso);
                        } else System.out.println("Professor não encontrado.");

                        break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } else System.out.println("Curso com ID "+ id +" não encontrado");
        } catch (InputMismatchException e) {
            System.out.println("Informe um ID válido.");
        }
    }

    @Override
    void deletar() {
        System.out.println("Remoção de Curso");
        System.out.print("Informe o ID do Curso: ");
        try {
            int id = input.nextInt();
            Curso curso = crud.listar(id);

            int sizeEstudantes = curso.getEstudantes().size();
            if (sizeEstudantes == 0) {
                curso.getProfessor().setCurso(null);
                resultado(
                        crud.deletar(curso),
                        "Curso " + id + " removido com sucesso!",
                        "Opss... Falha na remoção do curso."
                );
            } else System.out.println(
                "Há "+ sizeEstudantes +" estudantes neste curso. " +
                "Altere o curso destes estudantes antes de excluir este curso."
            );
        } catch (InputMismatchException e){
            System.out.println("Informe um ID válido.");
        } catch (NullPointerException e){
            System.out.println("Curso não encontrado.");
        }
    }

    private void showUpdates(Curso curso){
        resultado(
                crud.alterar(curso),
                "Curso alterado com sucesso!",
                "Opss... Falha na alteração do curso."
        );
    }

    private Calendar formatDate(String txt){
        Calendar cal = Calendar.getInstance();
        try {
            String[] date = txt.split("/");
            cal.set(
                    Integer.parseInt(date[2]),
                    Integer.parseInt(date[1]) -1,
                    Integer.parseInt(date[0])
            );
        } catch (Exception e) {
            System.out.println("Data ilegível, siga a formatação. Data alterada para o dia de hoje.");
        }
        return cal;
    }

}
