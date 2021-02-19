package com.jorpl.albumy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorpl.albumy.models.Album
import com.jorpl.albumy.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
): ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums
    val select = MutableLiveData<Album>()

    init {
        viewModelScope.launch {
            _albums.value = albumRepository.getAlbums()
        }
    }

    fun select(item: Album) {
        select.value = item
    }

}