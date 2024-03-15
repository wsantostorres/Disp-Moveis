package com.example.patrimeux.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.patrimeux.models.MovelModel;

import java.util.ArrayList;
import java.util.List;

public class MovelDao {
    SQLiteDatabase db;

    public MovelDao(SQLiteDatabase db){
        this.db = db;
    }

    public void insert(MovelModel movel){

        String[] args = new String[4];
        args[0] = movel.getNome();
        args[1] = movel.getDescricao();
        args[2] = movel.getLocal();
        args[3] = movel.getImagem();

        db.execSQL("INSERT INTO MOVEIS (NOME, DESCRICAO, LOCAL, IMAGEM) VALUES (?, ?, ?, ?)",
                args);
    }

    public void update(MovelModel movel, int id){

        String[] args = new String[5];
        args[0] = movel.getNome();
        args[1] = movel.getDescricao();
        args[2] = movel.getLocal();
        args[3] = movel.getImagem();
        args[4] = String.valueOf(id);

        db.execSQL("UPDATE MOVEIS SET nome = ?, descricao = ?, local = ?, imagem = ? WHERE id = ?",
                args);
    }

    public void delete(int id){
        String[] args = new String[1];
        args[0] = String.valueOf(id);
        db.execSQL("DELETE FROM MOVEIS WHERE id=?", args);
    }

    public MovelModel get(int id){
        String[] args = new String[1];
        args[0] = String.valueOf(id);

        Cursor result = db.rawQuery("SELECT id, nome, descricao, local, imagem FROM MOVEIS WHERE id=?", args);

        if (result.moveToNext()) {
            // Pegando atributos
            int idMovel = result.getInt(0);
            String nome = result.getString(1);
            String descricao = result.getString(2);
            String local = result.getString(3);
            String imagem = result.getString(4);

            // Criando objeto
            MovelModel movelItem = new MovelModel(nome, descricao, local, imagem);
            movelItem.setId(idMovel);

            result.close();

            return movelItem;
        } else {
            result.close();
            return null;
        }
    }

    public List<MovelModel> getAll(){

        List<MovelModel> moveis = new ArrayList<>();
        Cursor result = db.rawQuery("SELECT id, nome, descricao, local, imagem FROM MOVEIS", null);
        while (result.moveToNext()) {

            // Pegando atributos
            int id = result.getInt(0);
            String nome = result.getString(1);
            String descricao = result.getString(2);
            String local = result.getString(3);
            String imagem = result.getString(4);

            // Criando objeto e inserindo na lista
            MovelModel movelItem = new MovelModel(nome, descricao, local, imagem);
            movelItem.setId(id);
            moveis.add(0, movelItem);
        }
        result.close();

        return moveis;
    }



}
