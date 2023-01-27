package com.svape.makeitgood.model.service

interface ConfigurationService {

    suspend fun fetchConfiguration(): Boolean
    val isShowTaskEditButtonConfig: Boolean
}