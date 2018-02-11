package com.exmample.android.habittrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DB Helper class to perform DB operations
 */

public class HabitDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "habitTracker.db";
    private static final int DB_VERSION = 1;

    public HabitDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HabitDataBaseObject.CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertHabit(HabitItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValues(item);
        return db.insert(HabitDataBaseObject.TABLE_NAME, null, values);
    }

    private ContentValues getContentValues(HabitItem item){
        ContentValues values = new ContentValues();
        values.put(HabitDataBaseObject.ACTIVITY_DESCRIPTION, item.getHabitDescription());
        values.put(HabitDataBaseObject.ACTIVITY_TYPE, item.getHabitType());
        values.put(HabitDataBaseObject.ACTIVITY_DURATION, item.getHabitDuration());
        values.put(HabitDataBaseObject.ACTIVITY_PLACE, item.getHabitPlace());
        return values;
    }

    public Cursor getAllHabits(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                HabitDataBaseObject.TABLE_NAME,
                HabitDataBaseObject.projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }
}
