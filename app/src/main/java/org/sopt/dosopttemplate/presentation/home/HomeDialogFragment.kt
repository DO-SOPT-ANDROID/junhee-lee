package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.NoBodyAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment

class HomeDialogFragment :
    BindingDialogFragment<NoBodyAction2DialogBinding>(R.layout.no_body_action2_dialog) {
    var titleText: String? = null
    var confirmButtonText: String? = null
    var dismissButtonText: String? = null
    var confirmClickListener: (() -> Unit)? = null
    var dismissClickListener: (() -> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        titleText?.let {
            binding.dialogTitle.visibility = View.VISIBLE
            binding.dialogTitle.text = it
        } ?: kotlin.run {
            binding.dialogTitle.visibility = View.GONE
        }
        confirmButtonText?.let {
            binding.btnRight.text = it
        }
        dismissButtonText?.let {
            binding.btnLeft.text = it
        }

        confirmClickListener?.let {
            binding.btnRight.visibility = View.VISIBLE
            binding.btnRight.setOnClickListener {
                confirmClickListener?.invoke()
            }
        } ?: kotlin.run {
            binding.btnRight.visibility = View.GONE
        }

        dismissClickListener?.let {
            binding.btnLeft.visibility = View.VISIBLE
            binding.btnLeft.setOnClickListener {
                dismissClickListener?.invoke()
            }
        } ?: run {
            binding.btnLeft.visibility = View.GONE
        }
    }
}
