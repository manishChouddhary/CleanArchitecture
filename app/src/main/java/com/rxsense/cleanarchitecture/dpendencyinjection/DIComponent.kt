package com.rxsense.cleanarchitecture.dpendencyinjection

import android.content.Context
import com.rxsense.cleanarchitecture.CleanArchApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Dagger component for application
 */
@Component(modules = [AndroidInjectionModule::class, DIModule::class])
@Singleton
interface DIComponent {
    fun inject(application: CleanArchApplication)

    @Component.Builder
    abstract class Builder {
        abstract fun build(): DIComponent
        abstract fun appModule(appModule: DIModule): Builder

        @BindsInstance
        abstract fun application(context: Context): Builder
    }
}