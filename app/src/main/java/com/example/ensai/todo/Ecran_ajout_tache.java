package com.example.ensai.todo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ecran_ajout_tache extends AppCompatActivity {

    private EditText nom=null;
    private EditText com=null;
    private Spinner type=null;
    Element element = new Element();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_ajout_tache);
        Intent intent = getIntent();

        nom = (EditText) findViewById(R.id.editNom);
        com = (EditText) findViewById(R.id.editCom);
        type = (Spinner) findViewById(R.id.spinnerTypeElement);


        /* Afficher la date si type=anniversaire, rdv, échéance */
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (parentView.getItemAtPosition(position).equals("Anniversaire") || parentView.getItemAtPosition(position).equals("Rendez-Vous")
                        || parentView.getItemAtPosition(position).equals("Echéance")) {
                    TextView date = (TextView) findViewById(R.id.date);
                    date.setText("Date : ");
                }
                if (parentView.getItemAtPosition(position).equals("Mémo") || parentView.getItemAtPosition(position).equals("Autre")) {
                    TextView date = (TextView) findViewById(R.id.date);
                    date.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }

    public void clickAddTask(View vue){

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
        values.put("type", element.getType());
        writablebdd.insert("element", null, values);

        writablebdd.close();

        Toast.makeText(this, R.string.addTask, Toast.LENGTH_LONG).show();

        this.finish();
    }
}
