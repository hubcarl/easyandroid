package com.easy.team.module;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.easy.team.R;
import com.easy.team.core.EasyNativeActivity;
import com.easy.team.core.MessageChannel;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class NativeActivity extends EasyNativeActivity implements MethodChannel.MethodCallHandler {

    public static String CHANNEL = "com.happy.message/notify";

    private MethodChannel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        setActionToolBar();
        channel = new MethodChannel(MessageChannel.getFlutterView(), CHANNEL);

        Map<String,String> map = new HashMap<String, String>();
        map.put("from", "native");

        notifyFlutter("getFlutterVersion", map);
    }

    protected void notifyFlutter(String method, Object args) {
        channel.invokeMethod(method, args, new MethodChannel.Result() {
            @Override
            public void success(@Nullable Object o) {
                Log.d("--NativeActivity:success--", o.toString());
                // Toast.makeText(NativeActivity.this, "message:" + o.toString(), Toast.LENGTH_SHORT).show();
                AlertDialog dialog = null;
                final AlertDialog.Builder builder = new AlertDialog.Builder(NativeActivity.this);
                builder.setIcon(R.drawable.ic_menu_send);
                builder.setTitle("消息");
                builder.setMessage("Native收到来自Flutter的消息" + o.toString());
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.show();
            }

            @Override
            public void error(String s, @Nullable String s1, @Nullable Object o) {
                Log.d("--NativeActivity:error--", s1);
            }

            @Override
            public void notImplemented() {
                Log.d("--NativeActivity:notImplemented--","");
            }
        });
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

    }
}
