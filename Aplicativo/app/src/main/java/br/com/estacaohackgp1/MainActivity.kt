package br.com.estacaohackgp1

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var web = findViewById<WebView>(R.id.wbvNavegador)

        supportActionBar?.setDisplayShowTitleEnabled(false)


        web.webViewClient = myWebClient()
        web.settings.builtInZoomControls = true
        web.settings.displayZoomControls = false
        web.settings.javaScriptEnabled = true

        web.loadUrl("https://www.google.com.br")
        pgbLoad.max = 100

        imvBack.setOnClickListener{
            if(web.canGoBack())
                web.goBack()
        }
        imvHome.setOnClickListener{
            web.loadUrl("https://www.google.com.br")
        }
        imvForward.setOnClickListener{
            if(web.canGoForward())
                web.goForward()
        }
        btnIr.setOnClickListener {
            var url = edtUrl.text.trim().toString()
            if(url.isEmpty())
                Toast.makeText(this@MainActivity, "A Url está vazia", Toast.LENGTH_LONG).show()
            else if(url.indexOf("https") == -1)
                web.loadUrl("https://$url")
            else
                web.loadUrl(url)

        }

    }

    inner class myWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            pgbLoad.progress = view.progress
            pgbLoad.visibility = VISIBLE
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            pgbLoad.visibility = INVISIBLE
            imvPage.setImageBitmap(view?.favicon)
        }

    }
}
