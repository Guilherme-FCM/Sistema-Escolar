package Relatorios.Actions;

import Relatorios.Relatorio;

import java.awt.event.ActionEvent;

public class QtdEstudantesCursosAction extends RelatiosActions {
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setTitle("Quantidade de Estudantes Matriculados em Cada Curso");
        colunas = new String[]{"Nome do Curso          ", "Quantidade de Estudantes"};
        dados = Relatorio.qtdEstudantesCursos();
        super.actionPerformed(e);
    }
}
