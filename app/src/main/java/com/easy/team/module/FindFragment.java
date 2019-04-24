package com.easy.team.module;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.easy.team.R;
import com.easy.team.core.NativeFlutterActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FindFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        listView = (ListView)view.findViewById(R.id.lv);
        listView.setOnItemClickListener(this);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                getListData(),
                R.layout.find_listview_item,
                new String[]{ "title", "info", "img"},
                new int[]{ R.id.title, R.id.info, R.id.img });
        listView.setAdapter(adapter);
        return view;
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
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case 1:
                Intent intent = new Intent(getActivity(), NativeFlutterActivity.class);
                intent.setAction(Intent.ACTION_RUN);
                intent.putExtra("route", "about");
                startActivity(intent);
                break;
            case 2:
                break;
        }
    }
}
