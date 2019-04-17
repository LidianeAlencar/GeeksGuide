package br.com.app.geeksguide


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import android.R.id.message




class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        notificar(remoteMessage)
    }

    fun notificar(remoteMessage: RemoteMessage?) {

        initChannels(applicationContext);

        val intent = Intent(this, MapsActivity::class.java)

        // Creating a pending intent and wrapping our intent
        val pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val b = NotificationCompat.Builder(applicationContext, "default")
        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage!!.notification?.title.toString())
                .setContentText(remoteMessage!!.notification?.body.toString())
                .setContentInfo("INFO")
                .setChannelId("default")
                .setContentIntent(
                        pendingIntent/* createPendingIntent(
                                remoteMessage.notification?.clickAction,
                                remoteMessage.data
                        )*/
                );



        val notificationIntent = Intent(applicationContext, MapsActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP


        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(1, b.build())



    }

    fun initChannels(context: Context) {
        if (Build.VERSION.SDK_INT < 26) {
            return
        }
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("default",
                "Notificações",
                NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "Notificações"
        notificationManager.createNotificationChannel(channel)
    }


    private fun createPendingIntent(clickAction: String?, data: Map<String, String>): PendingIntent? {
        var pendingIntent: PendingIntent? = null
        try {
            val resultIntent = Intent("ListarAcivity")
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)

            for ((key, value) in data)
                resultIntent.putExtra(key, value)

            pendingIntent = PendingIntent.getActivity(
                    this, 0, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_ONE_SHOT
            )
        } catch (ex: Exception) {

        }

        return pendingIntent
    }

}