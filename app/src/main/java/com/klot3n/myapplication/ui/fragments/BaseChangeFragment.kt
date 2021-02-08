package com.klot3n.myapplication.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.klot3n.myapplication.MainActivity
import com.klot3n.myapplication.R
import com.klot3n.myapplication.utilities.APP_ACTIVITY

open class BaseChangeFragment(layout:Int) :Fragment(layout){

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        APP_ACTIVITY.hideKeyBoard()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true

    }

    open fun change() {

    }

}