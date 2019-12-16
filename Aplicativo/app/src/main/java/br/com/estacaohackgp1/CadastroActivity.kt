package br.com.estacaohackgp1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Lista de sexo
        val genero: Array<String> = arrayOf("Selecione um sexo", "Masculino", "Feminino")

        //Criando o SharedPreferences
        val minhaPreferencia = getSharedPreferences("usuarios", Context.MODE_PRIVATE)

        //Criando o Editor do SharedPreferences
        val meuEditor = minhaPreferencia.edit()

        //Criando o Adaptador do Spinner
        val adapterGenero = ArrayAdapter(this@CadastroActivity, R.layout.spinner_layout, genero)

        //Adicionando a lista no Spinner
        spnSexo.adapter = adapterGenero

        btnCadastrar.setOnClickListener {
            val nome = edtUsuario.text.toString().trim()
            val senha = edtSenha.text.toString().trim()


            //Validar campos vazios
            if(nome.isEmpty() || senha.isEmpty()){
                Toast.makeText(this@CadastroActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if(spnSexo.selectedItemPosition == 0){
                Toast.makeText(this@CadastroActivity, "Selecione um sexo", Toast.LENGTH_SHORT).show()
            } else {
                meuEditor.putString("nome", nome).apply()
                meuEditor.putString("senha", senha).apply()
                meuEditor.putString("sexo", spnSexo.selectedItem.toString()).apply()

                //Toast.makeText(this@CadastroActivity, getString(R.string.registerSuccess), Toast.LENGTH_SHORT).show()

                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Sucesso")
                    .setMessage("Cadastro efetuado com sucesso")
                    .setPositiveButton("Ok"){
                            _,_ -> finish()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            }
        }
    }
}
