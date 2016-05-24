package com.example.ensai.todo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ecran_accueil extends AppCompatActivity {

    protected ArrayAdapter<Element> adapter =null;
    ListView listeElement = null;
    final Context contexte = this;

    ElementAdapter elementAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_accueil);


        /*listeView sans lien avec la bdd
        ArrayList<Element> liste = new ArrayList<Element>();
        Element el1 = new Element("A","a");
        Element el2 = new Element("B","a");
        liste.add(el1);
        liste.add(el2);
        adapter = new ArrayAdapter<Element>(this, android.R.layout.simple_list_item_1, liste);
        listeElement.setAdapter(adapter);*/

        /*listeView en lien avec la bdd*/
        listeElement = (ListView) findViewById(R.id.liste);
        lienBDD();


        /*Gérer les clicks sur les éléments*/
        listeElement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(contexte, R.string.clickOnItem, Toast.LENGTH_LONG).show();
                Element element = (Element) parent.getItemAtPosition(position);
                Intent intentV = new Intent(contexte, Ecran_visualisation_element.class);
                intentV.putExtra("nom_item", element.getNom()); //récupérer les infos de l'item sur lequel on clique
                intentV.putExtra("com_item", element.getCommentaire());
                intentV.putExtra("type_item", element.getType());
                startActivity(intentV);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        lienBDD();
    }


    public void lienBDD() {
        ArrayList<Element> liste = new ArrayList<Element>();
        MySQLite bdd = new MySQLite(this);
        SQLiteDatabase writablebdd = bdd.getWritableDatabase();
        Cursor cursor1 = writablebdd.rawQuery("SELECT * FROM element",null);

        int nbRows = cursor1.getCount();
        while (cursor1.moveToNext()) {
            String nom = cursor1.getString(0);
            String description = cursor1.getString(1);
            String type = cursor1.getString(2);
            Element element1 = new Element();
            element1.setNom(nom);
            element1.setCommentaire(description);
            element1.setType(type);
            liste.add(element1);
        }
        /*adapter = new ArrayAdapter<Element>(this, android.R.layout.simple_list_item_1, liste);
        listeElement.setAdapter(adapter);*/

        elementAdapter = new ElementAdapter(this, liste);
        listeElement.setAdapter(elementAdapter);

        cursor1.close();
        bdd.close();
    }



    public ArrayAdapter<Element> getAdapter() {
        return adapter;
    }



    public void clickNewTask(View view){
        Toast.makeText(this, R.string.clickNewTask, Toast.LENGTH_LONG).show();

        /*accéder à l'écran d'ajout d'une tâche*/
        Intent intent = new Intent(this, Ecran_ajout_tache.class);
        startActivity(intent);
    }



}
