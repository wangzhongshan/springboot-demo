package killcode.wzs.springbootdemo.util;

import killcode.wzs.springbootdemo.cache.RedisClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyUtil {

    public static Properties getProperties(String file) {
        Objects.requireNonNull(file);
        Properties props = new Properties();
        try (InputStream inputStream = RedisClient.class.getClassLoader()
                                                        .getResourceAsStream(file)) {
            props.load(inputStream);
        } catch (IOException e) {
            throw new ConfigException(String.format("读取配置文件%s异常", file), e);
        }
        return props;
    }
}
