package com.example.minishopmanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PanierDBHelper(context: Context) : SQLiteOpenHelper(context, "shop.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE panier (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "prix TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS panier")
        onCreate(db)
    }

    fun ajouterProduit(nom: String, prix: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nom", nom)
            put("prix", prix)
        }
        db.insert("panier", null, values)
        db.close()
    }
}