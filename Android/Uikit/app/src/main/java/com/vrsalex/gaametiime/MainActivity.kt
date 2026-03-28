package com.vrsalex.gaametiime

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import com.vrsalex.uikit.theme.GaameTiimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false

        setContent {
            GaameTiimeTheme {
                val pagerState = rememberPagerState(1) { 2 }
                HorizontalPager(pagerState) {
                    when(it){
                        0 -> {
                            Theme1()
                        }
                        1 ->  {
                            Theme2()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        Log.i("CustomLog", "OnResume")
        super.onResume()
    }

    override fun onStop() {
        Log.i("CustomLog", "OnStop")
        super.onStop()
    }

    override fun onPause() {
        Log.i("CustomLog", "OnPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.i("CustomLog", "OnDestroy")
        super.onDestroy()
    }

}

