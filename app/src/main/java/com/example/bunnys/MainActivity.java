package com.example.bunnys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private FadingTextView fadingTextView;
WebView webView;


ProgressBar progressBarWeb;
ProgressDialog progressDialog;
RelativeLayout relativeLayout;
SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setEnableSmoothTransition(true);

        webView.loadUrl("https://www.soriana.co.ke");

        swipeRefreshLayout = ( SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished (WebView view, String url){
                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }


            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {


                webView.loadUrl("file:///android_asset/err.html");

            }


        });


        String[] texts = {"fashion as unique as you are", ""};
        FadingTextView FTV = (FadingTextView) findViewById(R.id.fadingTextView);
        FTV.setTexts(texts); //You can use an array resource or a string array as the parameter


        //For text change once every hour
        FTV.setTimeout(160000000, FadingTextView.MINUTES);


    }


    @Override
    protected void onRestart() {

        super.onRestart();
    }

//    public class myWebClient extends WebViewClient {
//        @Override
//        public void onPageStarted (WebView view, String url, Bitmap favicon){
//            super.onPageStarted(webView, url, favicon);
//        }
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url){
//            view.loadUrl(url);
//            return true;
//        }
//
//    }

    @Override
        public void onBackPressed(){
            if (webView.canGoBack()){
                webView.goBack();
            }
            else{
                super.onBackPressed();
            }

        }



}
