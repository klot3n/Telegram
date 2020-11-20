package com.klot3n.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.klot3n.myapplication.activities.RegisterActivity
//import android.widget.Toolbar
import com.klot3n.myapplication.databinding.ActivityMainBinding
import com.klot3n.myapplication.ui.fragments.ChatsFragment
import com.klot3n.myapplication.ui.objects.AppDrawer
import com.klot3n.myapplication.utilities.AUTH
import com.klot3n.myapplication.utilities.replaceActivity
import com.klot3n.myapplication.utilities.replaceFragment

private lateinit var mBinding: ActivityMainBinding
private lateinit var mAppDrawer: AppDrawer
private lateinit var mToolbar: Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (AUTH.currentUser!=null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment())
        } else {
            replaceActivity(RegisterActivity())

        }
    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar as Toolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        AUTH= FirebaseAuth.getInstance()

    }
}