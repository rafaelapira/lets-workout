package com.letsworkout.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.Window;
import android.graphics.Color;
import java.io.InputStream;
import java.util.Locale;

public class MainActivity extends Activity {
    private WebView webView;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(Color.rgb(7,18,38));
        getWindow().setNavigationBarColor(Color.rgb(7,18,38));
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(true);
        settings.setLoadsImagesAutomatically(true);
        webView.setBackgroundColor(Color.rgb(7,18,38));
        webView.setWebViewClient(new WebViewClient() {
            @Override public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                String prefix = "https://appassets.androidplatform.net/";
                if (url.startsWith(prefix)) {
                    String path = url.substring(prefix.length());
                    if (path.isEmpty()) path = "index.html";
                    try {
                        InputStream stream = getAssets().open(path);
                        String lower = path.toLowerCase(Locale.ROOT);
                        String mime = lower.endsWith(".html") ? "text/html" : lower.endsWith(".js") ? "application/javascript" : lower.endsWith(".css") ? "text/css" : lower.endsWith(".json") || lower.endsWith(".webmanifest") ? "application/json" : lower.endsWith(".png") ? "image/png" : "application/octet-stream";
                        return new WebResourceResponse(mime, "UTF-8", stream);
                    } catch (Exception ignored) { }
                }
                return super.shouldInterceptRequest(view, request);
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        setContentView(webView);
        // HTTPS-like local origin keeps IndexedDB available while every resource is served from APK assets.
        webView.loadUrl("https://appassets.androidplatform.net/index.html");
    }
    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack(); else super.onBackPressed();
    }
    @Override protected void onDestroy() {
        if (webView != null) { webView.destroy(); webView = null; }
        super.onDestroy();
    }
}
