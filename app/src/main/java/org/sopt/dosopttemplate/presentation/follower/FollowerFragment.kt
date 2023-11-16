package org.sopt.dosopttemplate.presentation.follower

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentFollowerBinding
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.TOP_POSITION
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingFragment
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class FollowerFragment : BindingFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {

    private val followerViewModel: FollowerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observeFollowerList()
    }

    private fun init() {
        followerViewModel.getFollowerList()
    }

    private fun observeFollowerList() {
        val followerAdapter = FollowerAdapter()
        followerViewModel.followerList.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    binding.rvFollower.adapter = followerAdapter
                    followerAdapter.submitList(uiState.data)
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, getString(R.string.follower_fail_text))
                }

                else -> {}
            }

        }
    }

    fun goToTop() {
        binding.rvFollower.smoothScrollToPosition(TOP_POSITION)
    }
}