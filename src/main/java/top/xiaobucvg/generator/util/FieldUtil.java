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
        char[] chars = source.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            chars[0] = (char) (chars[0] + 32);
        }
        return new String(chars);
    }

    /***
     * 一个下划线命名的字符串转为驼峰命名
     */
    public static String convertName(String source) {
        StringBuilder res = new StringBuilder();
        String[] arr = source.split("_");
        res = new StringBuilder(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            res.append(firstWordToUpper(arr[i]));
        }
        return res.toString();
    }

    /***
     * 点号连接的字符串只取最后一个点号之后的内容
     */
    public static String getLastField(String source) {
        int index = source.lastIndexOf(".");
        return source.substring(index + 1);
    }
}
