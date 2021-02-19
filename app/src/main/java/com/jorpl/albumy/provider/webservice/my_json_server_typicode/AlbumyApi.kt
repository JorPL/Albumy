package com.jorpl.albumy.provider.webservice.my_json_server_typicode

import com.jorpl.albumy.models.Album
import retrofit2.http.GET

interface AlbumyApi {

    @GET("posts")
    suspend fun getAlbums(): List<Album>

}