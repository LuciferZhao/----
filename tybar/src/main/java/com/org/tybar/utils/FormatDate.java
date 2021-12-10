package com.org.tybar.utils;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class FormatDate {


    public String formatDate1(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public String formatDate2(@org.jetbrains.annotations.NotNull String date) throws ParseException {
        String newDate = date.replace("T"," ");
        return newDate;
    }

    public int checkDate(Long dateTime,String paperEndDate){

        // 先将截止日期转换为毫秒
        Long paperEndDateToLong = new FormatDate().timeToLong(paperEndDate);
        // 然后在和传过来的dateTime进行比较
        int value = (int) (dateTime-paperEndDateToLong);

        return value;
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public String timeToString(Long time, String format){
        Assert.notNull(time, "time is null");
        // "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatString = DateTimeFormatter.ofPattern(format);
        return formatString.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }
    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     */
    public Long timeToLong(String time) {
        Assert.notNull(time, "time is null");
        DateTimeFormatter formatString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, formatString);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    /**
     * 取本月第一天
     */
    public static LocalDate firstDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.firstDayOfMonth());
    }
    /**
     * 取本月第一天
     */
    public static LocalDate firstDayOfLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1);
        return today.with(TemporalAdjusters.firstDayOfMonth());
    }
    /**
     * 取本月第N天
     */
    public static LocalDate dayOfThisMonth(int n) {
        LocalDate today = LocalDate.now();
        return today.withDayOfMonth(n);
    }
    /**
     * 取本月最后一天
     */
    public static LocalDate lastDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth());
    }
    /**
     * 获取本周一
     */
    public static LocalDateTime firstDayOfWeek(Long date) {
        // long转LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        return localDateTime.with(DayOfWeek.MONDAY);
    }
    /**
     * 获取上周一
     */
    public static LocalDateTime firstDayOfLastWeek(Long date) {
        // long转LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        LocalDateTime localDateTime1 = localDateTime.plusDays(-7);
        return localDateTime1.with(DayOfWeek.MONDAY);
    }
    /**
     * 获取上周一
     */
    public static LocalDateTime lastDay(Long date) {
        // long转LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneId.systemDefault());
        LocalDateTime localDateTime1 = localDateTime.plusDays(-1);
        return localDateTime1;
    }
    /**
     * 取本月第一天的开始时间
     */
    public static LocalDateTime startOfThisMonth() {
        return LocalDateTime.of(firstDayOfThisMonth(), LocalTime.MIN);
    }
    /**
     * 取本月最后一天的结束时间
     */
    public static LocalDateTime endOfThisMonth() {
        return LocalDateTime.of(lastDayOfThisMonth(), LocalTime.MAX);
    }
    /** LocalDate转时间戳 */
    public static Long localDate2Second(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
    }
    /** LocalDate转时间戳 */
    public static Long localDateTime2Second(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }
}
