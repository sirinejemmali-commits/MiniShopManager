package com.example.minishopmanager

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class CatalogueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogue)

        val listView = findViewById<ListView>(R.id.listViewProduits)
        val listeProduits = ArrayList<HashMap<String, Any>>()
        val dbHelper = PanierDBHelper(this)

        val jsonString = assets.open("produits.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val nom = obj.getString("nom")
            val prix = obj.getString("prix")
            val imageName = obj.getString("image")

            // Récupérer l'ID de l'image dans res/drawable
            val imageResId = resources.getIdentifier(imageName, "drawable", packageName)

            val map = HashMap<String, Any>()
            map["nom"] = nom
            map["prix"] = prix
            map["image"] = if (imageResId != 0) imageResId else R.mipmap.ic_launcher
            listeProduits.add(map)
        }

        val adapter = SimpleAdapter(
            this,
            listeProduits,
            R.layout.item_produit,
            arrayOf("image", "nom", "prix"),
            intArrayOf(R.id.imgProduit, R.id.tvNomProduit, R.id.tvPrixProduit)
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val nomSelectionne = listeProduits[position]["nom"].toString()
            val prixSelectionne = listeProduits[position]["prix"].toString()

            dbHelper.ajouterProduit(nomSelectionne, prixSelectionne)

            Toast.makeText(
                this,
                "$nomSelectionne ajouté au panier !",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
