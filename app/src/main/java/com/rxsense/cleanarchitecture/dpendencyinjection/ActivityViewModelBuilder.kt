package com.rxsense.cleanarchitecture.dpendencyinjection

import androidx.lifecycle.ViewModel
import com.rxsense.cleanarchitecture.viewcomponent.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * View model builder for activity view-models
 */
@Module
interface ActivityViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewmodel: MainViewModel): ViewModel
}
