package com.easy.team.core;

import android.util.Log;

import io.flutter.view.FlutterView;

public class MessageChannel {

    private FlutterView flutterView;
    private static MessageChannel messageChannel = new MessageChannel();

    public static FlutterView getFlutterView() {
        Log.d("--MessageChannel:get--", messageChannel.flutterView == null ? "null" : "object" );
        return messageChannel.flutterView;
    }

    public static void setFlutterView(FlutterView flutterView) {
        Log.d("--setFlutterView:set--", flutterView == null ? "null" : "object" );
        messageChannel.flutterView = flutterView;
    }

}
