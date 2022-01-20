package Entidades;

abstract class Pessoa implements Entidade {
    private int id;
    private String nome;

    public Pessoa(String nome) {
        this();
        this.nome = nome;
    }

    protected static int auto_increment;
    public Pessoa() {
        auto_increment++;
        this.id = auto_increment;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
