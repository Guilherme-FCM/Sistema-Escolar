package Entidades;

public class Historico implements Entidade, Comparable<Historico> {
    private int id;
    private int [] notas = new int[4];
    private int frequencia;
    private Estudante estudante;

    public Historico(int[] notas, int frequencia, Estudante estudante) {
        this(estudante);
        this.notas = notas;
        this.frequencia = frequencia;
    }

    protected static int auto_increment;
    public Historico(Estudante estudante) {
        auto_increment++;
        this.id = auto_increment;
        this.estudante = estudante;
    }

    public double obterMedia(){
        double total = 0;
        for (int nota : notas) total += nota;
        return total / notas.length;
    }

    public double obterFrequencia(){
        return frequencia * 100 / getEstudante().getCurso().getAulas();
    }

    public boolean obterSituacao(){
        return obterMedia() >= 6 && obterFrequencia() >= 75;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int[] getNotas() { return notas; }

    public void setAllNotas(int[] notas) { this.notas = notas; }

    public void setNota(int idx, int valor){
        this.notas[idx] = valor;
    }

    public int getFrequencia() { return frequencia; }

    public void setFrequencia(int frequencia) { this.frequencia = frequencia; }

    public Estudante getEstudante() { return estudante; }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    @Override
    public String toString() {
        return "| " +
                getId() + " | " +
                obterMedia() + " | " +
                getFrequencia() + " | " +
                getEstudante().getNome() + " | ";
    }

    @Override
    public int compareTo(Historico o) {
        String nomeObj = o.getEstudante().getCurso().getNome();
        String nomeHistorico = this.getEstudante().getCurso().getNome();
        return nomeHistorico.compareTo(nomeObj);
    }
}
