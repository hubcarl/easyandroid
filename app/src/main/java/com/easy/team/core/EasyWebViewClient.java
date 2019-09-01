package com.easy.team.core;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EasyWebViewClient extends WebViewClient {

    private NativeWebViewActivity nativeWebViewActivity;

    EasyWebViewClient(NativeWebViewActivity nativeWebViewActivity) {
        super();
        this.nativeWebViewActivity = nativeWebViewActivity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d("--onPageStarted--", url + " " + view.getTitle());
        this.nativeWebViewActivity.updateTitleAndUrl(view.getTitle(), url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.d("--onPageFinished--", url + " " + view.getTitle());
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }
}
