package killcode.wzs.springbootdemo.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {

    String key() default "";

    int expire() default -1;

    TimeUnit timeUnit() default TimeUnit.MINUTES;
}
