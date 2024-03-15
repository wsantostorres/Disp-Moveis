package com.example.patrimeux.models;

import androidx.annotation.NonNull;

public class MovelModel {
    private int id;
    private String nome;
    private String descricao;
    private String local;
    private String imagem;

    public MovelModel(String nome, String descricao, String local, String imagem){
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.imagem = imagem;
    }

    @NonNull
    public String toString(){
        return id + "- Nome: " + nome + " Descricao: " + descricao + "" + "Local: " + local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
