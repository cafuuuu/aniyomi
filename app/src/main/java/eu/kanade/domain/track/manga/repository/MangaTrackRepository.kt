package eu.kanade.domain.track.manga.repository

import eu.kanade.domain.track.manga.model.MangaTrack
import kotlinx.coroutines.flow.Flow

interface MangaTrackRepository {

    suspend fun getTrackByMangaId(id: Long): MangaTrack?

    suspend fun getTracksByMangaId(mangaId: Long): List<MangaTrack>

    fun getMangaTracksAsFlow(): Flow<List<MangaTrack>>

    fun getTracksByMangaIdAsFlow(mangaId: Long): Flow<List<MangaTrack>>

    suspend fun deleteManga(mangaId: Long, syncId: Long)

    suspend fun insertManga(track: MangaTrack)

    suspend fun insertAllManga(tracks: List<MangaTrack>)
}
