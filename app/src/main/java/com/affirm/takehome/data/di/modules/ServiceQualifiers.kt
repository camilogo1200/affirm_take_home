package com.affirm.takehome.data.di.modules

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationRetention.RUNTIME

@Retention(BINARY)
@Qualifier
annotation class ApiYelpService


@Retention(BINARY)
@Qualifier
annotation class ApiPlacesService
