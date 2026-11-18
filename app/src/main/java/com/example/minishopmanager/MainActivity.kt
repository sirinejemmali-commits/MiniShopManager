package com.example.minishopmanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUserName = findViewById<EditText>(R.id.etUserName)
        val cbRemember = findViewById<CheckBox>(R.id.cbRemember)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        prefs = getSharedPreferences("ShopPrefs", MODE_PRIVATE)

        // Préremplir le champ utilisateur lors d'une reconnexion
        etUserName.setText(prefs.getString("USER", ""))
        cbRemember.isChecked = prefs.getBoolean("REMEMBER", false)

        btnLogin.setOnClickListener {
            val editor = prefs.edit()
            if (cbRemember.isChecked) {
                editor.putString("USER", etUserName.text.toString())
                editor.putBoolean("REMEMBER", true)
            } else {
                editor.clear()
            }
            editor.apply()

            // Aller vers le catalogue
            val intent = Intent(this, CatalogueActivity::class.java)
            startActivity(intent)
        }
    }
}