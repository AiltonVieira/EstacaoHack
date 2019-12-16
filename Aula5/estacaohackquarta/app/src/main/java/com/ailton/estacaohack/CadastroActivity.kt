package com.ailton.estacaohack

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro.btnCadastro

class CadastroActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Lista de sexo
        val genero = this.resources.getStringArray(R.array.gender)

        //Criando o SharedPreferences
        val minhaPreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)

        //Criando o Editor do SharedPreferences
        val meuEditor = minhaPreferencia.edit()

        //Criando o Adaptador do Spinner
        val adapterGenero = ArrayAdapter(this@CadastroActivity, R.layout.spinner_layout, genero)

        //Adicionando a lista no Spinner
        spnSexo.adapter = adapterGenero

        btnCadastro.setOnClickListener {
            val nome = edtNome.text.toString().trim()
            val sobrenome = edtSobrenome.text.toString().trim()
            val email = edtEmail.text.toString().toLowerCase().trim()
            val senha = edtPassword.text.toString().trim()


            //Validar campos vazios
            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this@CadastroActivity, getString(R.string.fillFields), Toast.LENGTH_SHORT).show()
            } else if(spnSexo.selectedItemPosition == 0){
                Toast.makeText(this@CadastroActivity, getString(R.string.fillGender), Toast.LENGTH_SHORT).show()
            } else {
                meuEditor.putString("nome", nome).apply()
                meuEditor.putString("sobrenome", sobrenome).apply()
                meuEditor.putString("email", email).apply()
                meuEditor.putString("senha", senha).apply()
                meuEditor.putString("sexo", spnSexo.selectedItem.toString()).apply()

                //Toast.makeText(this@CadastroActivity, getString(R.string.registerSuccess), Toast.LENGTH_SHORT).show()

                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle(getString(R.string.success))
                    .setMessage(getString(R.string.registerSuccess))
                    .setPositiveButton(getString(R.string.ok)){
                        _,_ -> onBackPressed()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            }
        }

    }

}
