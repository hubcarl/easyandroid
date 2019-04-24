package com.easy.team.module;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.easy.team.R;
import com.easy.team.core.EasyNativeActivity;
import com.easy.team.core.NativeFlutterActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareActivity extends EasyNativeActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_flutter_compare);
        listView = findViewById(R.id.lv);
        listView.setOnItemClickListener(this);
        SimpleAdapter adapter = new SimpleAdapter(this,
                getListData(),
                R.layout.find_listview_item,
                new String[]{ "title", "info", "img"},
                new int[]{ R.id.title, R.id.info, R.id.img });
        listView.setAdapter(adapter);
    }

    private List<Map<String, Object>> getListData() {
        List<Map<String, Object>> list = new ArrayList();

        Map<String, Object> map1 = new HashMap();
        map1.put("title", "打开 Native 页面");
        map1.put("info", "Native vs Flutter");
        map1.put("img", R.mipmap.ic_launcher);

        list.add(map1);

        Map<String, Object> map2 = new HashMap();
        map2.put("title", "打开 Flutter 页面 ");
        map2.put("info", "Native vs Flutter");
        map2.put("img", R.mipmap.ic_flutter);
        list.add(map2);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("--onItemClick--", "click" + position);
        switch (position) {
            case 0:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case 1:
                Intent intent = new Intent(this, NativeFlutterActivity.class);
                intent.setAction(Intent.ACTION_RUN);
                intent.putExtra("route", "about");
                startActivity(intent);
                break;
            case 2:
                break;
        }
    }
}
