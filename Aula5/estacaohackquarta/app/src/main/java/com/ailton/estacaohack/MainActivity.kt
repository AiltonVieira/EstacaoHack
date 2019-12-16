package com.ailton.estacaohack

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val minhapreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)

        val nome = minhapreferencia.getString("nome", "Erro preferencia")
        val sobrenome = minhapreferencia.getString("sobrenome", "Erro preferencia")
        val email = minhapreferencia.getString("email", "Erro preferencia")
        val sexo = minhapreferencia.getString("sexo", "Erro preferencia")

        if(Locale.getDefault().language.equals("pt"))
        if(sexo.equals(resources.getStringArray(R.array.gender)[1]))
            txvWelcome.text = getString(R.string.welcome) + "o"
        else
            txvWelcome.text = getString(R.string.welcome) + "a"

        txvNome.text = "$nome $sobrenome"
        txvEmail.text = email
        txvSexo.text = sexo

        btnSair.setOnClickListener {
            finish() }
    }
}
