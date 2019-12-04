package com.ailton.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val minhapreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)

        //Criando o clique do Botão Entrar
        btnEntrar.setOnClickListener {

            // recuperando os valores digitados
            val usuario = edtUsuario.text.toString().trim()
            val senha = edtSenha.text.toString().trim()

            //Verificando se o usuário ou password estão corretos

            if (usuario.isEmpty() || senha.isEmpty()) {
                txvResultado.text = getString(R.string.emptyLogin)
            } else if (usuario == minhapreferencia.getString("nome", "") && senha == minhapreferencia.getString("senha", "")) {
                txvResultado.text = getString(R.string.userLogged)
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            } else {
                txvResultado.text = getString(R.string.incorrectUser)
            }
        }

        btnCadastro.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
        }
    }
}

