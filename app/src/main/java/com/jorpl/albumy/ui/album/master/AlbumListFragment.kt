package com.jorpl.albumy.ui.album.master

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jorpl.albumy.R
import com.jorpl.albumy.databinding.FragmentAlbumListBinding
import com.jorpl.albumy.databinding.ItemAlbumBinding
import com.jorpl.albumy.models.Album
import com.jorpl.albumy.ui.album.master.adapter.AlbumListAdapter
import com.jorpl.albumy.view_model.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A Fragment representing a list of albums. On
 * handsets, the fragment presents a list of albums, which when touched,
 * lead to a {@link AlbumDetailFragment} representing
 * album details.
 */
@AndroidEntryPoint
class AlbumListFragment : Fragment() {

    private val model: AlbumViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private lateinit var binding: FragmentAlbumListBinding
    private lateinit var onContextClickListener: View.OnContextClickListener
    private lateinit var onClickListener: View.OnClickListener

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)?.supportActionBar!!.setShowHideAnimationEnabled(false)
        (activity as AppCompatActivity?)?.supportActionBar!!.show()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        val recyclerView: RecyclerView = binding.albumsList
        setupRecyclerView(recyclerView, onClickListener, onContextClickListener)
    }

    /**
     * Init all listener of this fragment
     */
    private fun initListener() {
        binding.albumsTryAgain.setOnClickListener {
            binding.albumsTryAgain.visibility = View.GONE
            binding.albumsLoader.visibility = View.VISIBLE
            model.getAlbums()
        }
        onClickListener = View.OnClickListener { itemView ->
            val item = itemView.tag as Album
            model.select(item)
            itemView.findNavController().navigate(R.id.show_item_detail)
        }
        onContextClickListener = View.OnContextClickListener { v ->
            val item = v.tag as Album
            Toast.makeText(
                v.context,
                "Context click of item " + item.id,
                Toast.LENGTH_LONG
            ).show()
            true
        }
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
        onContextClickListener: View.OnContextClickListener
    ) {
        model.albums.observe(viewLifecycleOwner) {
            binding.albumsLoader.visibility = View.GONE
            if (it.isEmpty()) {
                binding.albumsTryAgain.visibility = View.VISIBLE
            } else {
                recyclerView.adapter = AlbumListAdapter(
                    it,
                    onClickListener,
                    onContextClickListener
                )
            }
        }
    }

}