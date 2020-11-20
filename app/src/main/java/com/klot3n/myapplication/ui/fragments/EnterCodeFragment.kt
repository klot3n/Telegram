package com.klot3n.myapplication.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.klot3n.myapplication.MainActivity
import com.klot3n.myapplication.R
import com.klot3n.myapplication.activities.RegisterActivity
import com.klot3n.myapplication.utilities.AUTH
import com.klot3n.myapplication.utilities.AppTextWatcher
import com.klot3n.myapplication.utilities.replaceActivity
import com.klot3n.myapplication.utilities.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title=phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }

        })
    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast("Добро пожаловать!")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(task.exception?.message.toString())

        }
    }

}