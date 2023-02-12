package com.svape.makeitgood.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svape.makeitgood.common.snackbar.SnackbarManager
import com.svape.makeitgood.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.svape.makeitgood.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class MakeItGoodViewModel(private val logService: LogService) : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )
}