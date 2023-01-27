package com.svape.makeitgood.model.service.module

import com.svape.makeitgood.model.service.AccountService
import com.svape.makeitgood.model.service.ConfigurationService
import com.svape.makeitgood.model.service.LogService
import com.svape.makeitgood.model.service.StorageService
import com.svape.makeitgood.model.service.impl.AccountServiceImpl
import com.svape.makeitgood.model.service.impl.ConfigurationServiceImpl
import com.svape.makeitgood.model.service.impl.LogServiceImpl
import com.svape.makeitgood.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}