package br.com.estacaohackgp1

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View.*
import android.webkit.WebChromeClient
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

        web.webViewClient = MyWebClient()
        web.webChromeClient = MyWebChromeClient()
        web.settings.builtInZoomControls = true
        web.settings.displayZoomControls = false
        web.settings.javaScriptEnabled = true


        edtUrl.setOnKeyListener { v, keyCode, event ->
            // If the event is a key-down event on the "enter" button
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Perform action on key press
                web.loadUrl(edtUrl.text.trim().toString())
                true
            }
            false
        }


        web.loadUrl("https://www.google.com.br")
        pgbLoad.max = 100

        imvBack.setOnClickListener {
            if (web.canGoBack())
                web.goBack()
        }
        imvHome.setOnClickListener {
            web.loadUrl("https://www.google.com.br")
        }
        imvForward.setOnClickListener {
            if (web.canGoForward())
                web.goForward()
        }
        btnIr.setOnClickListener {
            var url = edtUrl.text.trim().toString()
            if (url.isEmpty()){

                Toast.makeText(this@MainActivity, "A Url está vazia", Toast.LENGTH_LONG).show()

            } else if (url.indexOf("www") != -1){

                if (url.indexOf("http") != -1){

                    web.loadUrl(url)

                }
                web.loadUrl("http://$url")

            } else{

                web.loadUrl("https://www.google.com/search?q=$url")

            }
        }

    }

    inner class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            edtUrl.setText(url)
        }

    }

    inner class MyWebChromeClient : WebChromeClient() {
        override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
            super.onReceivedIcon(view, icon)
            imvPage.setImageBitmap(icon)
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            pgbLoad.visibility = VISIBLE
            txvUrl.visibility = GONE
            if (newProgress == 100) {
                pgbLoad.visibility = GONE
                txvUrl.visibility = VISIBLE
            } else {
                pgbLoad.progress = newProgress
            }
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            txvUrl.text = title
        }

    }
}