package com.rxsense.cleanarchitecture.dpendencyinjection

import dagger.Module

/**
 *
 */
@Module(
    includes = [
        ViewModelBuilder::class,
        ActivityBuilder::class,
        ActivityViewModelBuilder::class]
)
class DIModule {
}