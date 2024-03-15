package com.example.patrimeux.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "meu_bd";
    private static final int DATABASE_VERSION = 1;
    private static final String MOVEIS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS MOVEIS (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +  "                            nome TEXT, "
                    +  "                            descricao TEXT,"
                    +  "                               local TEXT,"
                    +  "                               imagem TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MOVEIS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MOVEIS");
        onCreate(db);
    }
}

