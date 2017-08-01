package yundonghui.aop.library.aspectj;


import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import yundonghui.aop.library.util.CheckLoginUtil;
import yundonghui.aop.library.util.NoDoubleClickUtil;

/**
 * author: moon
 * created on: 17/6/7 下午5:23
 * description:  校验登陆 切片
 */
@Aspect
public class CheckLoginAspectJ {

    private String TAG = "CheckLoginAspectJ ";
    private static final String DOUBLE_CLICK_POINT_CUT = "execution(@yundonghui.aop.library.trace.CheckLoginTrace * * (..))";
    private static final String POINT_CALLMETHOD = "execution(@yundonghui.aop.library.trace.CheckLoginTrace * * (..))";
    private static final String POINT_DOUBLE_CLICK = "execution(@yundonghui.aop.library.trace.CheckLoginTrace * * (..))";

    @Pointcut(DOUBLE_CLICK_POINT_CUT)
    public void methodAnnotated() {
    }

    @Pointcut(POINT_CALLMETHOD)
    public void methodCallAnnotated() {
    }

    @Around("methodAnnotated()")
    public Object aronudWeaverPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        String result = "----------------------------->aroundWeaverPoint";
        return result;//替换原方法的返回值
    }

    @Before("methodCallAnnotated()")
    public void beforecall(JoinPoint joinPoint) {
        Log.i("moon","beforecall");
    }


    @Around(POINT_DOUBLE_CLICK)
    public void checkLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (CheckLoginUtil.checkoutLogin()){
            proceedingJoinPoint.proceed();
        }else {
            Log.i("moon","您还未登陆");
        }
    }


}
