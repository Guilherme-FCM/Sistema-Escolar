package Relatorios;

import CRUD.Banco;
import CRUD.CRUD;
import Entidades.Curso;
import Entidades.Estudante;
import Entidades.Historico;

import java.util.*;

public class Relatorio {
    public static String[][] qtdEstudantesCursos(){
        ArrayList<Curso> cursos = new CRUD<>(Banco.cursos).listar();
        String[][] dados = new String[cursos.size()][2];

        for (int i = 0; i < cursos.size(); i++) {
            dados[i][0] = cursos.get(i).getNome();
            dados[i][1] = String.valueOf(cursos.get(i).getEstudantes().size());
        }
        return dados;
    }

    public static String[][] mediaEstudantesCursos(){
        ArrayList<Historico> historicos = new CRUD<>(Banco.historicos).listar();
        Collections.sort((historicos));
        String[][] dados = new String[historicos.size()][3];

        for (int i = 0; i < historicos.size(); i++){
            dados[i][0] = historicos.get(i).getEstudante().getCurso().getNome();
            dados[i][1] = historicos.get(i).getEstudante().getNome();
            dados[i][2] = String.valueOf(historicos.get(i).obterMedia());
        }
        return dados;
    }

    public static String[][] porcentagemEstudantesReprovados(){
        ArrayList<Curso> cursos = new CRUD<>(Banco.cursos).listar();
        String[][] dados = new String[cursos.size()][2];

        for (int i = 0; i < cursos.size(); i++){
            int reprovados = 0;

            ArrayList<Estudante> estudantes = cursos.get(i).getEstudantes();
            for (Estudante estudante : estudantes) {
                Historico historico = estudante.getHistorico();
                if (historico != null && !historico.obterSituacao())
                    reprovados++;
            }

            dados[i][0] = cursos.get(i).getNome();
            dados[i][1] = (reprovados * 100 / estudantes.size()) + "%";
        }
        return dados;
    }

    public static String[][] estudantesAprovados(){
        ArrayList<Historico> historicos = new CRUD<>(Banco.historicos).listar();
        ArrayList<Estudante> estudantes = new ArrayList<>();

        for (Historico h : historicos)
            if (h.obterSituacao()) estudantes.add(h.getEstudante());

        Collections.sort(estudantes);
        String[][] dados = new String[estudantes.size()][4];

        for (int i = 0; i < estudantes.size(); i++) {
            dados[i][0] = estudantes.get(i).getNome();
            dados[i][1] = String.valueOf(estudantes.get(i).getHistorico().obterMedia());
            dados[i][2] = estudantes.get(i).getHistorico().obterFrequencia() + "%";
            dados[i][3] = estudantes.get(i).getCurso().getNome();
        }

        return dados;
    }
}
