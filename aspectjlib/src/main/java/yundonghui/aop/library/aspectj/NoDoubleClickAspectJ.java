package yundonghui.aop.library.aspectj;


import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import yundonghui.aop.library.util.NoDoubleClickUtil;

/**
 * author: moon
 * created on: 17/6/7 下午5:23
 * description:  防止连击的 切片
 */
@Aspect
public class NoDoubleClickAspectJ {

    private String TAG = "TAG ";
    private static final String DOUBLE_CLICK_POINT_CUT = "execution(@yundonghui.aop.library.trace.NoDoubleClickTrace * * (..))";
    private static final String POINT_CALLMETHOD = "execution(@yundonghui.aop.library.trace.NoDoubleClickTrace * * (..))";
    private static final String POINT_DOUBLE_CLICK = "execution(@yundonghui.aop.library.trace.NoDoubleClickTrace * * (..))";

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
        Log.i("moon","methodCallAnnotated -------- beforecall");
    }


    @Around(POINT_DOUBLE_CLICK)
    public void onClickListener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (NoDoubleClickUtil.isDoubleClick()){
            proceedingJoinPoint.proceed();
        }
    }

//    @Around(POINT_DOUBLE_CLICK)
//    public void onClick(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        if (NoDoubleClickUtil.isDoubleClick()){
//            proceedingJoinPoint.proceed();
//        }
//    }


}
