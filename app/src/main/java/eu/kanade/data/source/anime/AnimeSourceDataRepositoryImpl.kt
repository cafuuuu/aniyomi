package eu.kanade.data.source.anime

import eu.kanade.data.handlers.anime.AnimeDatabaseHandler
import eu.kanade.domain.source.anime.model.AnimeSourceData
import eu.kanade.domain.source.anime.repository.AnimeSourceDataRepository
import kotlinx.coroutines.flow.Flow

class AnimeSourceDataRepositoryImpl(
    private val handler: AnimeDatabaseHandler,
) : AnimeSourceDataRepository {

    override fun subscribeAllAnime(): Flow<List<AnimeSourceData>> {
        return handler.subscribeToList { animesourcesQueries.findAll(animeSourceDataMapper) }
    }

    override suspend fun getAnimeSourceData(id: Long): AnimeSourceData? {
        return handler.awaitOneOrNull { animesourcesQueries.findOne(id, animeSourceDataMapper) }
    }

    override suspend fun upsertAnimeSourceData(id: Long, lang: String, name: String) {
        handler.await { animesourcesQueries.upsert(id, lang, name) }
    }
}
