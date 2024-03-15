package com.example.patrimeux;

import static android.view.Gravity.END;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.patrimeux.config.DatabaseHelper;
import com.example.patrimeux.dao.MovelDao;
import com.example.patrimeux.models.MovelModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    MovelDao movelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = new DatabaseHelper(this);
        db = helper.getReadableDatabase();

        movelDao = new MovelDao(db);

        List<MovelModel> moveisList = movelDao.getAll();
        showList(moveisList);
    }

    public void showList(List<MovelModel> moveisList){

        LinearLayout linearLayoutList = findViewById(R.id.linearLayoutList);
        linearLayoutList.removeAllViews();

        for(MovelModel movel : moveisList){
            // Criando componentes
            MaterialCardView card = new MaterialCardView(this);
            MaterialButton buttonEditMovel = new MaterialButton(this);
            MaterialButton buttonDeleteMovel = new MaterialButton(this);
            TextView title = new TextView(getApplicationContext());
            TextView description = new TextView(getApplicationContext());
            TextView place = new TextView(getApplicationContext());
            ImageView imageView = new ImageView(getApplicationContext());

            // LinearLayouts
            LinearLayout mainLayout = new LinearLayout(getApplicationContext());
            mainLayout.setPadding(16, 16, 16, 16);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout imageLayout = new LinearLayout(getApplicationContext());
            imageLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            imageLayoutParams.setMargins(0, 0, 0, 16);
            imageLayout.setLayoutParams(imageLayoutParams);

            LinearLayout textLayout = new LinearLayout(getApplicationContext());
            textLayout.setPadding(16, 16, 16, 16);
            textLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout buttonLayout = new LinearLayout(getApplicationContext());
            buttonLayout.setGravity(END);
            textLayout.setPadding(16, 16, 16, 16);
            buttonLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Estilizando card
            card.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            card.setCardBackgroundColor(Color.parseColor("#f8f8f8"));
            LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cardLayoutParams.setMargins(0, 0, 0, 16);
            card.setLayoutParams(cardLayoutParams);

            // Alterando textos
            title.setText(movel.getNome());
            title.setTextColor(Color.parseColor("#222222"));
            title.setTextSize(16);
            LinearLayout.LayoutParams titleMargin = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            titleMargin.setMargins(0, 0, 0, 16);
            title.setLayoutParams(titleMargin);

            description.setText(movel.getDescricao());
            LinearLayout.LayoutParams descriptionMargin = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            descriptionMargin.setMargins(0, 0, 0, 16);
            description.setLayoutParams(descriptionMargin);

            place.setText(movel.getLocal());

            // Botão Editar
            LinearLayout.LayoutParams editButtonParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            editButtonParams.setMargins(0, 0, 16, 0);
            buttonEditMovel.setLayoutParams(editButtonParams);
            buttonEditMovel.setText("Editar");

            // Botão Excluir
            buttonDeleteMovel.setText("Excluir");

            // Colocando imagem
            Bitmap bitmap = BitmapFactory.decodeFile(movel.getImagem());
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    500
            );
            imageView.setImageBitmap(bitmap);
            imageView.setLayoutParams(imageParams);

            // Inserindo elementos em cada layout
            imageLayout.addView(imageView);

            textLayout.addView(title);
            textLayout.addView(description);
            textLayout.addView(place);

            buttonLayout.addView(buttonEditMovel);
            buttonLayout.addView(buttonDeleteMovel);

            mainLayout.addView(imageLayout);
            mainLayout.addView(textLayout);
            mainLayout.addView(buttonLayout);

            card.addView(mainLayout);
            linearLayoutList.addView(card);

            // Adicionando funcionalidades
            buttonDeleteMovel.setOnClickListener(v -> {
                movelDao.delete(movel.getId());
                showList(movelDao.getAll());
            });

            buttonEditMovel.setOnClickListener(v -> {
                Intent intent = new Intent(this, FormActivity.class);
                intent.putExtra("pageTitle", movel.getNome());
                intent.putExtra("id", String.valueOf(movel.getId()));
                startActivity(intent);
            });
        }

    }

    public void createView(View button){
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("pageTitle","Cadastrar Móvel");
        startActivity(intent);
    }


}