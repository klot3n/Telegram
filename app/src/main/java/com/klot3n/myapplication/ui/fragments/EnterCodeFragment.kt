package com.klot3n.myapplication.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.klot3n.myapplication.R
import com.klot3n.myapplication.utilities.AppTextWatcher
import com.klot3n.myapplication.utilities.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {
    override fun onStart() {
        super.onStart()
        register_input_code.addTextChangedListener(AppTextWatcher {
                val string = register_input_code.text.toString()
                if (string.length == 6) {
                    verifiCode()
                }

        })
    }

    fun verifiCode() {
        showToast("Ok")
    }

}