package org.sopt.dosopttemplate.presentation.home

import android.animation.ObjectAnimator
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
    private var isFabOpen = false
    private val isLandscape by lazy { resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE }
    private val homeViewModel by activityViewModels<HomeViewModel>()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getProfileList()
        setFABClickEvent()
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

    private fun setFABClickEvent() {
        with(binding) {
            fabMain.setOnClickListener {
                toggleFab()
            }

            fabAddPerson.setOnClickListener {

            }
        }
    }

    private fun toggleFab() {
        with(binding) {
            if (isFabOpen) {
                ObjectAnimator.ofFloat(fabAddPerson, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(fabMain, View.ROTATION, 45f, 0f).apply { start() }
            } else {
                ObjectAnimator.ofFloat(fabAddPerson, "translationY", -200f).apply { start() }
                ObjectAnimator.ofFloat(fabMain, View.ROTATION, 0f, 45f).apply { start() }
            }
        }
        isFabOpen = !isFabOpen
    }

    companion object {
        const val PROFILE_LIST = "profile"
        private const val TOP_POSITION = 0
    }
}