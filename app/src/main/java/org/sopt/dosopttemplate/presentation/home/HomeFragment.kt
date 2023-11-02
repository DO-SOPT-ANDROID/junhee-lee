package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeAdapter by lazy { HomeAdapter() }

    private val homeViewModel by activityViewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateHome()
        getProfileList()
    }

    private fun updateHome() {
        binding.rvHome.adapter = homeAdapter
        homeViewModel.profileData.observe(viewLifecycleOwner) { profileList ->
            homeAdapter.submitList(profileList)
        }
    }

    private fun getProfileList() {
        homeViewModel.getProfileList()
    }
}
