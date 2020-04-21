package io.mega.megahub.pages.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import io.mega.megahub.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 启用appbar功能
        setSupportActionBar(toolbar)

        mNavController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(mNavController)


        var loader: ClassLoader? = classLoader
        while (loader != null) {
            Timber.d("classLoader: $loader")
            loader = loader.parent
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp() || super.onSupportNavigateUp()
    }
}
