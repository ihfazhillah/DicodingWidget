package com.ihfazh.widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int JOB_ID = 100;
    Button btnStartJob, btnEndJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEndJob = findViewById(R.id.btn_end_job);
        btnStartJob = findViewById(R.id.btn_start_job);

        btnEndJob.setOnClickListener(this);
        btnStartJob.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_end_job:
                cancelJob();
                break;
            case R.id.btn_start_job:
                startJob();
                break;
        }

    }

    private void cancelJob() {
        WorkManager.getInstance(this).cancelAllWorkByTag("TESTING");

        Toast.makeText(this, "Hello world, schedule canceled...", Toast.LENGTH_SHORT).show();
    }

    private void startJob() {
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(UpdateWidgetWorker.class, 15, TimeUnit.MINUTES)
                .addTag("TESTING")
                .build();

        WorkManager.getInstance(this).enqueue(periodicWorkRequest);

        Toast.makeText(this, "Hello world, schedule updated...", Toast.LENGTH_SHORT).show();
    }
}