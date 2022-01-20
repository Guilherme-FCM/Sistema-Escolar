package Relatorios.Actions;

import Relatorios.Relatorio;
import java.awt.event.ActionEvent;

public class MediaEstudantesCursosAction extends RelatiosActions {
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setTitle("Média de Cada Aluno Separado por Curso");
        colunas = new String[]{"Nome do Curso         ", "Nome do Estudante", "Média"};
        dados = Relatorio.mediaEstudantesCursos();
        super.actionPerformed(e);
    }
}
