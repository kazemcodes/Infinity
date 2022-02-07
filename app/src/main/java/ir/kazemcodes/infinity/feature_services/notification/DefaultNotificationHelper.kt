package ir.kazemcodes.infinity.feature_services.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.kazemcodes.infinity.feature_activity.presentation.MainActivity
import ir.kazemcodes.infinity.feature_services.flags
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNotificationHelper @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
) {
    private val notificationManager = NotificationManagerCompat.from(applicationContext)

    private val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    } else {
        PendingIntent.FLAG_UPDATE_CURRENT
    }

    fun openBookDetailIntent(
        bookId: Int,
        sourceId: Long,
    ): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            "https://www.ireader.com/book_detail_route/$bookId/$sourceId".toUri(),
            applicationContext,
            MainActivity::class.java
        )
    }
    fun openBookDetailPendingIntent(
        bookId: Int,
        sourceId: Long,
    ) : PendingIntent {
        return PendingIntent.getActivity(
            applicationContext, 0, openBookDetailIntent(bookId, sourceId), flags
        )
    }

    val openDownloadIntent = Intent(
        Intent.ACTION_VIEW,
        "https://www.ireader.com/downloader_route".toUri(),
        applicationContext,
        MainActivity::class.java
    )


    val openDownloadsPendingIntent: PendingIntent = PendingIntent.getActivity(
        applicationContext, 0, openDownloadIntent, flags
    )


}