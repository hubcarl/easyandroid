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
import com.easy.team.core.NativeWebViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<Map<String, Object>> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        listView = (ListView)view.findViewById(R.id.lv);
        listView.setOnItemClickListener(this);
        dataList = getListData();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                dataList,
                R.layout.find_listview_item,
                new String[]{ "title", "info", "img"},
                new int[]{ R.id.title, R.id.info, R.id.img });
        listView.setAdapter(adapter);
        return view;
    }

    private List<Map<String, Object>> getListData() {
        List<Map<String, Object>> list = new ArrayList();

        Map<String, Object> map1 = new HashMap();
        map1.put("title", "Egg + Vue 工程化解决方案");
        map1.put("info", "Egg + Vue 服务端渲染&前端渲染方案");
        map1.put("url", "https://www.yuque.com/easy-team/egg-react");
        map1.put("img", R.mipmap.ic_launcher);
        list.add(map1);

        Map<String, Object> map2 = new HashMap();
        map2.put("title", "Egg + React 工程化解决方案");
        map2.put("info", "Egg + React 服务端渲染&前端渲染方案");
        map2.put("img", R.mipmap.ic_launcher);
        map2.put("url", "https://www.yuque.com/easy-team/egg-vue");
        list.add(map2);

        Map<String, Object> map3 = new HashMap();
        map3.put("title", "前端工程解决方案");
        map3.put("info", "Vue/React/HTML/Weex工程方案");
        map3.put("img", R.mipmap.ic_launcher);
        map3.put("url", "https://www.yuque.com/easy-team/frontend");
        list.add(map3);

        Map<String, Object> map4 = new HashMap();
        map4.put("title", "easywebpack");
        map4.put("info", "Webpack 前端工程化解决方案");
        map4.put("img", R.mipmap.ic_launcher);
        map4.put("url", "https://www.yuque.com/easy-team/easywebpack");
        list.add(map4);

        Map<String, Object> map5 = new HashMap();
        map5.put("title", "eggjs");
        map5.put("info", "基于 koa 的企业级 Node.js 框架");
        map5.put("img", R.mipmap.ic_launcher);
        map5.put("url", "https://eggjs.org/zh-cn/index.html");
        list.add(map5);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("--onItemClick--", "click" + position);
        Map<String, Object> map = dataList.get(position);
        Intent intent = new Intent(getActivity(), NativeWebViewActivity.class);
        intent.putExtra("title", map.get("title").toString());
        intent.putExtra("url", map.get("url").toString());
        startActivity(intent);
    }
}
