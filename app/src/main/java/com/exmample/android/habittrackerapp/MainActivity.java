package com.exmample.android.habittrackerapp;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<HabitItem>> {

    private RecyclerView recyclerView;
    private HabitAdapter adapter;
    private TextView noHabitFoundTextView;
    private final int LOADER_ID = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =  findViewById(R.id.recycler_view);
        noHabitFoundTextView =  findViewById(R.id.text_no_product_found);
        adapter = new HabitAdapter(new ArrayList<HabitItem>(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<HabitItem>> onCreateLoader(int id, Bundle args) {
        return new HabitAsyncLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<HabitItem>> loader, List<HabitItem> data) {
        if (data.size() <= 0) {
            noHabitFoundTextView.setVisibility(View.VISIBLE);
        } else {
            noHabitFoundTextView.setVisibility(View.GONE);
        }
        adapter.setItems(data);
    }

    @Override
    public void onLoaderReset(Loader<List<HabitItem>> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_habit:
                addNewHabit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewHabit() {
        Intent intent = new Intent(this, HabitAdditionActivity.class);
        startActivity(intent);
    }
}
