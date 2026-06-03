package com.example.jiohotstartv;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        myWebView = findViewById(R.id.tvWebView);
        WebSettings webSettings = myWebView.getSettings();

        // JavaScript और वीडियो प्लेबैक के लिए जरूरी सेटिंग्स
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        // TV रिमोट से नेविगेशन (Focus) आसान बनाने के लिए
        myWebView.setFocusable(true);
        myWebView.setFocusableInTouchMode(true);

        // एक स्टैंडर्ड Smart TV का User-Agent सेट करना ताकि वेबसाइट TV मोड में खुले
        String tvUserAgent = "Mozilla/5.0 (Large Screen; SmartTV; Linux; Android 5.1.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.210 Safari/537.36";
        webSettings.setUserAgentString(tvUserAgent);

        // ऐप के अंदर ही लिंक खोलने के लिए
        myWebView.setWebViewClient(new WebViewClient());

        // JioHotstar की मुख्य वेबसाइट लोड करें
        myWebView.loadUrl("https://www.jiohotstar.com");
    }

    // TV रिमोट के 'Back' बटन को हैंडल करने के लिए ताकि ऐप एकदम से बंद न हो
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
