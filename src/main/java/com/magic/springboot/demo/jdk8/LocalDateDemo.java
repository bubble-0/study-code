package com.magic.springboot.demo.jdk8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class LocalDateDemo {
    public static void main(String[] args) {
        //1.获取当前日期，年月日
        LocalDate localDate = LocalDate.now();
        System.out.println("当前时间： " + localDate);

        //2.根据年月日构建LocalDate
        LocalDate localDate1 = LocalDate.of(2020, 05, 21);
        System.out.println("根据年月日构建LocalDate: " + localDate1);

        //3.根据字符串转换日期，默认按照yyyy-MM-dd的格式，也可以自定义格式，使用DateTimeFormatter类
        LocalDate localDate2 = LocalDate.parse("2020-05-21");
        System.out.println("根据字符串转换：" + localDate2);

        //4.自定义转换
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate3 = LocalDate.parse("20200421", formatter);
        System.out.println("自定义转换: " + localDate3);
        System.out.println("自定义格式输出： " + formatter.format(localDate3));

        //5.获取本月第一天, 需要使用localDate对象，结合TemporalAdjusters类来使用
        LocalDate localDate4 = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("5月第一天 ： " + localDate4);

        LocalDate localDate5 = localDate3.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("4月第一天 ： " + localDate5);

        //6.获取本月第二天
        LocalDate localDate61 = localDate.withDayOfMonth(1);
        LocalDate localDate62 = localDate.withDayOfMonth(2);
        LocalDate localDate63 = localDate.withDayOfMonth(3);
        System.out.println("5月第一天 : " + localDate61 +  " 5月第二天 : " + localDate62 + " 5月第三天 : " + localDate63);

        LocalDate localDate71 = localDate3.withDayOfMonth(1);
        LocalDate localDate72 = localDate3.withDayOfMonth(2);
        LocalDate localDate73 = localDate3.withDayOfMonth(3);
        System.out.println("4月第一天 : " + localDate71 +  " 4月第二天 : " + localDate72 + " 4月第三天 : " + localDate73);

        //7.获取本月最后一天
        LocalDate localDate8 = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("5月最后一天: " + localDate8);

        LocalDate localDate9 = localDate3.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("4月最后一天: " + localDate9);

        //8.明天
        LocalDate tomorrowDay = localDate.plusDays(1L);
        System.out.println("明天： " + tomorrowDay);

        //9.昨天
        LocalDate yesterday = localDate.minusDays(1l);
        System.out.println("昨天： " + yesterday);

        //获取今年的第xx天
        LocalDate day = localDate.withDayOfYear(10);
        System.out.println("今年的第十天: " + day);

        long days = localDate.until(localDate1, ChronoUnit.DAYS);
        System.out.println("两个日期相差的天数: " + days);

        long weeks = localDate.until(localDate1, ChronoUnit.WEEKS);
        System.out.println("两个日期相差的周数: " +  weeks);



    }
}
