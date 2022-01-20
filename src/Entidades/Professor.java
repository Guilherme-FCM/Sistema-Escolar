package Entidades;

public class Professor extends Pessoa {
    private String titulacao;
    private Curso curso;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor(String nome, String titulacao) {
        super(nome);
        this.titulacao = titulacao;
    }

    public Professor() {
        super();
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    @Override
    public String toString() {
        String retorno = "| " +
                getId() + " | " +
                getNome() + " | " +
                getTitulacao() + " | ";

        try {
            retorno += getCurso().getNome() + " | ";
        } catch (NullPointerException e){
            retorno += "null | ";
        }

        return retorno;
    }
}
