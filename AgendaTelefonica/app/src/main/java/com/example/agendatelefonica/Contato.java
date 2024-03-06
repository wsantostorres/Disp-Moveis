package com.example.agendatelefonica;

public class Contato {
    private String nome;
    private String fone;

    public Contato(String aNome, String aFone) {
        nome = aNome;
        fone = aFone;
    }

    @Override
    public String toString() {
        return nome + " - - - - - - - - - - " + fone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
}
