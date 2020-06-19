package io.mega.megahub.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import java.io.File
import java.lang.Exception

/**
 * 检查patch文件，下载patch文件，加载下好的patch文件
 */
const val UPDATE_PATCH = 10001
const val DOWNLOAD_PATCH = 10002
const val FILE_END = ".apatch"

class AndFixService: Service() {
    private var mPatchFileDir: String? = null
    private var mPatchFile: String? = null

    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                UPDATE_PATCH -> checkUpdatePatch()
                DOWNLOAD_PATCH -> {

                }
            }
        }
    }

    private fun checkUpdatePatch() {

    }

    override fun onCreate() {
        super.onCreate()
        initService()
    }

    // 完成文件夹的创建
    private fun initService() {
        mPatchFileDir = externalCacheDir?.absolutePath + "/apatch"
        val patchDir = File(mPatchFileDir!!)
        try {
            if (!patchDir.exists()) patchDir.mkdir()
        } catch (e: Exception) {
            e.printStackTrace()
            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // service 被系统回收后，不会重启
        mHandler.sendEmptyMessage(UPDATE_PATCH)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}