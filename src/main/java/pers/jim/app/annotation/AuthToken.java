package pers.jim.app.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;


//https://www.cnblogs.com/gmq-sh/p/4798194.html
@Retention(RetentionPolicy.RUNTIME) //保持到执行结束，可以用反射获取
@Target(ElementType.METHOD) //只能注释函数
public @interface AuthToken {
}
