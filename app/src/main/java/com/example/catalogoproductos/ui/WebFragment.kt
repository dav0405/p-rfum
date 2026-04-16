package com.example.catalogoproductos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.catalogoproductos.R

class WebFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_web, container, false)
        
        val webView = view.findViewById<WebView>(R.id.webView)
        val txtUrlDisplay = view.findViewById<TextView>(R.id.txtUrlDisplay)
        val btnGoWeb = view.findViewById<View>(R.id.btnGoWeb)
        
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        
        val desktopUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
        settings.userAgentString = desktopUserAgent

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                txtUrlDisplay.text = url
            }
        }
        
        webView.loadUrl("https://www.dior.com/es_sam")
        
        btnGoWeb.setOnClickListener {
            webView.reload()
        }
        
        return view
    }
}
