package com.rxsense.cleanarchitecture

import android.app.Application
import com.rxsense.cleanarchitecture.dpendencyinjection.DIComponent
import com.rxsense.cleanarchitecture.dpendencyinjection.DIModule
import com.rxsense.cleanarchitecture.dpendencyinjection.DaggerDIComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Application class for application level initializations
 */
class CleanArchApplication : Application(), HasAndroidInjector {
    companion object {
        lateinit var diComponent: DIComponent
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        diComponent = DaggerDIComponent.builder()
            .appModule(DIModule(this))
            .application(applicationContext)
            .build()
        diComponent.inject(this)
    }
}