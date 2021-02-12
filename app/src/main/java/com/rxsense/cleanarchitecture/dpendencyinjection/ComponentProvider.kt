package com.rxsense.cleanarchitecture.dpendencyinjection

import com.rxsense.cleanarchitecture.datacomponent.Repository
import com.rxsense.cleanarchitecture.datacomponent.networkservice.IService
import com.rxsense.cleanarchitecture.domain.IRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Module to provide feature level dependencies can be different
 * for the every feature.
 */
@Module(includes = [IComponentProvider::class])
class ComponentProvider {
    @Provides
    fun provideService(retrofit: Retrofit): IService = retrofit.create(IService::class.java)
}

@Module
interface IComponentProvider {
    @Binds
    fun bindRepository(repository: Repository): IRepository
}