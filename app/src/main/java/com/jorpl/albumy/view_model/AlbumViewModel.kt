package com.jorpl.albumy.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorpl.albumy.models.Album
import com.jorpl.albumy.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel shared between AlbumDetailFragment & AlbumListFragment
 *
 * @property albumRepository
 */
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

    /**
     * To call when album selected to notify all observers
     *
     * @param item album selected
     */
    fun select(item: Album) {
        select.value = item
    }

}