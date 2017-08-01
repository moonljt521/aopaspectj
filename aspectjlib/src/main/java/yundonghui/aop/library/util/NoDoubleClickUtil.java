package yundonghui.aop.library.util;


import android.util.Log;

/**
 * author: moon
 * created on:
 * description: 防止连击的工具类
 */
public class NoDoubleClickUtil {

    private static long lastClickTime = 0;
    private final static int SPACE_TIME = 500;

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime >
                SPACE_TIME) {
            isClick2 = true;
        } else {
            isClick2 = false;
        }
        lastClickTime = currentTime;
        Log.i("moon","是否可以连点 = " + isClick2);
        return isClick2;
    }
}