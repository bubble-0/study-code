package com.magic.springboot;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

public class Demo {

    //批量将sql转换成map实体
    private static List<Map<String, String>> batchSqltoMapEntity(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException("传入sql为空");
        }
        List list = new ArrayList();

        //如果包含;说明有多个语句
        if (str.indexOf(";") != -1) {
            String arr[] = str.split(";");
            for (String s : arr) {
                if (!StringUtils.isEmpty(s.trim())) {
                    list.add(sqltoMapEntity(s.trim()));
                }

            }
        } else {
            list.add(sqltoMapEntity(str));
        }
        return list;
    }

    //将sql语句转换成map实体
    private static Map sqltoMapEntity(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException("传入sql为空");
        }

        String strs = str;
        String[] arr = strs.split("values|VALUES");

        String[] colArr = arr[0].substring(arr[0].indexOf("(") + 1, arr[0].indexOf(")")).split(",");
        String[] valArr = arr[1].substring(arr[1].indexOf("(") + 1, arr[1].indexOf(")")).split(",");

        Map map = new HashMap<>();

        if (colArr.length == valArr.length && valArr.length != 0) {
            for (int i = 0; i < valArr.length; i++) {
                map.put(colArr[i].toLowerCase().trim(), deleteSpecialCharacters(valArr[i].trim()));
            }
        } else {
            throw new RuntimeException("传入的sql语句有误");
        }

        return map;
    }

    private static String deleteSpecialCharacters(String str) {
        if (str.startsWith("'") || str.startsWith("\"")) {
            str = str.substring(1);
        }

        if (str.endsWith("'") || str.endsWith("\"")) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

    public static void main(String[] args) {
        Timestamp now = new Timestamp(new Date().getTime());
        System.out.println(now.toString());

    }
}
