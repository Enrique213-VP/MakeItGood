package com.svape.makeitgood.screens.login

import androidx.compose.runtime.mutableStateOf
import com.svape.makeitgood.LOGIN_SCREEN
import com.svape.makeitgood.SETTINGS_SCREEN
import com.svape.makeitgood.common.ext.isValidEmail
import com.svape.makeitgood.common.snackbar.SnackbarManager
import com.svape.makeitgood.model.service.AccountService
import com.svape.makeitgood.model.service.LogService
import com.svape.makeitgood.screens.MakeItGoodViewModel
import com.svape.makeitgood.R.string as AppText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
): MakeItGoodViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
    private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }
}