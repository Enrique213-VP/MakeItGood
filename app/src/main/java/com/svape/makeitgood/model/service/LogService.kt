package com.svape.makeitgood.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}