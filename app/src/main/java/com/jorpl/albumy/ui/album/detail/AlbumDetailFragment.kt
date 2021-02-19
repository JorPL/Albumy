package com.jorpl.albumy.ui.album.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.jorpl.albumy.R
import com.jorpl.albumy.databinding.FragmentAlbumDetailBinding
import com.jorpl.albumy.ui.album.detail.adapter.AlbumDetailAdapter
import com.jorpl.albumy.view_model.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a single Album detail screen.
 */
@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {

    private val model: AlbumViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private var _binding: FragmentAlbumDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        binding.itemAlbumPhotos.layoutManager = GridLayoutManager(binding.itemAlbumPhotos.context, 2)

        model.select.observe(viewLifecycleOwner) { album ->
            binding.toolbarLayout.title = album.title
            Glide.with(this)
                .load(album.banner)
                .into(binding.appbarBanner)
            binding.itemAlbumPhotos.adapter = AlbumDetailAdapter(binding.itemAlbumPhotos.context, album.photos)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}