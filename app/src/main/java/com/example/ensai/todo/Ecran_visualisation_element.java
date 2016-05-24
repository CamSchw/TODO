package com.example.ensai.todo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ecran_visualisation_element extends AppCompatActivity {

    EditText nomVis = null;
    EditText comVis = null;
    ImageView typeVis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_visualisation_element);
        Intent intentV = getIntent();
        String nomItem = intentV.getStringExtra("nom_item");
        String comItem = intentV.getStringExtra("com_item");
        String typeItem = intentV.getStringExtra("type_item");


        nomVis = (EditText) findViewById(R.id.nom_a_vis);
        comVis = (EditText) findViewById(R.id.com_a_vis);
        typeVis = (ImageView) findViewById(R.id.type_a_vis);
        nomVis.setText(nomItem);
        comVis.setText(comItem);

        /*switch (typeItem) {
            case "Mémo" : typeVis.setImageResource(R.drawable.ic_assignment_memo);
                break;
            case "Rendez-Vous" : typeVis.setImageResource(R.drawable.ic_people_rdv);
                break;
            case "Anniversaire" : typeVis.setImageResource(R.drawable.ic_action_anniv);
                break;
            case "Echéance" : typeVis.setImageResource(R.drawable.ic_today_echeance);
                break;
            //default et autre
        }*/



    }


    /*Retour à l'écran d'accueil*/
    public void clickRetourMenu(View view){
        /* Si modif */
        String newNom = nomVis.getText().toString();
        MySQLite bdd = new MySQLite(this);
        SQLiteDatabase writablebdd = bdd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom",newNom);
        writablebdd.update("element",values,"description=?", new String[] {comVis.getText().toString()});
        String newCom = comVis.getText().toString();
        values.put("description",newCom);
        writablebdd.update("element",values,"nom=?", new String[] {newNom});
        writablebdd.close();
        bdd.close();

        Intent intent = new Intent(this, Ecran_accueil.class);
        startActivity(intent);
    }

    /*Suppression de la tâche*/
    public void clickSupprimer(View view){
        String newNom = nomVis.getText().toString();
        MySQLite bdd = new MySQLite(this);
        SQLiteDatabase writablebdd = bdd.getWritableDatabase();
        writablebdd.delete("element","nom=?", new String[] {newNom});
        writablebdd.close();
        bdd.close();

        Intent intent = new Intent(this, Ecran_accueil.class);
        startActivity(intent);
    }

}
