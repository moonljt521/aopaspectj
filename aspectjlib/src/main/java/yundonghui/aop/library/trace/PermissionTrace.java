package yundonghui.aop.library.trace;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * author: moon
 * created on:
 * description:  权限注解
 */
@Target({METHOD}) @Retention(RUNTIME)
public @interface PermissionTrace {
    public String [] permission();
}