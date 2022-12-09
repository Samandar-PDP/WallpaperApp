package uz.digital.wallpaperapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import uz.digital.wallpaperapp.R
import uz.digital.wallpaperapp.adapter.ViewPagerAdapter
import uz.digital.wallpaperapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                binding.tabLayout.getTabAt(position)?.orCreateBadge?.number
            }
        })
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                binding.tabLayout.getTabAt(tab?.position!!)?.removeBadge()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("All"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Animals"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("New"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Technology"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}