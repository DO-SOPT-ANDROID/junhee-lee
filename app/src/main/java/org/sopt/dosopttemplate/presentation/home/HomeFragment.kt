package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeRvAdapter by lazy {
        HomeRecyclerAdapter(
            onClick = { position ->
                val intent = Intent(requireContext(), HomeProfileDetailActivity::class.java)
                val profileList = homeViewModel.profileData.value
                intent.putExtra(PROFILE_LIST, profileList?.get(position) as Parcelable)
                startActivity(intent)
            },
        )
    }
    private val homeVpAdapter by lazy { HomeVpAdapter() }
    private val isLandscape by lazy { resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE }
    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getProfileList()
    }

    private fun setAdapter() {
        if (isLandscape) {
            binding.vpHome.adapter = homeVpAdapter
            binding.vpHome.visibility = View.VISIBLE
            binding.rvHome.visibility = View.GONE
            binding.springDotsIndicator.attachTo(binding.vpHome)
        } else {
            binding.rvHome.adapter = homeRvAdapter
            binding.rvHome.visibility = View.VISIBLE
            binding.vpHome.visibility = View.GONE
            binding.springDotsIndicator.visibility = View.GONE
        }

        homeViewModel.profileData.observe(viewLifecycleOwner) { profileList ->
            if (isLandscape) {
                homeVpAdapter.submitList(profileList)
            } else {
                homeRvAdapter.submitList(profileList)
            }
        }
    }


    private fun getProfileList() {
        homeViewModel.getProfileList()
    }

    fun goToTop() {
        binding.rvHome.smoothScrollToPosition(TOP_POSITION)
    }

    companion object {
        const val PROFILE_LIST = "profile"
        private const val TOP_POSITION = 0
    }
}