package com.example.ensai.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ensai on 27/04/16.
 */
public class MySQLite extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1 ;
    private static final String DATABASE_NAME = "baseElement" ;

    public MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE element (nom TEXT PRIMARY KEY, description TEXT, type TEXT)");


        ContentValues values = new ContentValues();
        values.put("nom", "Prérequis");
        values.put("description", "Prise en main de l'application");
        values.put("type", "Autre");
        /*values.put("A vous de jouer", "Ajoutez une nouvelle tâche");*/
        db.insert("element",null,values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // changer de version et supprimer l'ancienne base de données pour la recréer
    }
}

// Pour désinstaller : commande dans le terminal ./adb uninstall com.example.ensai.todo