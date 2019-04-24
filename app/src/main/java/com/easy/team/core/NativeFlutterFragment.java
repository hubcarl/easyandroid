package com.easy.team.core;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.team.module.CompareActivity;
import com.easy.team.module.NativeActivity;

import java.util.HashMap;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;


public class NativeFlutterFragment extends Fragment {

    private FlutterView flutterView;

    public static Fragment newInstance(String route) {
        NativeFlutterFragment fragment = new NativeFlutterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("route", route);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static Fragment newInstance(String route, Bundle bundle) {
        NativeFlutterFragment fragment = new NativeFlutterFragment();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("route", route);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return this.getFlutterView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.registerMethodChannel(this.getFlutterView());
        MessageChannel.setFlutterView(this.flutterView);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d("--onHiddenChanged--", String.valueOf(hidden));
        super.onHiddenChanged(hidden);
        if (!hidden) {
            MessageChannel.setFlutterView(this.flutterView);
        }
    }

    protected void registerMethodChannel(FlutterView flutterView) {
        new MethodChannel(flutterView, "com.happy/navigation").setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, MethodChannel.Result result) {

                        Log.i("--MethodChannel--", "method:"  + call.method);

                        if (call.method.equals("pushRoute")) {
                            String route = call.argument("route");
                            HashMap args = (HashMap)call.arguments;
                            Log.i("--MethodChannel--", "route:"  + route);
                            if ("native".equals(route)) {
                                Intent intent = new Intent(getActivity(), NativeActivity.class);
                                intent.putExtra("args", args);
                                startActivity(intent);
                            } else if ("web".equals(route)) {
                                Intent intent = new Intent(getActivity(), NativeWebViewActivity.class);
                                intent.putExtra("title", args.get("title").toString());
                                intent.putExtra("url", args.get("url").toString());
                                startActivity(intent);
                            } else if ("compare".equals(route)) {
                                Intent intent = new Intent(getActivity(), CompareActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getActivity(), NativeFlutterActivity.class);
                                intent.setAction(Intent.ACTION_RUN);
                                intent.putExtra("route", route);
                                intent.putExtra("args", args);
                                startActivity(intent);
                            }
                            result.success("success");
                        } else if (call.method.equals("pop")) {
                            result.success("success");
                        } else {
                            result.notImplemented();
                        }
                    }
                }
        );
    }

    public FlutterView getFlutterView(){
        if (this.flutterView == null) {
            String route = getArguments().getString("route");
            this.flutterView = Flutter.createView(getActivity(), getLifecycle(), route);
            // fix black screen
            this.flutterView.setZOrderOnTop(true);
            // this.flutterView.setZOrderMediaOverlay(true);
            this.flutterView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        }
        return this.flutterView;
    }
}
