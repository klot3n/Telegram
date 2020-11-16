package com.klot3n.myapplication.utilities

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.klot3n.myapplication.R
import com.klot3n.myapplication.activities.RegisterActivity
import com.klot3n.myapplication.ui.fragments.ChatsFragment

fun Fragment.showToast (message:String){
    Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity:AppCompatActivity){
    val intent= Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.replaceFragment(fragment:Fragment){
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.dataContainer, fragment)
        .commit()
}

fun Fragment.replaceFragment(fragment:Fragment){
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.dataContainer, fragment)
        ?.commit()
}