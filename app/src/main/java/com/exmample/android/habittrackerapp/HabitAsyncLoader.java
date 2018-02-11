package com.exmample.android.habittrackerapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Habit Loader.
 */

public class HabitAsyncLoader extends AsyncTaskLoader<List<HabitItem>> {

    private HabitDBHelper dbHelper;

    public HabitAsyncLoader(Context context) {
        super(context);
        dbHelper = new HabitDBHelper(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<HabitItem> loadInBackground() {
        Cursor cursor = dbHelper.getAllHabits();
        if (cursor != null && cursor.getCount() > 0) {
            return readHabitData(cursor);
        }
        return new ArrayList<>();
    }

    private List<HabitItem> readHabitData(Cursor cursor) {
        List<HabitItem> habitItems = new ArrayList<>();
        HabitItem habitItem;
        cursor.moveToFirst();
        do {
            habitItem = new HabitItem();
            habitItem.initializeFromCursor(cursor);
            habitItems.add(habitItem);
        } while (cursor.moveToNext());
        cursor.close();
        return habitItems;
    }
}
