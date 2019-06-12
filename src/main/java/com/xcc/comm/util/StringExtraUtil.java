package com.xcc.comm.util;

public class StringExtraUtil {

    /**
     * 首字母大写{效率低}
     * @param str
     * @return
     */
    @Deprecated
    public static String upperFirstLetter(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char)(ch[0] - 32);
        }
        return new String(ch);
    }
}
