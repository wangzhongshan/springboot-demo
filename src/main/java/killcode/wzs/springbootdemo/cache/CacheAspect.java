package killcode.wzs.springbootdemo.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;

@Aspect
@Component
public class CacheAspect {

    private static final Logger log = LoggerFactory.getLogger(CacheAspect.class);
    private static final RedisClient redisClient = RedisClient.getInstance();
    private final static ParserConfig defaultParseConfig = new ParserConfig();
    static {
        defaultParseConfig.setAutoTypeSupport(true);
    }

    @Around("@annotation(Cacheable)")
    public Object cacheable(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Cacheable annotation = method.getAnnotation(Cacheable.class);
        Object[] args = joinPoint.getArgs();
        String key;
        if (annotation.key().isEmpty()) {
            key = CacheUtil.genCacheKey(joinPoint.getTarget(), method, args);
        } else {
            key = SPELUtils.parseKey(annotation, method, args);
        }
        String obj = redisClient.get(key);
        if (obj != null) {
            return JSON.parseObject(obj, Object.class, defaultParseConfig);
        } else {
            Object proceed = joinPoint.proceed();
            Duration duration = Duration.ofSeconds(annotation.timeUnit().toSeconds(annotation.expire()));
            String jsonString = JSON.toJSONString(proceed, SerializerFeature.WriteClassName);
            redisClient.set(key, jsonString, duration);
            return proceed;
        }
    }
}
