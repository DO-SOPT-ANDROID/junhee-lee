package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.FriendProfileEntity
import org.sopt.dosopttemplate.databinding.ActivityHomeAddProfileBinding
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE_LIST
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class HomeAddProfileActivity :
    BindingActivity<ActivityHomeAddProfileBinding>(R.layout.activity_home_add_profile) {

    private val datePickerDialogFragment = HomeDatePickerDialogFragment()
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupDatePicker(datePickerDialogFragment)
        clickAddProfileEnd()

    }

    private fun setupDatePicker(datePickerDialogFragment: HomeDatePickerDialogFragment) {
        datePickerDialogFragment.setDatePickerDialogListener(object : DatePickerDialogListener {
            override fun onDateSelected(date: String) {
                binding.etHomeAddBirthday.setText(date)
            }
        })

        binding.ivHomeAddBirthday.setOnSingleClickListener {
            datePickerDialogFragment.show(supportFragmentManager, "DatePickerDialog")
        }
    }

    private fun clickAddProfileEnd() {
        binding.btnHomeAdd.setOnSingleClickListener {
            with(binding) {
                if (etHomeAddName.text.toString().isNotBlank()
                    && etHomeAddBirthday.text.toString().isNotBlank()
                ) {
                    val intent = Intent(this@HomeAddProfileActivity, HomeActivity::class.java)
                    val birthday = LocalDate.parse(
                        etHomeAddBirthday.text.toString(),
                        DateTimeFormatter.ofPattern("yyyyMMdd")
                    )
                    val profileInfo = FriendProfileEntity(
                        name = etHomeAddName.text.toString(),
                        music = etHomeAddMusic.text.toString(),
                        birthday = birthday,
                        imageUri = R.drawable.img_default_profile,
                        isMusicRegist = false,
                        isTodayBirthday = false
                    )
                    homeViewModel.addFriend(profileInfo)
                    intent.putExtra(PROFILE_LIST, profileInfo)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    showSnackbar(binding.root, getString(R.string.signup_error_message))
                }
            }
        }
    }

}