package com.example.myapplication23.screen.home


import android.util.Log
import androidx.core.view.isVisible
import com.example.myapplication23.databinding.FragmentHomeBinding
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.home.homelist.HomeCategory
import com.example.myapplication23.screen.home.homelist.HomeListFragment
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import com.example.myapplication23.widget.adapter.HomeListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment
    : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var viewPagerAdapter: HomeListFragmentPagerAdapter

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
    }

    override fun observeData() = with(binding) {
//        initViewPager()
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LocationState.Success -> {
                    initViewPager()
                }
            }
        }
    }

    private fun initViewPager() = with(binding) {
        orderChipGroup.isVisible = true

        if (::viewPagerAdapter.isInitialized.not()) {
            val homeCategory = HomeCategory.values()

            val homeListFragmentList = homeCategory.map {
//                RestaurantListFragment.newInstance(it, locationLatLng)
                HomeListFragment.newInstance(it)
            }
            viewPagerAdapter = HomeListFragmentPagerAdapter(
                this@HomeFragment,
                homeListFragmentList,
//                locationLatLng
            )
            viewPager.adapter = viewPagerAdapter

            viewPager.offscreenPageLimit = homeCategory.size

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setText(HomeCategory.values()[position].categoryNameId)
            }.attach()
        }


//        if (locationLatLng != viewPagerAdapter.locationLatLng) {
//            viewPagerAdapter.locationLatLng = locationLatLng
//            viewPagerAdapter.fragmentList.forEach {
//                it.viewModel.setLocationLatLng(locationLatLng)
//            }
//        }
    }


    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() : HomeFragment {
            return HomeFragment().apply {

            }
        }
    }
}