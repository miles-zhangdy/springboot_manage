package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	String logtype() default ""; //模块名称
	String typename() default ""; //业务方法名称
	String author() default ""; //作者
	String logconstants() default ""; //log4j的模块
}