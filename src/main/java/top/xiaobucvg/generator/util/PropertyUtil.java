package top.xiaobucvg.generator.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            ClassLoader classLoader = DBUtil.class.getClassLoader();
            InputStream ins = classLoader.getResourceAsStream("beanconfig.properties");
            properties.load(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        String value = properties.getProperty(key);
        return value == null ? "" : value;
    }
}
