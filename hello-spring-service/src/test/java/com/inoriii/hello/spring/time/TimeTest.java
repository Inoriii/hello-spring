package com.inoriii.hello.spring.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author sakura
 * @date: 2022/6/29 22:14
 * @description:
 */
public class TimeTest {
    /**
     * 中国标准时间(China Standard Time) CST  UTC+8 GMT+8
     * UT   Universal Time 世界时。根据原子钟计算出来的时间。
     * GMT  Greenwich Mean Time 格林尼治标准时间。这是以英国格林尼治天文台观测结果得出的时间，这个地方的当地时间过去被当成世界标准的时间。
     * UTC  Coordinated Universal Time 协调世界时。每隔几年都会给世界时+1秒，让(UT)和(GMT)相差不至于太大。并将得到的时间称为UTC。
     * 协调世界时不与任何地区位置相关，也不代表此刻某地的时间，所以在说明某地时间时要加上时区
     * <p>
     * 新api的优势在于
     * DateTimeFormatter是线程安全的
     * Month用1~12表示1月到12月
     * Week用1~7表示周一到周日
     */
    public static void main(String[] args) {
        //ZoneId 是时区相关类
        //北京时区
        ZoneId beijing = ZoneId.of("UTC+8");
        //美国夏威夷州时区
        ZoneId hawaii = ZoneId.of("UTC-10");

        System.out.println("//--------------------------------------------------------------------------------");
        //不含有时区的时间相关类
        System.out.println("不含有时区的时间相关类");
        //Instant  时刻   用来代替时间戳     有年月日时分秒信息     也可以理解成有时区(打印时有时区标识)，但只能是UTC+0 GMT+0时区
        Instant instant = Instant.now();
        System.out.println("Instant.now()" + instant);
        System.out.println("Instant.of() " + Instant.ofEpochSecond(1656599684));
        System.out.println("Instant.of() " + Instant.ofEpochMilli(1656599721234L));

        //LocalDateTime 有年月日时分秒信息     不含有时区   体现在打印没有表示时区的后缀
        //LocalDate,LocalTime 与LocalDateTime同理
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalDateTime.now()" + localDateTime);
        System.out.println("LocalDateTime.of()" + LocalDateTime.ofInstant(instant, beijing));
        System.out.println("LocalDateTime.of()有时区问题" + LocalDateTime.ofEpochSecond(1656599684, 0, ZoneOffset.UTC));
        System.out.println("LocalDateTime.of()" + LocalDateTime.of(2022, 6, 29, 0, 0, 0));
        System.out.println("LocalDate,LocalTime 与LocalDateTime同理" + localDate + localTime);

        System.out.println("//--------------------------------------------------------------------------------");

        System.out.println("含有时区的时间相关类");

        //ZonedDateTime 含有时区的时间相关类,由LocalDateTime和ZoneId组成
        //ZonedDateTime 通过Instant或LocalDateTime增加时区而来
        ZonedDateTime zonedDateTime = instant.atZone(beijing);
        System.out.println("ZonedDateTime通过Instant转换而来（1）：" + zonedDateTime);
        zonedDateTime = instant.atZone(hawaii);
        System.out.println("ZonedDateTime通过Instant转换而来（2）：" + zonedDateTime);

        //ZonedDateTime带有时区的当前时间  LocalDateTime拼接上时区后缀
        zonedDateTime = localDateTime.atZone(hawaii);
        System.out.println("ZonedDateTime通过LocalDateTime拼接上时区后缀而来：" + zonedDateTime);

        System.out.println("//--------------------------------------------------------------------------------");

        //Instant常用时间操作
        System.out.println("Instant常用时间操作");
        System.out.println("获取时间戳(秒): " + instant.getEpochSecond());
        System.out.println("获取时间戳(毫秒): " + instant.toEpochMilli());
        System.out.println("取整: " + instant.truncatedTo(ChronoUnit.HOURS));

        //LocalDateTime,ZonedDateTime常用时间操作,二者共同的操作
        System.out.println("LocalDateTime,ZonedDateTime常用时间操作,二者共同的操作");

        System.out.println("获取时间戳(秒): " + localDateTime.toInstant(ZoneOffset.UTC));

        System.out.println("取日期" + localDateTime.getDayOfMonth());
        System.out.println("取星期" + localDateTime.getDayOfWeek());
        System.out.println("取日期加" + localDateTime.plusDays(1));
        System.out.println("取日期减" + localDateTime.minusDays(1));

        System.out.println("调整日期（时分秒不变）" + zonedDateTime.withDayOfMonth(1));
        System.out.println("调整日期（时分秒不变）" + zonedDateTime.withDayOfYear(1));

        System.out.println("日期比大小" + localDateTime.isAfter(localDateTime.plusDays(1)));
        System.out.println("计算时差");
        Duration duration = Duration.between(localDateTime, localDateTime
                .minusWeeks(10).plus(10, ChronoUnit.HOURS).plus(20, ChronoUnit.MINUTES));
        System.out.println("计算时差,一共相差天(差值全部转化)" + duration.toDays());
        System.out.println("计算时差,一共相小时(差值全部转化)" + duration.toHours());
        System.out.println("计算时差,一共相分钟(差值全部转化)" + duration.toMinutes());
        System.out.println("计算时差格式化" + format(duration));
        System.out.println("当天3点" + localDate.atTime(LocalTime.of(3, 0, 0)));

        Period period = Period.between(LocalDate.now(), LocalDate.of(2000, 12, 1));
        String s = String.format("计算时差相差%d年%02d月%02d日", period.getYears(), period.getMonths(), period.getDays());
        System.out.println(s);

        //LocalDate常用时间操作
        System.out.println("LocalDate常用时间操作");
        // 本月第一天
        System.out.println("本月第1天:" + localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("本月最后1天:" + localDate.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("下月第1天:" + localDate.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println("本月第1个周一:" + localDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.println("本月第2个周二:" + localDate.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.TUESDAY)));
        System.out.println("本周三:" + localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY)));
        System.out.println("下一个周一(不算今天否则加上OrSame):" + localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        System.out.println("上一个周一(不算今天否则加上OrSame):" + localDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));
        System.out.println("当天0点:" + localDate.atStartOfDay());
        System.out.println("是否是闰年:" + localDate.isLeapYear());
        System.out.println("//--------------------------------------------------------------------------------");

        //DateTimeFormatter,用于【格式化时间为字符串】或【转字符串为时间】
        System.out.println("格式化操作");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //时间格式转字符串格式 dateTimeFormatter.format或者DateTime.format都行
        System.out.println("时间转字符串(Formatter.format):" + dateTimeFormatter.format(localDateTime));
        //会去掉时区（除非指定显示时区）
        System.out.println("时间转字符串,会去掉时区(DateTime.format):" + zonedDateTime.format(dateTimeFormatter) + "  " + zonedDateTime);

        //字符串转时间
        LocalDateTime parseLocalDateTime = LocalDateTime.parse("2022-06-29T23:41:03");
        System.out.println("字符串转时间:" + parseLocalDateTime);

        //字符串转时间，使用自定义格式
        parseLocalDateTime = LocalDateTime.parse("2022-06-29 03:41:03", dateTimeFormatter);
        System.out.println("字符串转时间，使用自定义格式:" + parseLocalDateTime);

        System.out.println("夏威夷时区" + zonedDateTime);
        zonedDateTime = zonedDateTime.withZoneSameInstant(beijing);
        System.out.println("夏威夷转北京时区" + zonedDateTime);
    }

    public static String format(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }
}
