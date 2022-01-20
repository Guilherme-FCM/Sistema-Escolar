package Relatorios.Actions;

import Relatorios.Relatorio;

import java.awt.event.ActionEvent;

public class PorcentagemEstudantesReprovadosAction extends RelatiosActions {
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setTitle("Porcentagem de Estudantes Reprovados");
        colunas = new String[]{"Nome do Curso          ", "Estudantes Reporvados (%)"};
        dados = Relatorio.porcentagemEstudantesReprovados();
        super.actionPerformed(e);
    }
}
