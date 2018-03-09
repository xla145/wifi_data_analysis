package com.wzxy.base.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaseUtils {

    public static Date getCurrentTime() {

        return new Date();
    }

    public static Timestamp getTimeTimestamp() {

        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * @param specifiedDay 指定日期
     * @param dateFormat   日期格式：yyyy-MM-dd HH:mm:ss
     * @param day          值为：-1（前一天），0（当天），1（下一天）
     * @return
     */
    public static String getSpecifiedDay(String specifiedDay, String dateFormat, Integer day) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            date = isEmpty(specifiedDay) ? date : new SimpleDateFormat(dateFormat).parse(specifiedDay);
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, day);
            date = calendar.getTime();
            return formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return formatter.format(date);
        }

    }

    /**
     * 转换时间格式，防止提交上来的数据不是时间格式
     *
     * @param dateString 时间字符串
     * @param dateFormat 时间格式 : yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateString(String dateString, String dateFormat) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            if (isEmpty(dateString)) {
                return formatter.format(date);
            }
            formatter.parse(dateString);
            return dateString;
        } catch (Exception e) {
            return formatter.format(date);
        }
    }

    /**
     * 判断对象是否为空（或者转用org.apache.commons.lang.StringUtils.isBlank(String)方法）
     *
     * @param obj 要判断的对象
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof List)) {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String)) {
            return ((String) obj) == null || ((String) obj).length() == 0;
        }
        return false;
    }

    /**
     * @param obj 要判断的对象
     * @return boolean
     */
    public static boolean isIntEmpty(int val) {
        if (val == 0) {
            return true;
        }
        return false;
    }

    /**
     * 把字符串列表(List<String>)转换成字符串数组(String[])<br>
     * 通过List的toArray(T[])方法进行转换。<br>
     *
     * @param listString 要转换的List<String>
     * @return String[] 或 null
     */
    public static String[] changeToStringArray(List<String> listString) {
        if (listString.size() > 0 && !listString.isEmpty()) {
            return listString.toArray(new String[listString.size()]);
        }
        return null;
    }

    /**
     * 把字符串数组(String[])转换成字符串(String)输出<br>
     * 使用Arrays.toString(String[])进行转换<br>
     *
     * @param stringArray 要转换的字符串数组
     * @return 字符串（String）或 null
     */
    public static String changeToString(String[] stringArray) {
        if (stringArray.length > 0) {
            return Arrays.toString(stringArray);
        }

        return null;
    }

    /**
     * 把字符串转成整型
     *
     * @param numberString 需要转化的字符串
     * @return 一个整型数（int）或 0
     */
    public static int changeToInteger(String numberString) {
        if (numberString != null && numberString.trim() != "") {
            return Integer.parseInt(numberString);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(getSpecifiedDay(null, "yyyy-MM-dd HH", 1));
    }
}
