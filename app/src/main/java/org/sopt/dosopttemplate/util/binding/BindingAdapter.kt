package org.sopt.dosopttemplate.util.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setSignupZeroErrorTextVisible")
    fun setSignupZeroErrorTextVisible(view: TextView, length: Int) {
        if (length == 0) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

}
