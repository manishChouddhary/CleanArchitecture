package com.rxsense.cleanarchitecture.dpendencyinjection

import androidx.lifecycle.ViewModelProvider
import com.rxsense.cleanarchitecture.dpendencyinjection.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Generic builder for view model creation by dagger
 */
@Module
internal abstract class ViewModelBuilder {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: DaggerViewModelFactory
    ): ViewModelProvider.Factory
}
