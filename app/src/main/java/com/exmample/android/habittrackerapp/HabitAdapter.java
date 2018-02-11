package com.exmample.android.habittrackerapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.RecyclerViewHolder> {

    private List<HabitItem> items;
    private Context context;

    public HabitAdapter(List<HabitItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<HabitItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        HabitItem item = items.get(position);
        holder.habitDescriptionTextView.setText(context.getString(R.string.activity) + " : " +item.getHabitDescription());
        holder.habitTypeTextView.setText(context.getString(R.string.activity_type) + " : " +item.getHabitType());
        holder.habitDurationTextView.setText(context.getString(R.string.activity_duration) + " : " +item.getHabitDuration());
        holder.habitPlaceTextView.setText(context.getString(R.string.activity_place) + " : " +item.getHabitPlace());
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView habitDescriptionTextView, habitTypeTextView, habitDurationTextView, habitPlaceTextView;


        public RecyclerViewHolder(View view) {
            super(view);
            habitDescriptionTextView = view.findViewById(R.id.habit_description);
            habitTypeTextView = view.findViewById(R.id.habit_type);
            habitDurationTextView = view.findViewById(R.id.habit_duration);
            habitPlaceTextView = view.findViewById(R.id.habit_place);
        }

    }
}
