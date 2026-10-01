
package com.example.listepays

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewProducts = findViewById<ListView>(R.id.listViewProducts)
        val products = resources.getStringArray(R.array.products)

        // Tableau d'images Android correspondant à chaque produit
        val productImages = arrayOf(
            android.R.drawable.ic_menu_call,          // Téléphone
            android.R.drawable.ic_btn_speak_now,     // Casque Bluetooth
            android.R.drawable.ic_menu_recent_history,// Montre connectée
            android.R.drawable.ic_lock_power_off,    // Chargeur USB
            android.R.drawable.ic_menu_manage,       // Accessoires
            android.R.drawable.ic_menu_gallery       // Cosmétiques
        )

        val adapter = ProductAdapter(this, products, productImages)
        listViewProducts.adapter = adapter

        listViewProducts.setOnItemClickListener { parent, _, position, _ ->
            val selectedProduct = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Produit sélectionné : $selectedProduct", Toast.LENGTH_SHORT).show()
        }
    }
}

class ProductAdapter(
    private val context: Context,
    private val products: Array<String>,
    private val images: Array<Int>
) : BaseAdapter() {

    override fun getCount(): Int = products.size

    override fun getItem(position: Int): Any = products[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)

        val txtProduct = view.findViewById<TextView>(R.id.txtProduct)
        val imgProduct = view.findViewById<ImageView>(R.id.imgProduct)

        txtProduct.text = products[position]

        // Attribution de l'image correspondant au produit
        if (position < images.size) {
            imgProduct.setImageResource(images[position])
        } else {
            imgProduct.setImageResource(android.R.drawable.ic_menu_compass)
        }

        return view
    }
}