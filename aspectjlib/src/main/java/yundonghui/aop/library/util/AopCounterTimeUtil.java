package yundonghui.aop.library.util;

import java.util.concurrent.TimeUnit;

/**
 * author: moon
 * created on: 17/6/8 下午12:32
 * description: 测试方法时长的 工具类
 */
public class AopCounterTimeUtil {

    private long startTime;
    private long endTime;
    private long elapsedTime;

    public AopCounterTimeUtil() {
    }

    private void reset() {
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;
    }

    public void start() {
        reset();
        startTime = System.nanoTime();
    }

    public void stop() {
        if (startTime != 0) {
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
        } else {
            reset();
        }
    }

    public long getTotalTimeMillis() {
        return (elapsedTime != 0) ? TimeUnit.NANOSECONDS.toMillis(endTime - startTime) : 0;
    }

}
