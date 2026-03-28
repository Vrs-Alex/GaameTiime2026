package com.vrsalex.gaametiime.presentation.feature.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vrsalex.gaametiime.R
import com.vrsalex.uikit.component.v1.AppButton
import com.vrsalex.uikit.component.v1.AppTextInput
import com.vrsalex.uikit.theme.AppTheme

/**
 * Назначение:
 * Дата: 28.03.2026
 * Автор: Вартанян Алексей
 */
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onCreate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when(it) {
                RegisterContract.Effect.Create -> { onCreate() }
                RegisterContract.Effect.OnLogin -> {}
            }
        }
    }

    RegisterContent(state, viewModel)
}

@Composable
private fun RegisterContent(state: RegisterContract.State, viewModel: RegisterViewModel) {

    Column(
        Modifier.fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.register),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            Modifier.fillMaxWidth()
                .padding(horizontal = 52.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(Modifier.width(220.dp)) {
                Spacer(Modifier.height(26.dp))
                Text(
                    text = "Create Account",
                    style = AppTheme.types.text.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = AppTheme.colors.redElemment
                    )
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Hi, kindly fill in the form to proceed combat",
                    style = AppTheme.types.text.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                )
                Spacer(Modifier.height(13.dp))
            }

            AppTextInput(
                value = state.fullName,
                onValueChange = {
                    viewModel.updateFields {
                        copy(fullName = it)
                    }
                },
                placeholder = "Full Name"
            )
            AppTextInput(
                value = state.username,
                onValueChange = {
                    viewModel.updateFields {
                        copy(username = it)
                    }
                },
                placeholder = "User Name"
            )
            Row() {
                AppTextInput(
                    value = state.phonePrefix,
                    onValueChange = {
                        viewModel.updateFields {
                            copy(phonePrefix = it)
                        }
                    },
                    placeholder = "+234",
                    modifier = Modifier.width(35.dp)
                )
                Spacer(Modifier.width(18.dp))
                AppTextInput(
                    value = state.phone,
                    onValueChange = {
                        viewModel.updateFields {
                            copy(phone = it)
                        }
                    },
                    placeholder = "Your Phone"
                )
            }
            AppTextInput(
                value = state.email,
                onValueChange = {
                    viewModel.updateFields {
                        copy(email = it)
                    }
                },
                placeholder = "Email"
            )
            AppTextInput(
                value = state.password,
                onValueChange = {
                    viewModel.updateFields {
                        copy(password = it)
                    }
                },
                placeholder = "Password"
            )
            AppTextInput(
                value = state.passwordConfirm,
                onValueChange = {
                    viewModel.updateFields {
                        copy(passwordConfirm = it)
                    }
                },
                placeholder = "Confirm Password"
            )
        }
        Spacer(Modifier.height(44.dp))
        Column(
            Modifier.width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppButton(
                text = "Create Account",
                {
                    viewModel.onNext()
                },
                modifier = Modifier.width(224.dp)
            )
            Spacer(Modifier.height(23.dp))
            Text(
                text = "Connect With:",
                style = AppTheme.types.text.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = AppTheme.colors.redElemment
                )
            )
            Spacer(Modifier.height(7.dp))
            Row() {
                Icon(
                    painter = painterResource(R.drawable.goole),
                    tint = null,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(15.dp))
                Icon(
                    painter = painterResource(R.drawable.facebook),
                    tint = null,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Already have an account?",
                style = AppTheme.types.text.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "Login",
                style = AppTheme.types.text.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = AppTheme.colors.redElemment
                )
            )
            Spacer(Modifier.height(5.dp))
        }
    }

}