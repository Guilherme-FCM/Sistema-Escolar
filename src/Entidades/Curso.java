package Entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Curso implements Entidade {
    private int id, aulas, vagas;
    private String nome, periodo;
    private Calendar inicio, fim;
    private Professor professor;
    private ArrayList<Estudante> estudantes = new ArrayList<>();

    public Curso(String nome, String periodo, Calendar inicio, Calendar fim, int aulas, int vagas, Professor professor) {
        this(professor);
        this.nome = nome;
        this.periodo = periodo;
        this.inicio = inicio;
        this.fim = fim;
        this.aulas = aulas;
        this.vagas = vagas;
    }

    private static int auto_increment;
    public Curso(Professor professor) {
        this.professor = professor;
        auto_increment++;
        this.id = auto_increment;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean addEstudante(Estudante estudante){
        return estudantes.add(estudante);
    }

    public boolean removeEstudante(Estudante estudante){
        return estudantes.remove(estudante);
    }

    public ArrayList<Estudante> getEstudantes() {
        return estudantes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Calendar getCalendarInicio() {
        return inicio;
    }

    public String getInicio(){
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        return formater.format(inicio.getTime());
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getCalendarFim() {
        return fim;
    }

    public String getFim(){
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        return formater.format(fim.getTime());
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public int getId() {
        return id;
    }

    public int getAulas() { return aulas; }

    public void setAulas(int aulas) { this.aulas = aulas; }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    @Override
    public String toString() {
        return  "| " +
                getId() + " | " +
                getNome() + " | " +
                getPeriodo() + " | " +
                getInicio() + " | " +
                getFim() + " | " +
                getVagas() + " | " +
                getProfessor().getNome() + " | ";
    }
}
