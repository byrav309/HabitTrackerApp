package com.exmample.android.habittrackerapp;

import android.database.Cursor;

/**
 * Class which holds habit information
 */

public class HabitItem {

    private int habitId;
    private String habitDescription;
    private String habitType;
    private int habitDuration;
    private String habitPlace;

    public HabitItem(){

    }

    public HabitItem(String habitDescription, String habitType, int habitDuration, String habitPlace){
        this.habitDescription = habitDescription;
        this.habitType = habitType;
        this.habitDuration = habitDuration;
        this.habitPlace = habitPlace;
    }

    public int getHabitId() {
        return habitId;
    }

    public String getHabitDescription() {
        return habitDescription;
    }

    public String getHabitType() {
        return habitType;
    }

    public int getHabitDuration() {
        return habitDuration;
    }

    public String getHabitPlace() {
        return habitPlace;
    }

    public void initializeFromCursor(Cursor cursor) {
        habitId = cursor.getInt(cursor.getColumnIndex(HabitDataBaseObject.ACTIVITY_ID));
        habitDescription = cursor.getString(cursor.getColumnIndex(HabitDataBaseObject.ACTIVITY_DESCRIPTION));
        habitType = cursor.getString(cursor.getColumnIndex(HabitDataBaseObject.ACTIVITY_TYPE));
        habitDuration = cursor.getInt(cursor.getColumnIndex(HabitDataBaseObject.ACTIVITY_DURATION));
        habitPlace = cursor.getString(cursor.getColumnIndex(HabitDataBaseObject.ACTIVITY_PLACE));
    }
}
