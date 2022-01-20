package Relatorios.Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImprimirAction implements ActionListener {
    String titulo;
    String[] colunas;
    String[][] dados;

    public ImprimirAction(String titulo, String[] colunas, String[][] dados) {
        this.titulo = titulo;
        this.colunas = colunas;
        this.dados = dados;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File arquivo = new File("relatório.txt");
        try {
            FileWriter escritor = new FileWriter(arquivo);
            escritor.write("Relatório: " + titulo);
            escritor.write("\n \n");

            for (String coluna : colunas)
                escritor.write(String.format("| %s ", coluna));
            escritor.write("|\n");

            for (String[] linha : dados) {
                for (int c = 0; c < linha.length; c++) {
                    linha[c] = formatarColuna(colunas[c], linha[c]);
                    escritor.write(String.format("| %s ", linha[c]));
                }
                escritor.write("|\n");
            }
            escritor.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String formatarColuna(String coluna, String valor){
        int diferencaTam = coluna.length() - valor.length();

        if (diferencaTam > 0)
            for (int i = 0; i < diferencaTam; i++)
                valor = valor.concat(" ");

        else if (diferencaTam < 0)
            valor = valor.substring(0, coluna.length());

        return valor;
    }
}
