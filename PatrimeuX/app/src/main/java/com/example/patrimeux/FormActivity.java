package com.example.patrimeux;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.Manifest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.patrimeux.config.DatabaseHelper;
import com.example.patrimeux.dao.MovelDao;
import com.example.patrimeux.models.MovelModel;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    MovelDao movelDao;
    int id = -1;
    Button buttonTakePic;
    ImageView image;
    String pathToFile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        DatabaseHelper helper = new DatabaseHelper(this);
        db = helper.getReadableDatabase();

        movelDao = new MovelDao(db);

        image = findViewById(R.id.image);

        // Habilitar o botão de voltar na barra de ação
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        // Alterando titulo da página
        String pageTitle = intent.getStringExtra("pageTitle");
        actionBar.setTitle(pageTitle);

        if(intent.hasExtra("id")){
                id = Integer.parseInt(intent.getStringExtra("id"));
                Log.v("IdMovel", String.valueOf(id));
                MovelModel movelEdit = movelDao.get(id);
                TextInputEditText n = findViewById(R.id.nome);
                TextInputEditText d = findViewById(R.id.descricao);
                TextInputEditText l = findViewById(R.id.local);
                n.setText(movelEdit.getNome());
                d.setText(movelEdit.getDescricao());
                l.setText(movelEdit.getLocal());

                Bitmap bitmap = BitmapFactory.decodeFile(movelEdit.getImagem());
                image.setImageBitmap(bitmap);
        }

        buttonTakePic = findViewById(R.id.buttonPicture);
        if(Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 50);
        }
        buttonTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPicureTakeAction();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
            image.setImageBitmap(bitmap);
        }
    }

    private void dispatchPicureTakeAction() {
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            photoFile = createPhotoFile();

            if(photoFile != null){
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(FormActivity.this, "com.example.patrimeux.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePic, 1);
            }
        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;

        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (IOException e) {
            Log.v("Erro na foto:", e.toString());
        }

        return image;
    }

    public void createOrUpdateMovel(View v){
        TextInputEditText n = findViewById(R.id.nome);
        TextInputEditText d = findViewById(R.id.descricao);
        TextInputEditText l = findViewById(R.id.local);

        String nome = n.getText().toString();
        String descricao = d.getText().toString();
        String local = l.getText().toString();

        if(id != -1){
            MovelModel movelUpdate = new MovelModel(nome, descricao, local, pathToFile);
            movelDao.update(movelUpdate, id);
        }else{
            MovelModel movel = new MovelModel(nome, descricao, local, pathToFile);
            movelDao.insert(movel);
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}