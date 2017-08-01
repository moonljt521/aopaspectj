package yundonghui.aop.library.trace;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: moon
 * created on:
 * description:  校验登陆注解
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD ,ElementType.ANNOTATION_TYPE ,ElementType.TYPE})
public @interface CheckLoginTrace {

}