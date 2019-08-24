package top.xiaobucvg.generator.util;

/***
 * 字段处理工具类
 *
 * by Mr.Zhang
 */
public class FieldUtil {
    /***
     * 把首字母大写
     */
    public static String firstWordToUpper(String source) {
        char[] chars = source.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

    /***
     * 把首字母小写
     */
    public static String firstWordToLower(String source) {
        return source;
    }

    /***
     * 一个下划线命名的字符串转为驼峰命名
     */
    public static String convertName(String source) {
        return source;
    }

    /***
     * 点号连接的字符串只取最后一个点号之后的内容
     */
    public static String getLastField(String source) {
        return source;
    }
}
