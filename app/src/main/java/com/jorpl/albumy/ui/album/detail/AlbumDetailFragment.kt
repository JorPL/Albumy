package com.jorpl.albumy.ui.album.detail

import android.annotation.SuppressLint
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
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController


/**
 * A fragment representing a single Album detail screen.
 */
@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {

    private val model: AlbumViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private lateinit var binding: FragmentAlbumDetailBinding

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        binding.albumDetailPhotos.layoutManager = GridLayoutManager(binding.albumDetailPhotos.context, 2)
        binding.albumDetailToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        (activity as AppCompatActivity?)?.supportActionBar!!.setShowHideAnimationEnabled(false)
        (activity as AppCompatActivity?)?.supportActionBar!!.hide()

        model.select.observe(viewLifecycleOwner) { album ->
            binding.albumDetailToolbarLayout.title = album.title
            Glide.with(this)
                .load(album.banner)
                .into(binding.albumDetailAppbarBanner)
            binding.albumDetailPhotos.adapter = AlbumDetailAdapter(binding.albumDetailPhotos.context, album.photos)
        }

        return binding.root
    }
}