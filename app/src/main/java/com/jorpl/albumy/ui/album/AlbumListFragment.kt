package com.jorpl.albumy.ui.album

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jorpl.albumy.R
import com.jorpl.albumy.databinding.FragmentAlbumListBinding
import com.jorpl.albumy.databinding.ItemAlbumBinding
import com.jorpl.albumy.models.Album
import com.jorpl.albumy.view_model.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

@AndroidEntryPoint
class AlbumListFragment : Fragment() {

    private val model: AlbumViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    private val unhandledKeyEventListenerCompat = ViewCompat.OnUnhandledKeyEventListenerCompat { v, event ->
        if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
            Toast.makeText(
                v.context,
                "Undo (Ctrl + Z) shortcut triggered",
                Toast.LENGTH_LONG
            ).show()
            true
        } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
            Toast.makeText(
                v.context,
                "Find (Ctrl + F) shortcut triggered",
                Toast.LENGTH_LONG
            ).show()
            true
        }
        false
    }

    private var _binding: FragmentAlbumListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()

        val recyclerView: RecyclerView = binding.albumsList

        /** Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        val onClickListener = View.OnClickListener { itemView ->
            val item = itemView.tag as Album
            model.select(item)
            itemView.findNavController().navigate(R.id.show_item_detail)
        }

        /**
         * Context click listener to handle Right click events
         * from mice and trackpad input to provide a more native
         * experience on larger screen devices
         */
        val onContextClickListener = View.OnContextClickListener { v ->
            val item = v.tag as Album
            Toast.makeText(
                v.context,
                "Context click of item " + item.id,
                Toast.LENGTH_LONG
            ).show()
            true
        }
        setupRecyclerView(recyclerView, onClickListener, onContextClickListener)
    }

    private fun initListener() {
        binding.albumsTryAgain.setOnClickListener {
            binding.albumsTryAgain.visibility = View.GONE
            binding.albumsLoader.visibility = View.VISIBLE
            model.getAlbums()
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
                recyclerView.adapter = SimpleItemRecyclerViewAdapter(
                    it,
                    onClickListener,
                    onContextClickListener
                )
            }
        }
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: List<Album>,
        private val onClickListener: View.OnClickListener,
        private val onContextClickListener: View.OnContextClickListener
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id.toString()
            holder.contentView.text = item.title

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setOnContextClickListener(onContextClickListener)
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
            val idView: TextView = binding.idText
            val contentView: TextView = binding.content
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}