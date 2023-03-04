package com.svape.makeitgood.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.svape.makeitgood.SETTINGS_SCREEN
import com.svape.makeitgood.SIGN_UP_SCREEN
import com.svape.makeitgood.common.ext.isValidEmail
import com.svape.makeitgood.common.ext.isValidPassword
import com.svape.makeitgood.common.ext.passwordMatches
import com.svape.makeitgood.common.snackbar.SnackbarManager
import com.svape.makeitgood.model.service.*
import com.svape.makeitgood.screens.MakeItGoodViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.svape.makeitgood.R.string as AppText
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : MakeItGoodViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
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

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            accountService.linkAccount(email, password)
            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
        }
    }
}