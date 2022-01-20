package Entidades;

public class Estudante extends Pessoa implements Comparable<Estudante>{
    private int matricula;
    private Curso curso;
    private Historico historico;

    public Estudante(String nome, int matricula, Curso curso) {
        super(nome);
        this.matricula = matricula;
        this.curso = curso;
    }

    public Estudante(int matricula, Curso curso) {
        super();
        this.matricula = matricula;
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Historico getHistorico() { return historico; }

    public void setHistorico(Historico historico) { this.historico = historico; }

    @Override
    public String toString() {
        return  "| " +
                getId() + " | " +
                getNome() + " | " +
                getMatricula() + " | " +
                getCurso().getNome() + " | ";
    }

    @Override
    public int compareTo(Estudante o) {
        return this.getNome().compareTo(o.getNome());
    }
}
