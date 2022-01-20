package Relatorios.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class RelatiosActions extends JFrame implements ActionListener {
    String[] colunas;
    String[][] dados;

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = new JTable(dados, colunas);

        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(table, BorderLayout.CENTER);

        JButton botao = new JButton("Imprimir");
        botao.addActionListener(new ImprimirAction(getTitle(), colunas, dados));
        botao.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
        add(botao, BorderLayout.PAGE_END);

        setSize(500, 300);
        setVisible(true);
    }
}
