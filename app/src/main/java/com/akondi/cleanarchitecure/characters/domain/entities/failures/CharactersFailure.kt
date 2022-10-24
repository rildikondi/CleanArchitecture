package com.akondi.cleanarchitecure.characters.domain.entities.failures

import com.akondi.cleanarchitecure.core.exception.Failure

class CharactersFailure {
    class CharactersNotAvailable: Failure.FeatureFailure()
    class NonExistentCharacter: Failure.FeatureFailure()
}