package com.paulo.mymovie.domain.usecases.favorite

data class UseCasesFavorite (
    val get: GetAllFavoriteUseCase,
    val save:  SaveFavoriteUseCase,
    val remove:  RemoveFavoriteUseCase,
    val byCode:  GetFavoriteUseCase
)