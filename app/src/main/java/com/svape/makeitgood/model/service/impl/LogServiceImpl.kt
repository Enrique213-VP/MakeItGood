package com.svape.makeitgood.model.service.impl

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.svape.makeitgood.model.service.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor() : LogService {
    override fun logNonFatalCrash(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }
}