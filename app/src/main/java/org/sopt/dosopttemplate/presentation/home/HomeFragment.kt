package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DummyProfile
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeAdapter by lazy { HomeAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHome()
    }

    private fun initHome() {
        val homeProfileList = DummyProfile().mockProfileList
        binding.rvHome.adapter = homeAdapter
        homeAdapter.submitList(homeProfileList)
    }
}
