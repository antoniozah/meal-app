package com.azachos.mealdataapp.data.repository

import com.azachos.mealdataapp.data.NetworkResult
import com.azachos.mealdataapp.data.api.MealAppService
import com.azachos.mealdataapp.data.mappers.toDomain
import com.azachos.mealdataapp.domain.models.CategoryMeal
import com.azachos.mealdataapp.domain.models.MealCategory
import com.azachos.mealdataapp.domain.models.MealDetails
import com.azachos.mealdataapp.domain.models.RandomRecipeMeal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealAppService: MealAppService
) : MealRepository {
    override suspend fun randomRecipe(): Flow<NetworkResult<List<RandomRecipeMeal>>> {
        return flow {
            emit(NetworkResult.Loading())
            try {
                val response = mealAppService.getRandom()

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val domainMeals = responseBody?.let {
                        it.meals.map {mealDto ->
                            mealDto.toDomain()
                        }
                    } ?: emptyList()
                    emit(NetworkResult.Success(data = domainMeals))
                } else {
                    emit(NetworkResult.Error(message = response.code().toString(), data = null))
                }

            } catch (e: Exception) {
                emit(
                    NetworkResult.Error(
                        message = e.localizedMessage ?: "Something did wrong",
                        data = null
                    )
                )
            }
        }
    }

    override suspend fun mealCategories(): Flow<NetworkResult<List<MealCategory>>> {
        return flow {
            emit(NetworkResult.Loading())
            try {
                val response = mealAppService.getMealCategories()

                if(response.isSuccessful) {
                    val domainMealCategories = response.body()?.let {
                        it.categories.map { category ->
                            category.toDomain()
                        }
                    } ?: emptyList()
                    emit(NetworkResult.Success(data = domainMealCategories))
                } else {
                    emit(NetworkResult.Error(message = "Error with code ${response.code()}", data = null))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(message = e.localizedMessage ?: "Something did wrong", data = null))
            }
        }
    }

    override suspend fun categoryMeals(category: String): Flow<NetworkResult<List<CategoryMeal>>> {
        return flow {
            emit(NetworkResult.Loading())
            try {
                val response = mealAppService.getMealsByCategory(category)
                if(response.isSuccessful) {
                    val mealsByCategory = response.body()?.let {
                        it.meals.map {meal ->
                            meal.toDomain()
                        }
                    } ?: emptyList()
                    emit(NetworkResult.Success(data = mealsByCategory))
                } else {
                    emit(NetworkResult.Error(message = "Error with code ${response.code()}", data = null))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(message = e.localizedMessage ?: "Something did wrong", data = null))
            }
        }
    }

    override suspend fun singleMeal(mealId: Int): Flow<NetworkResult<List<MealDetails>>> {
        return flow {
            emit(NetworkResult.Loading())
            try {
                val response = mealAppService.getMealById(mealId)
                if(response.isSuccessful) {
                    val mealDetails = response.body()?.let {
                        it.meals.map { meal ->
                            meal.toDomain()
                        }
                    } ?: emptyList()
                    emit(NetworkResult.Success(data = mealDetails))
                } else {
                    emit(NetworkResult.Error(message = "Error with code ${response.code()}", data = null))
                }
            } catch(e: Exception) {
                emit(NetworkResult.Error(message = e.localizedMessage ?: "Something did wrong"))
            }
        }
    }
}