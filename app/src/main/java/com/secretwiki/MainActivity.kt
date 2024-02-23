package com.secretwiki

import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Funkcja do wyświetlania "brak strony"
    private fun showNoPageContent(webView: WebView) {
        // Tutaj możesz obsłużyć brak dostępu do strony lub brak dostępu do sieci
        // Na przykład, możesz ustawić alternatywną zawartość w WebView
        webView.loadData("SecretWiki not connected", "text/html", "UTF-8")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.WebView)
        val webSettings: WebSettings = webView.settings

        // Wyłącz pamięć podręczną
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE

        //webSettings.userAgentString = "secretwiki_browser"

        // Ustawienie WebViewClient tylko raz
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)

                // Sprawdzamy rodzaj błędu
                if (error?.errorCode == ERROR_HOST_LOOKUP || error?.errorCode == ERROR_CONNECT || error?.errorCode == ERROR_TIMEOUT) {
                    // Błąd związany z brakiem dostępu do sieci lub strony
                    showNoPageContent(webView)
                }
            }
        }

        webView.loadUrl("https://secretwiki.noweenergie.org")
    }
}
