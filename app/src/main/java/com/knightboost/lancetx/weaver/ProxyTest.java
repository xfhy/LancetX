package com.knightboost.lancetx.weaver;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;

import com.knightboost.lancet.api.Origin;
import com.knightboost.lancet.api.Scope;
import com.knightboost.lancet.api.annotations.Group;
import com.knightboost.lancet.api.annotations.Proxy;
import com.knightboost.lancet.api.annotations.TargetClass;
import com.knightboost.lancet.api.annotations.TargetMethod;
import com.knightboost.lancet.api.annotations.Weaver;

@Weaver
@Group("proxyTest")
public class ProxyTest {

    @Proxy()
    @TargetClass(value = "android.util.Log", scope = Scope.SELF)
    @TargetMethod(methodName = "i")
    public static int replaceLogI(String tag, String msg) {
        msg = msg + "lancet";
        return (int) Origin.call();
    }

    @Proxy()
    @TargetClass(value = "android.graphics.Bitmap", scope = Scope.ALL)
    @TargetMethod(methodName = "createBitmap")
    public static Bitmap createBitmap(int width, int height, Bitmap.Config config) {

        //分析该Bitmap的大小
        float factor = 1;
        if (config.name().equals(Bitmap.Config.ARGB_8888.name())) {
            factor = 4;
        } else if (config.name().equals(Bitmap.Config.ARGB_4444.name()) || config.name().equals(Bitmap.Config.RGB_565.name())) {
            factor = 2;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && config.name().equals(Bitmap.Config.RGBA_F16.name())) {
            factor = 8;
        }
        float size = width * height * factor / (1024f * 1024f);
        Log.d("xfhy666", "这里在创建Bitmap size=" + size + "M");
        //这里如果遇到size过大的可以打印堆栈
        return (Bitmap) Origin.call();
    }

}
