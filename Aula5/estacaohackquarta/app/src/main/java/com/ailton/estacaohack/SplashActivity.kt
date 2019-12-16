package com.ailton.estacaohack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Criando a Splash:
        Handler().postDelayed({

            //Criando a Intent
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))

            finish()
        },3000)
    }
}
