package com.example.myprinttest;

import java.io.File;

import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity
        implements OnClickListener {

    static final int REQUEST_CAPTURE_IMAGE = 100;
    WebView webView;
    Button button1;
    File webFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view, String url) {
                return false;
            }
        });
        button1 = (Button)findViewById(R.id.button1);
        webView.loadUrl("http://www.google.com/");
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button1){
            sendContent();
        }
    }

    protected void sendContent(){
        try {
            PrintManager printManager = (PrintManager)
                    getBaseContext().getSystemService(Context.PRINT_SERVICE);
            PrintDocumentAdapter adapter =
                    webView.createPrintDocumentAdapter();
            PrintJob printJob = printManager.print(
                    "\"" + webView.getTitle() + "\"",
                    adapter, new PrintAttributes.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}