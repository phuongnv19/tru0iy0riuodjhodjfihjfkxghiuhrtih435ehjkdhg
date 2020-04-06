package com.toprate.erp.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String EXPORT_DATE_FORMAT = "yyyyMMddHHmmss";

    public static String formatLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return localDate == null ? "" : localDate.format(formatter);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return localDateTime == null ? "" : localDateTime.format(formatter);
    }

    public static LocalDate toLocalDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return LocalDate.parse(strDate, formatter);
    }

    public static String formatLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP);
        return localDateTime == null ? "" : localDateTime.format(formatter);
    }

    public static String formatLocalDateTimeToDefaultFormat(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return localDateTime == null ? "" : localDateTime.format(formatter);
    }

    public static String formatLocalDateTimeToExportFormat(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EXPORT_DATE_FORMAT);
        return localDateTime == null ? "" : localDateTime.format(formatter);
    }


    public static String date2str(Date date, String format){
        SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy");
        return mdyFormat.format(date);
    }

    public static String date2str(Date date){
        SimpleDateFormat mdyFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return mdyFormat.format(date);
    }

}
