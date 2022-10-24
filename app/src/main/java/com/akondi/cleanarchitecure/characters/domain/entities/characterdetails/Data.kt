package com.akondi.cleanarchitecure.characters.domain.entities.characterdetails

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
) {
    companion object {
        fun empty() = Data(
            0,
            0,
            0,
            emptyList(),
            0
        )
    }
}