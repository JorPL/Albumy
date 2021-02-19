package com.jorpl.albumy.repository

import com.jorpl.albumy.models.Album
import com.jorpl.albumy.provider.webservice.my_json_server_typicode.AlbumyApi
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val api: AlbumyApi
) {

    suspend fun getAlbums(): List<Album> {
        return api.getAlbums()
    }

}