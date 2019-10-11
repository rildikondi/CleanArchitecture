package com.akondi.cleanarchitecure.merchants.domain.usecases

import android.content.Context
import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.functional.Either
import com.akondi.cleanarchitecure.core.functional.Either.Right
import com.akondi.cleanarchitecure.core.interactor.UseCase
import com.akondi.cleanarchitecure.core.navigation.Navigator
import javax.inject.Inject

class PlayMerchant
@Inject constructor(private val context: Context,
                    private val navigator: Navigator
) : UseCase<UseCase.None, PlayMerchant.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}