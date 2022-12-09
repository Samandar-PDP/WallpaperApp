package uz.digital.wallpaperapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.digital.wallpaperapp.R
import uz.digital.wallpaperapp.databinding.FragmentDetailBinding
import uz.digital.wallpaperapp.databinding.FragmentHomeBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var image: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image = arguments?.getInt("image")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image?.let {
            binding.constraintLayout.setBackgroundResource(it)
        }
        binding.floatingActionButton2.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomView = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        bottomView.setContentView(R.layout.bottom_layout)
        bottomView.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}