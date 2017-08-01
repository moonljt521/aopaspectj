package yundonghui.aop.library.aspectj;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import yundonghui.aop.library.util.AopCounterTimeUtil;

/**
 * author: moon
 * created on: 17/6/8 下午1:47
 * description:  打印日志的 aspect 切片
 */
@Aspect
public class DebugLogAspect {

    private static final String POINTCUT_METHOD =
            "execution(@yundonghui.aop.library.trace.ExecuteDuringTimeTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@yundonghui.aop.library.trace.ExecuteDuringTimeTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {}

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final AopCounterTimeUtil stopWatch = new AopCounterTimeUtil();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

//        Udebug.i(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

        return result;
    }

    /**
     * Create a log message.
     *
     * @param methodName A string with the method name.
     * @param methodDuration Duration of the method in milliseconds.
     * @return A string representing message.
     */
    private static String buildLogMessage(String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();
        message.append("测试执行时间 --> ");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }

}
