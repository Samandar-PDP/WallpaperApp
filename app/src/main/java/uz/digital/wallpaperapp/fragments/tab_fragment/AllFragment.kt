package uz.digital.wallpaperapp.fragments.tab_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.digital.wallpaperapp.R
import uz.digital.wallpaperapp.adapter.ImageAdapter
import uz.digital.wallpaperapp.util.Constants

class AllFragment : Fragment(R.layout.fragment_all) {
    private val imageAdapter by lazy { ImageAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv: RecyclerView = view.findViewById(R.id.rv)
        rv.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        imageAdapter.submitList(Constants.imageList())
        imageAdapter.onClick = {
            val bundle = bundleOf("image" to it)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }
}