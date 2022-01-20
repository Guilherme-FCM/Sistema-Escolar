package Relatorios.Actions;

import Relatorios.Relatorio;

import java.awt.event.ActionEvent;

public class EstudantesAprovadosAction extends RelatiosActions {
    @Override
    public void actionPerformed(ActionEvent e) {
        setTitle("Nomes dos Alunos Aprovados em Ordem Alfabética");
        colunas = new String[]{"Nome do Estudante", "Média", "Frequência", "Nome do Curso          "};
        dados = Relatorio.estudantesAprovados();
        super.actionPerformed(e);
    }
}
