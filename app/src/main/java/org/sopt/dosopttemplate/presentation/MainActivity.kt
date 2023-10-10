package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import sopt.uni.util.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvNickname.text = intent.getStringExtra("name")
        binding.tvId.text = intent.getStringExtra("id")
        binding.tvMbti.text = intent.getStringExtra("mbti")
    }
}
