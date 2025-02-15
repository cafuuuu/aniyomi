package eu.kanade.tachiyomi.glance.anime

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class AnimeUpdatesGridGlanceReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = AnimeUpdatesGridGlanceWidget().apply { loadData() }
}
