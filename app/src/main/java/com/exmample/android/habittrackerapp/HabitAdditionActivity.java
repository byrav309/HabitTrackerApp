package com.exmample.android.habittrackerapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HabitAdditionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText activityDescriptionInput;
    private EditText activityTypeInput;
    private EditText activityDurationInput;
    private EditText activityPlaceInput;
    private Button saveButton;
    private HabitDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_addidtion);
        dbHelper = new HabitDBHelper(getApplicationContext());
        initialize();
    }

    private void initialize() {
        activityDescriptionInput = findViewById(R.id.activity_description);
        activityTypeInput = findViewById(R.id.activity_type);
        activityPlaceInput = findViewById(R.id.activity_place);
        activityDurationInput = findViewById(R.id.activity_duration);
        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                if (saveHabit()) {
                    showDialogToAddAnotherActivity();
                }
                break;
        }
    }

    private void showDialogToAddAnotherActivity() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.add_another_activity)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        clearAllField();
                        dialog.dismiss();
                    }
                }).
                setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    private void clearAllField() {
        activityDescriptionInput.setText("");
        activityTypeInput.setText("");
        activityDurationInput.setText("");
        activityPlaceInput.setText("");
    }

    private boolean saveHabit() {
        boolean status = true;
        if (!checkIfValueSet(activityDescriptionInput, R.string.category_activity)) {
            status = false;
        }
        if (!checkIfValueSet(activityTypeInput, R.string.category_type)) {
            status = false;
        }
        if (!checkIfValueSet(activityDurationInput, R.string.category_time)) {
            status = false;
        }
        if (!checkIfValueSet(activityPlaceInput, R.string.category_place)) {
            status = false;
        }

        if (!status) {
            return false;
        }
        HabitItem item = new HabitItem(activityDescriptionInput.getText().toString().trim(),
                activityTypeInput.getText().toString().trim(), Integer.parseInt(activityDurationInput.getText().toString().trim()),
                activityPlaceInput.getText().toString().trim());
        if (dbHelper.insertHabit(item) != 0) {
            showMessage(R.string.activity_inserted_successfully);
        } else {
            showMessage(R.string.activity_insertion_failed);
            status = false;
        }
        return status;
    }

    private void showMessage(int resourceId) {
        Toast.makeText(getApplicationContext(), resourceId, Toast.LENGTH_LONG).show();
    }

    private boolean checkIfValueSet(EditText text, int resourceId) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError(getString(R.string.missing) + " : " + getString(resourceId));
            return false;
        }
        return true;
    }
}
