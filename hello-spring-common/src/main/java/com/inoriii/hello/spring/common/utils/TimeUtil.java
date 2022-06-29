package com.inoriii.hello.spring.common.utils;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

/**
 * @author sakura
 * @date: 2022/6/29 22:14
 * @description:
 */
public class TimeUtil {
    /**
     * 中国标准时间(China Standard Time) CST  UTC+8 GMT+8
     * UT   Universal Time 世界时。根据原子钟计算出来的时间。
     * GMT  Greenwich Mean Time 格林尼治标准时间。这是以英国格林尼治天文台观测结果得出的时间，这个地方的当地时间过去被当成世界标准的时间。
     * UTC  Coordinated Universal Time 协调世界时。每隔几年都会给世界时+1秒，让(UT)和(GMT)相差不至于太大。并将得到的时间称为UTC。
     * 协调世界时不与任何地区位置相关，也不代表此刻某地的时间，所以在说明某地时间时要加上时区
     */
    public static void main(String[] args) {
        //TimeZone 时区相关类，依赖于ZoneId
        //北京时区
        ZoneId beijing = ZoneId.of("UTC+8");
        //美国夏威夷州时区
        ZoneId hawaii = ZoneId.of("UTC-10");

        TimeZone beijingTimeZone = TimeZone.getTimeZone(beijing);
        TimeZone hawaiiTimeZone = TimeZone.getTimeZone(hawaii);
        //带有时区的时间值
        Clock clock = Clock.systemUTC();

        System.out.println("//--------------------------------------------------------------------------------");

        //不含有时区的时间相关类
        System.out.println("不含有时区的时间相关类");
        //Instant 是一个时间的秒数值，没有时区，是UTC+0 GMT+0 有时间的数值
        //Instant.now()使用了当前系统时区：Clock.systemUTC().instant();
        Instant instant = Instant.now(clock);
        System.out.println(instant);

        //LocalDateTime 不含有时区的时间,打印没有后缀  有年月日时分秒信息
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDateTime myLocalDateTime = LocalDateTime.of(2022, 6, 29, 0, 0, 0);
        System.out.println(myLocalDateTime);

        System.out.println("//--------------------------------------------------------------------------------");

        //ZonedDateTime 含有时区的时间相关类,由LocalDateTime和ZoneId组成

        //转换时区,通过Instant或LocalDateTime增加时区而来  夏威夷时区
        ZonedDateTime zonedDateTime = instant.atZone(hawaii);
        System.out.println(zonedDateTime);
        //ZonedDateTime带有时区的当前时间  LocalDateTime加上时区
        ZonedDateTime zonedDateTime1 = localDateTime.atZone(hawaii);
        System.out.println(zonedDateTime1);

        System.out.println("//--------------------------------------------------------------------------------");

        //常用时间操作
        //取整
        System.out.println(instant.truncatedTo(ChronoUnit.HOURS));
        //localDateTime取日期
        System.out.println(localDateTime.getDayOfMonth());
        //取日期
        System.out.println(zonedDateTime.getDayOfMonth());

        System.out.println("//--------------------------------------------------------------------------------");

        //时间格式转字符串格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format1 = dateTimeFormatter.format(localDateTime);
        String format2 = dateTimeFormatter.format(zonedDateTime);
        System.out.println(format1);
        System.out.println(format2);

        //字符串转时间
        LocalDateTime parseLocalDateTime = LocalDateTime.parse("2022-06-29T23:41:03");
        ZonedDateTime zonedDateTime2 = parseLocalDateTime.atZone(beijing);
    }
}
