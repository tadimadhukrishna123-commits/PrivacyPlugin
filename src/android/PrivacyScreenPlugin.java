package com.bank.privacyscreen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

public class PrivacyScreenPlugin extends CordovaPlugin {

    private View overlay;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        final Activity activity = cordova.getActivity();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.setRecentsScreenshotEnabled(false);
        }
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);

        final Activity activity = cordova.getActivity();

        activity.runOnUiThread(() -> {

            if (overlay == null) {

                overlay = new FrameLayout(activity);
                overlay.setBackgroundColor(Color.BLACK);

                ViewGroup decor =
                        (ViewGroup) activity.getWindow().getDecorView();

                decor.addView(
                        overlay,
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT)
                );
            }

        });
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);

        final Activity activity = cordova.getActivity();

        activity.runOnUiThread(() -> {

            if (overlay != null) {

                ViewGroup parent = (ViewGroup) overlay.getParent();

                if (parent != null) {
                    parent.removeView(overlay);
                }

                overlay = null;
            }

        });
    }
}
