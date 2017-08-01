package yundonghui.aop.library.aspectj;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yundonghui.aop.library.trace.PermissionTrace;

/**
 * author: moon
 * created on: 17/6/7 下午5:23
 * description:  权限切片（android6.0以上危险权限）
 */
@Aspect
public class PermissionAspectJ {

    private static final int MY_PERMISSIONS_REQUEST_PERMISSION = 101;

    //所有方法已TakePhoto结尾的方法，这里的@annotation(permissionCheck) ，里面一定是首字母小写，否则失效
    private static final String POINTCUT_METHOD_PERMISS_CHECK
            = "execution(@yundonghui.aop.library.trace.PermissionTrace * *TakePhoto(..))" +
            " && @annotation(permissionTrace)";

    @Pointcut(POINTCUT_METHOD_PERMISS_CHECK)
    public void methodAnnotatedWithtakePhoto(PermissionTrace permissionTrace) {
        //Log.e("PermissionCheck", "methodAnnotatedWithtakePhoto");
    }

    //PermissionCheck 参数一个切面函数有的话，所有的Before，after都要有，

    @Around("methodAnnotatedWithtakePhoto(permissionTrace)")
    public Object around(ProceedingJoinPoint joinPoint, PermissionTrace permissionTrace) throws Throwable {
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        if (permissionTrace.permission().length != 0) {
            for (int i = 0; i < permissionTrace.permission().length; i++) {
                if (ContextCompat.checkSelfPermission((Context) joinPoint.getTarget(),
                        permissionTrace.permission()[i])
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.i("moon",className + ":" + methodName + "没有" + permissionTrace.permission()[i] + "的权限执行");
                }
            }
        }
        result = joinPoint.proceed();
        return result;
    }

    @Before("methodAnnotatedWithtakePhoto(permissionTrace)")
    public void before(JoinPoint joinPoint, PermissionTrace permissionTrace) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Log.i("moon",className + ":" + methodName + "on before");
        if (permissionTrace.permission().length == 0) {
            return;
        }
        boolean needRequestPermissions = false;

        List<String> stringList = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 
            stringList = new ArrayList<>(Arrays.asList(permissionTrace.permission()));
            for (int i = 0; i < permissionTrace.permission().length; i++) {


                if (joinPoint.getTarget() instanceof FragmentActivity) {
                    if (ContextCompat.checkSelfPermission((Context) joinPoint.getTarget(),
                            permissionTrace.permission()[i])
                            != PackageManager.PERMISSION_GRANTED) {
                        needRequestPermissions = true;
                    } else {
                        stringList.remove(permissionTrace.permission()[i]);//不用申请这个权限，移除掉
                    }
                }
            }
        }

        if (needRequestPermissions) {
            String[] needToRequestPermission = new String[stringList.size()];
            ActivityCompat.requestPermissions((Activity) joinPoint.getTarget(),
                    stringList.toArray(needToRequestPermission),
                    MY_PERMISSIONS_REQUEST_PERMISSION);
        }
    }

    @After("methodAnnotatedWithtakePhoto(permissionTrace)")
    public void after(JoinPoint joinPoint, PermissionTrace permissionTrace) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
    }
}
