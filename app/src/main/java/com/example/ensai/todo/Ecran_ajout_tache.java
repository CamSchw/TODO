package com.example.ensai.todo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Ecran_ajout_tache extends AppCompatActivity {

    private EditText nom=null;
    private EditText com=null;
    private Spinner type=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_ajout_tache);
        Intent intent = getIntent();

        nom = (EditText) findViewById(R.id.editNom);
        com = (EditText) findViewById(R.id.editCom);
        type = (Spinner) findViewById(R.id.spinnerTypeElement);

    }

    public void clickAddTask(View vue){

        Element element = new Element();
        element.setNom(nom.getText().toString());
        element.setCommentaire(com.getText().toString());
        element.setType(type.getSelectedItem().toString());

        /*Ajout à la base de données*/
        MySQLite bdd = new MySQLite(this);
        SQLiteDatabase writablebdd = bdd.getWritableDatabase();
        ContentValues values = new ContentValues();

        /* ATTENTION : gérer si le nom existe déjà (toast) */

        values.put("nom",element.getNom());
        values.put("description", element.getCommentaire());
        writablebdd.insert("element", null, values);

        writablebdd.close();

        Toast.makeText(this, R.string.addTask, Toast.LENGTH_LONG).show();

        this.finish();
    }
}
