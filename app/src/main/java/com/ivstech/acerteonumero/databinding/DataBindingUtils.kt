package com.ivstech.acerteonumero.databinding

import android.view.View
import androidx.databinding.BindingAdapter

object DataBindingUtils {
    @JvmStatic
    @BindingAdapter("android:layout_width")
    fun setLayoutWidth(view: View, width: Float) {
        view.layoutParams.width = width.toInt()
    }

    @JvmStatic
    @BindingAdapter("android:layout_height")
    fun setLayoutHeight(view: View, height: Float) {
        view.layoutParams.height = height.toInt()
    }
}