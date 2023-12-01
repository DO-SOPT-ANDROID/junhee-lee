package org.sopt.dosopttemplate.util.binding

import android.content.res.ColorStateList
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import org.sopt.dosopttemplate.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setUnderlineColor")
    fun setUnderlineColor(view: View, isValid: Boolean) {
        val colorResId = if (isValid) R.color.Pink_500 else R.color.Gray_600
        if (view is EditText) {
            view.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(view.context, colorResId))
        }
    }

}
