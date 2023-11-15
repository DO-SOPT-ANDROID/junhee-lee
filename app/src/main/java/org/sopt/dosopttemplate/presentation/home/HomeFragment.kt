package org.sopt.dosopttemplate.presentation.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var isFabOpen = false
    private val isLandscape by lazy { resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE }
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeRvAdapter by lazy {
        HomeRecyclerAdapter(
            onClick = moveToProfileDetail(),
            deleteUser = { deleteUserDialog(it) }
        )
    }
    private val homeVpAdapter by lazy {
        HomeVpAdapter(
            onClick = moveToProfileDetail(),
            deleteUser = { deleteUserDialog(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getProfileList()
        setFABClickEvent()
        initResultLauncher()
    }

    private fun setAdapter() {
        with(binding) {
            if (isLandscape) {
                vpHome.adapter = homeVpAdapter
                vpHome.visibility = View.VISIBLE
                rvHome.visibility = View.GONE
                springDotsIndicator.attachTo(vpHome)
            } else {
                rvHome.adapter = homeRvAdapter
                rvHome.visibility = View.VISIBLE
                vpHome.visibility = View.GONE
                springDotsIndicator.visibility = View.GONE
            }
        }

        homeViewModel.profileData.observe(viewLifecycleOwner) { profileList ->
            if (isLandscape) {
                homeVpAdapter.submitList(profileList)
            } else {
                homeRvAdapter.submitList(profileList)
            }
        }
    }

    private fun moveToProfileDetail(): (Int) -> Unit {
        return { position ->
            val intent = Intent(requireContext(), HomeProfileDetailActivity::class.java)
            val profileList = homeViewModel.profileData.value
            intent.putExtra(PROFILE_LIST, profileList?.get(position) as Parcelable)
            startActivity(intent)
        }
    }

    private fun deleteUserDialog(userName: String) {
        HomeDialogFragment().apply {
            titleText = this@HomeFragment.getString(R.string.dialog_home_user_delete_title)
            confirmButtonText = this@HomeFragment.getString(R.string.dialog_apply_text)
            dismissButtonText = this@HomeFragment.getString(R.string.dialog_cancel_text)
            confirmClickListener = {
                homeViewModel.deleteProfile(userName)
                dismiss()
            }
            dismissClickListener = {
                dismiss()
            }
        }.show(childFragmentManager, "")
    }

    private fun getProfileList() {
        homeViewModel.initProfileList()
        homeViewModel.fetchProfileList()
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
                val intent = Intent(requireContext(), HomeAddProfileActivity::class.java)
                resultLauncher.launch(intent)
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

    private fun initResultLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                homeViewModel.fetchProfileList()
            }
        }
    }

    override fun onDestroyView() {
        binding.rvHome.adapter = null
        binding.vpHome.adapter = null
        super.onDestroyView()
    }

    companion object {
        const val PROFILE_LIST = "profile"
        private const val TOP_POSITION = 0
    }
}