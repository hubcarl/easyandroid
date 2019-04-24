package com.easy.team.test;

import android.os.Bundle;
import android.util.Log;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class NativeFlutterActivity extends FlutterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        registerMethodChannel();
    }

    private void registerMethodChannel() {
        new MethodChannel(getFlutterView(), "com.happy/navigation").setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                        Log.i("--channel--", call.method);
                        if (call.method.equals("pushRoute")) {
                            result.success("success");
                        } else {
                            result.notImplemented();
                        }
                    }
                }
        );
    }
}
