package com.klot3n.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klot3n.myapplication.databinding.ActivityRegisterBinding
import androidx.appcompat.widget.Toolbar
import com.klot3n.myapplication.R
import com.klot3n.myapplication.ui.fragments.EnterPhoneNumberFragment
import com.klot3n.myapplication.utilities.replaceFragment


class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mTootbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mTootbar = mBinding.registerToolbar
        setSupportActionBar(mTootbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment())
    }
}