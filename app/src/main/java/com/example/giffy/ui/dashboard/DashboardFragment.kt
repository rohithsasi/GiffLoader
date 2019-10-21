package com.example.giffy.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.giffy.R
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        setHasOptionsMenu(true)

        dashboardViewModel.text.observe(this, Observer {
           initAndLoadWebView(web_view_holder ,it)
        })
        return root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initAndLoadWebView(webView: WebView, url :String) {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowContentAccess = false
        webSettings.allowFileAccess = false
        webSettings.allowFileAccessFromFileURLs = false
        webSettings.allowUniversalAccessFromFileURLs = false

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Toast.makeText(
                    context,
                    "No Internet. Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        webView.loadUrl(url)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        activity!!.invalidateOptionsMenu()
        menu.findItem(R.id.app_bar_search).setVisible(false)
    }

}