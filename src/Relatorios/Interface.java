package Relatorios;

import Relatorios.Actions.EstudantesAprovadosAction;
import Relatorios.Actions.MediaEstudantesCursosAction;
import Relatorios.Actions.PorcentagemEstudantesReprovadosAction;
import Relatorios.Actions.QtdEstudantesCursosAction;

import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame {
    public Interface() throws HeadlessException {
        this.setTitle("Relatórios");
        this.setSize(415, 365);
        this.setLayout(null);

        JButton b1 = new JButton("Quantidade de estudantes matriculados em cada curso");
        b1.setBounds(25,25,350,50);
        b1.addActionListener(new QtdEstudantesCursosAction());
        this.add(b1);

        JButton b2 = new JButton("Média de cada aluno separado por curso");
        b2.setBounds(25,100,350,50);
        b2.addActionListener(new MediaEstudantesCursosAction());
        this.add(b2);

        JButton b3 = new JButton("Porcentagem de estudantes reprovados");
        b3.setBounds(25,175,350,50);
        b3.addActionListener(new PorcentagemEstudantesReprovadosAction());
        this.add(b3);

        JButton b4 = new JButton("Nomes dos alunos aprovados em ordem alfabética");
        b4.setBounds(25,250,350,50);
        b4.addActionListener(new EstudantesAprovadosAction());
        this.add(b4);

        this.setVisible(true);
    }
}
