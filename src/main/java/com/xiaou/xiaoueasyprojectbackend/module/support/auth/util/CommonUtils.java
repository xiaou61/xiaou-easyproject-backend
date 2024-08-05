package com.xiaou.xiaoueasyprojectbackend.module.support.auth.util;

import cn.hutool.core.util.RandomUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.constants.NumberConstants;


import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * @Description: 全局公共工具类
 * @Author: sxgan
 * @Date: 2024/3/1 15:11
 * @Version: 1.0
 **/
public class CommonUtils {
    
    /**
     * 生成8位的随机验证码
     *
     * @return
     */
    public static String generateCaptcha() {
        String captcha = RandomUtil.randomString(NumberConstants.NUMBER_8.intValue());
        return captcha.toUpperCase(Locale.ROOT);
    }
    
    /**
     * 生成某一区间内的n个唯一随机数
     *
     * @param min 区间最小值
     * @param max 区间最大值
     * @param n   多少个数
     * @return Set<Integer>
     */
    public static Set<Integer> generateUniqueRandomNumbers(int min, int max, int n) {
        Random random = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        
        while (uniqueNumbers.size() < n) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            uniqueNumbers.add(randomNumber);
        }
        
        return uniqueNumbers;
    }
    
    /**
     * 将传入的Integer数组转换为固定位数的字符串数组形式
     *
     * @param array  固定数组
     * @param length 字符串长度
     * @return String[]
     */
    public static String[] intArrayToStringArray(List<Integer> array, int length) {
        String[] result = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = String.format("%0" + length + "d", array.get(i));
        }
        return result;
    }
    
    /**
     * 判断所提供的Object是否为空或null；如果传入List数组，则逐项判空或null，有且仅有全部项都不为空或不为null则返回false
     *
     * @param obj Object类型
     * @return boolean
     */
    public static boolean checkIsNullOrEmpty(Object obj) {
        if (obj instanceof String) {
            return (obj == null || (((String) obj).equals(""))
                    || (((String) obj).equals("null"))
                    || ((String) obj).equals("undefined"));
        } else if (obj instanceof List) {
            List list = (List) obj;
            for (Object o : list) {
                if (o instanceof String) {
                    if (o == null || (((String) o).equals(""))) {
                        return true;
                    }
                }
                if (o instanceof Integer) {
                    if (o == null) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            return obj == null;
        }
    }
    
    
    /**
     * 获取一个UUID
     *
     * @return
     */
    public static String getUUIDStr() {
        return UUID.randomUUID().toString().toUpperCase(Locale.ROOT);
    }
    
    /**
     * 带前缀的UUID
     *
     * @param prefix
     * @return
     */
    public static String getUUIDStr(String prefix) {
        return (prefix + "-" + UUID.randomUUID().toString()).toUpperCase(Locale.ROOT);
    }
}