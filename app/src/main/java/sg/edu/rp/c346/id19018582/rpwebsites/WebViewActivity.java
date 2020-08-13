package sg.edu.rp.c346.id19018582.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.rp.c346.id19018582.rpwebsites.R;

public class WebViewActivity extends AppCompatActivity {
    WebView wv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        wv = findViewById(R.id.webView);

        Intent intentReceive = getIntent();
        String url = intentReceive.getStringExtra("url");

        wv.setWebViewClient(new WebViewClient());

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);

        wv.loadUrl(url);
    }
}
