package com.phyntom.android.journalapp;

import android.arch.persistence.room.TypeConverter;

import java.time.LocalDate;

public class DateTypeConverter {

    /**
     * method use to convert long to locaDate
     * @param timestamp
     * @return
     */
    @TypeConverter
    public static LocalDate toDate(Long timestamp) {
        return timestamp == null ? null : LocalDate.ofEpochDay(timestamp);
    }

    /**
     * method used to convert locaDate to long
     * @param date
     * @return
     */
    @TypeConverter
    public static Long toTimestamp(LocalDate date) {
        return date == null ? null : date.toEpochDay();
    }
}
