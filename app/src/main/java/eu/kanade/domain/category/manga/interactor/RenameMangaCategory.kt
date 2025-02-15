package eu.kanade.domain.category.manga.interactor

import eu.kanade.domain.category.manga.repository.MangaCategoryRepository
import eu.kanade.domain.category.model.Category
import eu.kanade.domain.category.model.CategoryUpdate
import eu.kanade.domain.category.model.anyWithName
import eu.kanade.tachiyomi.util.lang.withNonCancellableContext
import eu.kanade.tachiyomi.util.system.logcat
import logcat.LogPriority

class RenameMangaCategory(
    private val categoryRepository: MangaCategoryRepository,
) {

    suspend fun await(categoryId: Long, name: String) = withNonCancellableContext {
        val categories = categoryRepository.getAllMangaCategories()
        if (categories.anyWithName(name)) {
            return@withNonCancellableContext Result.NameAlreadyExistsError
        }

        val update = CategoryUpdate(
            id = categoryId,
            name = name,
        )

        try {
            categoryRepository.updatePartialMangaCategory(update)
            Result.Success
        } catch (e: Exception) {
            logcat(LogPriority.ERROR, e)
            Result.InternalError(e)
        }
    }

    suspend fun await(category: Category, name: String) = await(category.id, name)

    sealed class Result {
        object Success : Result()
        object NameAlreadyExistsError : Result()
        data class InternalError(val error: Throwable) : Result()
    }
}
