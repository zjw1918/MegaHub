package io.mega.megahub

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.mega.megahub.andfix.AndFixPatchManager
import java.io.File

class MainActivity2: AppCompatActivity() {
    private var mPatchDir: String? = null

    companion object {
        val FILE_END = ".apatch"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mPatchDir = externalCacheDir?.absolutePath + "/apatch/"

        val file = File(mPatchDir!!)
        // 创建文件夹
        if (!file.exists()) {
            file.mkdir()
        }

    }

    fun createBug(view: View) {
        Utils.printLog()
    }
    fun fixBug(view: View) {
        AndFixPatchManager.getInstance().addPatch(getPatchName())
    }

    private fun getPatchName(): String? {
        return mPatchDir + "imooc$FILE_END"
    }
}