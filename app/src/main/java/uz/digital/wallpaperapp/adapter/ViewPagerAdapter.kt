package uz.digital.wallpaperapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.digital.wallpaperapp.fragments.tab_fragment.AllFragment
import uz.digital.wallpaperapp.fragments.tab_fragment.AnimalsFragment
import uz.digital.wallpaperapp.fragments.tab_fragment.NewFragment
import uz.digital.wallpaperapp.fragments.tab_fragment.TechnologyFragment

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AllFragment()
            1 -> AnimalsFragment()
            2 -> NewFragment()
            3 -> TechnologyFragment()
            else -> Fragment()
        }
    }
}