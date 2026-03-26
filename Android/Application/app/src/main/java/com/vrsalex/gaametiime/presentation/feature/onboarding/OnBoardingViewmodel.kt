package com.vrsalex.gaametiime.presentation.feature.onboarding

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.gaametiime.R
import com.vrsalex.gaametiime.domain.onboarding.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Назначение:
 * Дата: 25.03.2026
 * Автор: Вартанян Алексей
 */
@HiltViewModel
class OnBoardingViewmodel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
): ViewModel() {


    val pages = listOf(
        OnboardingContract.State(
            imageId = R.drawable.page1,
            title = "Get Paid! Playing Video Game",
            description = "Earn points and real cash when you win a battle with no delay in cashing out",
            showSkip = true
        ),
        OnboardingContract.State(
            imageId = R.drawable.page2,
            title = "Schedule Games With Friends",
            description = "Easily create an upcoming event and get ready for battle. Yeah! real combat fella.",
            showSkip = true
        ),
        OnboardingContract.State(
            imageId = R.drawable.page3,
            title = "Text, Audio and Video Chat",
            description = "Intuitive real life experience on mobile. Chat with fellow gamers before and after combat for free!",
            showSkip = false,
            mainButtonText = "Let’s Combat!"
        )
    )


    init {
        viewModelScope.launch {
            onboardingRepository.markAsCompleted()
        }
    }


}