package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.DatepickerDialogBinding
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.util.extension.setOnSingleClickListener
import java.util.Calendar

class HomeDatePickerDialogFragment :
    BindingDialogFragment<DatepickerDialogBinding>(
        R.layout.datepicker_dialog,
        isWrapContent = true,
    ) {
    private var listener: DatePickerDialogListener? = null

    fun setDatePickerDialogListener(listener: DatePickerDialogListener) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMaxDate()

        binding.btnLeft.setOnSingleClickListener {
            dismiss()
        }
        binding.btnRight.setOnSingleClickListener {
            val year = binding.datePicker.year
            val month = binding.datePicker.month + 1
            val day = binding.datePicker.dayOfMonth
            val selectedDate = String.format(getString(R.string.dialog_date_format), year, month, day)
            listener?.onDateSelected(selectedDate)
            dismiss()
        }
    }

    private fun setMaxDate() {
        val calendar = Calendar.getInstance()
        binding.datePicker.maxDate = calendar.timeInMillis
    }
}
