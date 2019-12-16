package br.com.estacaohackgp1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //SharedPreferences
        val preferences= getSharedPreferences("usuarios", Context.MODE_PRIVATE)
        val editor = preferences.edit()


        val nome = preferences.getString("nome", "")
        val senha = preferences.getString("senha", "")
        val lembrar = preferences.getBoolean("lembrar", false)

        if(lembrar){
            edtUsuario.setText(nome)
            edtSenha.setText(senha)
            chbLembrar.isChecked = true
        }

        btnEntrar.setOnClickListener {
            if(edtUsuario.text.trim().toString().isEmpty() || edtSenha.text.trim().toString().isEmpty()){
                Toast.makeText(this@LoginActivity, "Campo usuário e/ou senha vazio!", Toast.LENGTH_SHORT).show()
            } else if(edtUsuario.text.trim().toString().equals(nome) && edtSenha.text.trim().toString().equals(senha) ||(edtUsuario.text.toString().equals("admin") && edtSenha.text.toString().equals("admin"))){
                if(chbLembrar.isChecked) {
                    editor.putBoolean("lembrar", true).apply()
                } else {
                    editor.putBoolean("lembrar", false).apply()
                }

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))

            } else {
                Toast.makeText(this@LoginActivity, "Usuário inválido!", Toast.LENGTH_SHORT).show()
            }
        }

        btnSite.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }


    }
}
