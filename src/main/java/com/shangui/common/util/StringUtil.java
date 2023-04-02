package com.shangui.common.util;

import java.util.Random;
import java.util.UUID;

public class StringUtil {

    public static String getRandomByUUID(){
        return UUID.randomUUID().toString();
    }

    private static final String SOURCE_STRING = "0123456789-_abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
    private static final int DEFAULT_LENGTH = 16;

    /**
     * 获取默认长度的随机串，16位
     * @return 随机串
     */
    public static String getRandomString() {
        return createRandomString(SOURCE_STRING, DEFAULT_LENGTH);
    }

    /**
     * 获取指定长度的随机字符串
     * @param length 随机串的长度
     * @return 随机串
     */
    public static String getRandomString(int length) {
        return createRandomString(SOURCE_STRING, length);
    }

    /**
     * 获取随机串
     * @param source 源字符串
     * @param length 随机串的长度
     * @return 随机串
     */
    private static String createRandomString(String source, int length) {
        if (isNullOrEmpty(source)) {
            return "";
        }

        StringBuffer result = new StringBuffer();
        Random random = new Random();

        for(int index = 0; index < length; index++) {
            result.append(source.charAt(random.nextInt(source.length())));
        }

        System.out.println(result.toString());
        return result.toString();
    }

    /**
     * 判断字符串是否为空
     * @param target
     * @return true：空，false：非空
     */
    private static boolean isNullOrEmpty(String target) {
        if (null == target || "".equals(target) || target.isEmpty()) {
            return true;
        }
        return false;
    }

}
