package me.drakeet.inmessage.utils;

import android.widget.Toast;

import me.drakeet.inmessage.App;

/**
 * Created by drakeet on 9/27/14.
 */
public class ToastUtils{

    private ToastUtils() {
    }

    public static void showShort(int resId) {
        Toast.makeText(App.getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(App.getContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_LONG).show();
    }

}
