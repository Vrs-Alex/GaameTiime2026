package com.vrsalex.gaametiime

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.TimePicker
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vrsalex.gaametiime.preview.AppTimePicker
import com.vrsalex.gaametiime.preview.DatePicker
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.v1.button.AppButton
import com.vrsalex.uikit.component.v1.button.AppTertiaryButton
import com.vrsalex.uikit.component.v1.card.AppCard
import com.vrsalex.uikit.component.v1.card.AppTimer
import com.vrsalex.uikit.component.v1.controller.AppCheckBox
import com.vrsalex.uikit.component.v1.controller.AppPagination
import com.vrsalex.uikit.component.v1.input.AppTextInput
import com.vrsalex.uikit.component.v1.tabbar.AppTabBar
import com.vrsalex.uikit.component.v1.tabbar.AppTabItem
import com.vrsalex.uikit.theme.GaameTiimeTheme


val leftItems = listOf(
    AppTabItem(
        payload = "1",
        title = "Statistics",
        iconId = R.drawable.statictic
    ),
    AppTabItem(
        payload = "discover",
        title = "Discover",
        iconId = R.drawable.location
    )
)

val rightItems = listOf(
    AppTabItem(
        payload = "3",
        title = "Chat",
        iconId = R.drawable.chat
    ),
    AppTabItem(
        payload = "Profile",
        title = "Profile",
        iconId = R.drawable.profile
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false
        setContent {
            GaameTiimeTheme {


                val state = rememberPagerState(1) { 2 }
                HorizontalPager(state) {
                    when(it){
                        0 -> {
                            Column(
                                Modifier.fillMaxSize()
                                    .background(Color.White)
                                    .safeDrawingPadding()
                                    .padding(horizontal = 20.dp)
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                AppButton(
                                    text = "Let’s Combat!",
                                    onClick = {}
                                )
                                AppTertiaryButton(
                                    text = "Logout",
                                    iconId = R.drawable.logout,
                                    { }
                                )

                                var text by remember { mutableStateOf("") }
                                AppTextInput(
                                    value = text,
                                    onValueChange = { text = it },
                                    placeHolder = "Full Name"
                                )

                                AppPagination(
                                    state = rememberPagerState(2) { 3 }
                                )

                                AppTimer(
                                    "00:00"
                                )

                                AppCheckBox(
                                    active = true,
                                    onClick = {}
                                )
                                AppCheckBox(
                                    active = false,
                                    onClick = {}
                                )

                                AppCard(
                                    title = "Schedule",
                                    description = "Easily schedule event/games then find like minded players for battle. You up for it?",
                                    imageId = R.drawable.image1,
                                    onClick = { }
                                )

                                DatePicker()

                                AppTimePicker()

                                AppTabBar(
                                    leftItems = leftItems,
                                    rightItems = rightItems,
                                    centerItem = AppTabItem(
                                        payload = "center",
                                        iconId = R.drawable.schedule,
                                        title = ""
                                    ),
                                    onItemClick = { }
                                )
                            }
                        }
                        1 -> {
                            Column(
                                Modifier.fillMaxSize()
                                    .background(Color(0xFFE9F0F7))
                                    .safeDrawingPadding()
                                    .padding(horizontal = 20.dp)
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Spacer(Modifier.height(20.dp))
                                com.vrsalex.uikit.component.v2.button.AppButton(
                                    "Log in",
                                    {},
                                    icon = R.drawable.lock
                                )

                                var text by remember { mutableStateOf("") }
                                com.vrsalex.uikit.component.v2.input.AppTextInput(
                                    value = text,
                                    onValueChange = { text = it },
                                    placeHolder = "Input field",
                                    modifier = Modifier.width(200.dp),
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.checkmark),
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
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

