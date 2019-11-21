package com.wgu_android.studenttracker6.Utilities;

import androidx.room.TypeConverter;

import java.util.Date;

public class ConvertTypes {

    @TypeConverter
    public static Date fromDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toDate(Date date) {
        return date == null ? null : date.getTime();
    }
}

