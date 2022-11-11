package com.gricsan.caradaro.features.user_list.domain.usecases

import com.gricsan.caradaro.base.domain.models.Result
import com.gricsan.caradaro.features.user_list.domain.contracts.UserRepository
import com.gricsan.caradaro.features.user_list.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUsersUseCase(
    private val repo: UserRepository
) {

    operator fun invoke(): Flow<Result<List<User>>> = flow {
        try {
            emit(Result.Loading())
            val users = repo.getUsers()
            emit(Result.Success(users))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occurred!"))
        } catch (e: IOException) {
            emit(Result.Error("Request failed, please try again later."))
        }
    }

}