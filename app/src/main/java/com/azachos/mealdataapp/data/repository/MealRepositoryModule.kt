package com.azachos.mealdataapp.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract  class MealRepositoryModule {

    @Binds
    abstract fun bindMealRepository(mealRepositoryImpl: MealRepositoryImpl) : MealRepository
}