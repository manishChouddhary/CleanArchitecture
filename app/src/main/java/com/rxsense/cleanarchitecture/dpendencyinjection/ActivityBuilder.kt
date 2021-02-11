package com.rxsense.cleanarchitecture.dpendencyinjection

import com.rxsense.cleanarchitecture.viewcomponent.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}
