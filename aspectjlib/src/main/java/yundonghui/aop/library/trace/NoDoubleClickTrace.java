package yundonghui.aop.library.trace;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by moon on 17/4/25.
 *  防连击功能注解
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD ,ElementType.ANNOTATION_TYPE ,ElementType.TYPE})
public @interface NoDoubleClickTrace {

}