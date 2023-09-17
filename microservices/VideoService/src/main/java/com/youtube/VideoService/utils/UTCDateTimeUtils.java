package com.youtube.VideoService.utils;


import org.joda.time.DateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;


public final class UTCDateTimeUtils {


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public static LocalDateTime utcTimeNow() {
        return LocalDateTime.now(Clock.systemUTC());
    }


    public static Long utcTimeNowInSeconds() {
        return Instant.now(Clock.systemUTC()).getEpochSecond();
    }


    public static String toYearMonthAndDay(LocalDateTime time) {

        return time.format(formatter);
    }


    public static String getDateAndTime() {
        LocalDateTime dateObj = LocalDateTime.now();
        return dateObj.format(formatterWithTime);
    }


    public static int toYear(DateTime time) {

        return time.getYear();
    }


    public static int toWeek(DateTime time) {
        return time.getWeekOfWeekyear();
    }


    public static LocalDateTime toLocalDateTime(DateTime time) {

        return OffsetDateTime.parse(time.toString()).toLocalDateTime();
    }


    public static int currentYear() {
        return utcTimeNow().getYear();
    }


    public static int currentWeek() {
        var now = utcTimeNow();
        return now.get(WeekFields.ISO.weekOfWeekBasedYear());
    }


    public static int lastWeek() {
        return currentWeek() - 1;
    }


    public static LocalDateTime dateOnMondayOfGivenWeek() {
        return utcTimeNow().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }


    public static int lastMonth() {
        return utcTimeNow().getMonthValue() - 1;
    }


    public static int currentMonth() {
        return utcTimeNow().getMonthValue();
    }


}
