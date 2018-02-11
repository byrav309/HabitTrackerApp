package com.exmample.android.habittrackerapp;

/**
 * Class which holds table info related Habit.
 */

public class HabitDataBaseObject {

    public static String TABLE_NAME = "habit";
    public static final String ACTIVITY_ID = "_ID";
    public static final String ACTIVITY_DESCRIPTION = "habitDescription";
    public static final String ACTIVITY_TYPE = "habitType";
    public static final String ACTIVITY_DURATION = "habitDuration";
    public static final String ACTIVITY_PLACE = "place";

    public static String[] projection = {
            ACTIVITY_ID, ACTIVITY_DESCRIPTION, ACTIVITY_TYPE, ACTIVITY_DURATION, ACTIVITY_PLACE
    };

    public static String CREATE_HABIT_TABLE = "CREATE TABLE " +
            TABLE_NAME + " ( " +
            ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ACTIVITY_DESCRIPTION + " TEXT NOT NULL, " +
            ACTIVITY_TYPE + " TEXT NOT NULL, " +
            ACTIVITY_DURATION + " INTEGER NOT NULL DEFAULT 0, " +
            ACTIVITY_PLACE + " TEXT NOT NULL " + " ); ";
}
