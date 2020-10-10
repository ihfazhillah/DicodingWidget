package com.ihfazh.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UpdateWidgetWorker extends Worker {
    public UpdateWidgetWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(getApplicationContext());
        RemoteViews view = new RemoteViews(getApplicationContext().getPackageName(), R.layout.random_numbers_widget);
        ComponentName theWidget = new ComponentName(getApplicationContext(), RandomNumbersWidget.class);
        String lastUpdate = "Random: " + NumberGenerator.getNumber(100);
        view.setTextViewText(R.id.appwidget_text, lastUpdate);
        widgetManager.updateAppWidget(theWidget, view);
        return Result.success();
    }
}
