package killcode.wzs.springbootdemo.cache;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * Created by wangzhongshan on 2017/8/24.
 */
public class CacheUtil {

    private static final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    public static String genCacheKey(Object target, Method method, Object[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append(target.getClass().getName());
        sb.append(".");
        sb.append(method.getName());
        sb.append("(");
        String[] parameterNames = discoverer.getParameterNames(method);
        for (int i = 0; i < args.length; i++) {
            sb.append(parameterNames[i]).append(":").append(args[i].toString());
            if (i < args.length - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
