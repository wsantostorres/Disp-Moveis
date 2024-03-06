package com.example.agendatelefonica;

import java.util.ArrayList;

public class Agenda extends ArrayList<Contato> {

    public void inserir(String aNome, String aFone) {
        this.add(new Contato(aNome, aFone));
    }
    public void editar(int id, String aNome, String aFone) {
        Contato contato = this.get(id);
        contato.setNome(aNome);
        contato.setFone(aFone);
    }

    public void excluir(int id) {
        this.remove(id);
    }
}

