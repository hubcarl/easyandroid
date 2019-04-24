package com.easy.team.core;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.easy.team.R;
import com.easy.team.db.FavoriteDB;

public class NativeWebViewActivity extends EasyNativeActivity {

    private WebView web;
    private TextView tvTitle;
    private String title;
    private String url;
    private FavoriteDB favoriteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        favoriteDB = new FavoriteDB(this);

        tvTitle = findViewById(R.id.title);
        web = findViewById(R.id.web);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        if (title != null ) {
           tvTitle.setText(title);
        }

        WebSettings webSettings = web.getSettings();
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        web.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {
                        web.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title != null) {
                    NativeWebViewActivity.this.title = title;
                    tvTitle.setText(title);
                }
            }
        });
        web.setWebViewClient(new EasyWebViewClient());
        web.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", this.url);
                cm.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "Copy Successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_open_browser:
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(this.url));
                startActivity(intent);
                break;
            case R.id.action_refresh:
                web.reload();
                break;
            case R.id.action_favorite:
                long rowId = favoriteDB.add(title, url);
                if (rowId > -1) {
                    Toast.makeText(getApplicationContext(), "Favorite Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Favorite Failed", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
