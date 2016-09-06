package com.example.dmitry.cashmonitor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.dmitry.cashmonitor.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = (new SimpleDateFormat()).getCalendar();
        ((TextView) findViewById(R.id.tvDateMain))
                //.setText(calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR));
                .setText((new SimpleDateFormat("dd.MM.yyyy").format(new Date())));
    }

    public void notesListener(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("FragmentType", 'n');
        startActivity(intent);
    }

    public void historyBoughtListener(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("FragmentType", 'h');
        startActivity(intent);
    }

    public void graphListener(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("FragmentType", 'g');
        startActivity(intent);
    }

    public void settingListener(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("FragmentType", 's');
        startActivity(intent);
    }

    public void calendarListener(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("FragmentType", 'c');
        startActivity(intent);
    }

    public void aboutProgrammListener(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("FragmentType", 'a');
        startActivity(intent);
    }
}
