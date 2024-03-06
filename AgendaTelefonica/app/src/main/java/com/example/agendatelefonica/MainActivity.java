package com.example.agendatelefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agenda = new Agenda();
    }

    public void addTelefone(View v){
        String nome = ((EditText)findViewById(R.id.nomeText)).getText().toString();
        String fone = ((EditText)findViewById(R.id.foneText)).getText().toString();

        if(!validation(nome, fone)){
            return;
        }

        agenda.inserir(nome, fone);

        clearForm(v);
        updateList();
    };


    View.OnClickListener actionAddTelefone = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nome = ((EditText)findViewById(R.id.nomeText)).getText().toString();
            String fone = ((EditText)findViewById(R.id.foneText)).getText().toString();

            if(!validation(nome, fone)){
                return;
            }

            agenda.inserir(nome, fone);

            clearForm(v);
            updateList();
        }
    };


    public void updateList(){
        final EditText nome = findViewById(R.id.nomeText);
        final EditText fone = findViewById(R.id.foneText);

        LinearLayout ll = findViewById(R.id.listaTelefonica);
        ll.removeAllViews();

        //percorre todos os contatos salvos na agenda
        for (int i = 0; i < agenda.size(); i++){
            final Contato contato = agenda.get(i);
            final int finalI = i;

            //cria um componente e adiciona no linearLayout
            TextView tv = new TextView(getApplicationContext());
            tv.setText(contato.toString());
            tv.setTextSize(18f);
            ll.addView(tv);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = finalI;
                    nome.setText(contato.getNome());
                    fone.setText(contato.getFone());
                    updateTelefone(v, id);
                    deleteTelefone(v, id);
                }
            });

        }
    }

    public void updateTelefone(View v, int id){
        Button buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = ((EditText)findViewById(R.id.nomeText)).getText().toString();
                String fone = ((EditText)findViewById(R.id.foneText)).getText().toString();

                if(!validation(nome, fone)){
                    return;
                }

                agenda.editar(id, nome, fone);

                clearForm(v);
                updateList();

                buttonSave.setOnClickListener(actionAddTelefone);

            }
        });

    }

    public void deleteTelefone(View v, int id){
        Button buttonDelete = findViewById(R.id.delete);
        Button buttonSave = findViewById(R.id.save);

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                agenda.excluir(id);
                clearForm(v);
                updateList();

                buttonSave.setOnClickListener(actionAddTelefone);

            }
        });
    }

    public void clearForm(View view){
        Button buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(actionAddTelefone);

        final EditText nome = findViewById(R.id.nomeText);
        final EditText fone = findViewById(R.id.foneText);
        nome.setText("");
        fone.setText("");
    }

    public boolean validation(String nome, String fone){
        LinearLayout ll = findViewById(R.id.listaTelefonica);
        TextView tv = new TextView(getApplicationContext());

        if(nome.isEmpty() || fone.isEmpty()){
            tv.setText("Preencha todos os campos, por favor");
            tv.setTextSize(14f);
            tv.setTextColor(Color.rgb(255, 0, 0));
            ll.addView(tv);
            return false;
        }

        tv.setText("");

        return true;
    }


}