package org.sopt.dosopttemplate.presentation.android

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class DoAndroidFragment : BindingFragment<FragmentDoAndroidBinding>(R.layout.fragment_do_android) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
