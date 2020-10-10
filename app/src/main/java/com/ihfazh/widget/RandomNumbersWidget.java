package com.ihfazh.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RandomNumbersWidget extends AppWidgetProvider {

    private static final String WIDGET_ID_EXTRA = "widget id extra";
    private static final String APP_WIDGET_CLICK = "app widget click";

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        String lastUpdate = "Random: " + NumberGenerator.getNumber(100);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_numbers_widget);
        views.setTextViewText(R.id.appwidget_text, lastUpdate);

        // set pending intent
        views.setOnClickPendingIntent(R.id.btn_update, createPendingIntent(context, appWidgetId, APP_WIDGET_CLICK));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    private PendingIntent createPendingIntent(Context context, int appWidgetId, String intentType) {
        String lastUpdate = "Random: " + NumberGenerator.getNumber(100);
        // Construct the RemoteViews object
        Intent intent = new Intent(context, getClass());
        intent.setAction(intentType);
        intent.putExtra(WIDGET_ID_EXTRA, appWidgetId);
        return PendingIntent.getBroadcast(context, appWidgetId, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (APP_WIDGET_CLICK.equals(intent.getAction())){
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_numbers_widget);
            String text = "Updated: " + NumberGenerator.getNumber(1020);
            views.setTextViewText(R.id.appwidget_text, text);
            int appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0);
            manager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

