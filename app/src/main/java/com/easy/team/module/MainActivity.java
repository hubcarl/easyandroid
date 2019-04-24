package com.easy.team.module;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.easy.team.R;
import com.easy.team.core.NativeFlutterFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private int [] layoutTabIds = { R.id.layout_tab_home, R.id.layout_tab_category, R.id.layout_tab_find, R.id.layout_tab_setting };
    private int [] btnTabIds = { R.id.btn_tab_home, R.id.btn_tab_category, R.id.btn_tab_find, R.id.btn_tab_setting };
    private int [] tabNormalIconIds = { R.drawable.ic_tab_home_normal, R.drawable.ic_tab_category_normal, R.drawable.ic_tab_find_normal, R.drawable.ic_tab_setting_normal };
    private int [] tabHoverIconIds = { R.drawable.ic_tab_home_hover, R.drawable.ic_tab_category_hover, R.drawable.ic_tab_find_hover, R.drawable.ic_tab_setting_hover };

    private AppBarLayout appBarLayout;
    private List<Fragment> fragmentList = Arrays.asList(null, null, null, null);
    private List<ImageButton> imageButtonList = new ArrayList<>(4);
    private Fragment currentFragment = null;

    private void initView() {
        for( int id: layoutTabIds) {
            findViewById(id).setOnClickListener(this);
        }
        for( int id: btnTabIds) {
            ImageButton btnTab = findViewById(id);
            btnTab.setOnClickListener(this);
            imageButtonList.add(btnTab);
        }
    }


    private void init(){
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
        switchTab(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void switchTab(int tabIndex) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        currentFragment = fragmentList.get(tabIndex);
        if (currentFragment == null ) {
            switch (tabIndex) {
                case 0:
                    currentFragment = new HomeFragment();
                    break;
                case 1:
                    currentFragment = NativeFlutterFragment.newInstance("github");
                    break;
                case 2:
                    currentFragment = NativeFlutterFragment.newInstance("document");
                    break;
                case 3:
                    currentFragment = NativeFlutterFragment.newInstance("profile");
                    break;
            }
            fragmentList.set(tabIndex, currentFragment);
            transaction.add(R.id.container, currentFragment);
        } else {
            transaction.show(currentFragment);
        }

        imageButtonList.get(tabIndex).setImageResource(tabHoverIconIds[tabIndex]);
        for( int i=0; i<fragmentList.size(); i++ ){
            if (tabIndex != i){
                if (fragmentList.get(i) !=null ) {
                    transaction.hide(fragmentList.get(i));
                }
                imageButtonList.get(i).setImageResource(tabNormalIconIds[i]);
            }
        }
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        Log.d("---tag", v.getTag() == null ? "empty" : v.getTag().toString());
        int tabIndex = Integer.valueOf(v.getTag().toString());
        switchTab(tabIndex);
    }
}
