package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.NoBodyAction2DialogBinding
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.util.extension.setOnSingleClickListener

class HomeDialogFragment(userName: String) :
    BindingDialogFragment<NoBodyAction2DialogBinding>(R.layout.no_body_action2_dialog) {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val name = userName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dialogTitle.setText(getString(R.string.dialog_home_user_delete_title))
            btnLeft.setText(getString(R.string.dialog_cancel_text))
            btnRight.setText(getString(R.string.dialog_apply_text))
            btnLeft.setOnSingleClickListener {
                dismiss()
            }
            btnRight.setOnSingleClickListener {
                homeViewModel.deleteProfile(name)
                dismiss()
            }
        }
    }
}
