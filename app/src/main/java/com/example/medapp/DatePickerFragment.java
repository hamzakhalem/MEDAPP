package com.example.medapp;


import android.app.DatePickerDialog;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    @NonNull
    private int year,month,day;

    public DatePickerFragment() {
        Calendar calendar=Calendar.getInstance();


        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
    }

    public DatePickerFragment(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
    }
}
