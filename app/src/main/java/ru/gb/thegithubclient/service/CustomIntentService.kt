package ru.gb.thegithubclient.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log


class CustomIntentService : Service() {
    private lateinit var serviceLooper: Looper
    private lateinit var handler: ServiceHandler

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val thread = Thread { Log.e("Hello from intent service", msg.arg1.toString()) }
            thread.start()
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        HandlerThread("ServiceHandlerThread", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            serviceLooper = looper
            handler = ServiceHandler(serviceLooper)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.let {
            it.obtainMessage().also { msg ->
                msg.arg1 = startId
                handler.sendMessage(msg)
            }
        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}