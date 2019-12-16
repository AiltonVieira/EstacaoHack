package br.com.estacaohackgp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        imvLogo.animate().alpha(1f).duration = 2000
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            finish()
        },3000)
    }
}
