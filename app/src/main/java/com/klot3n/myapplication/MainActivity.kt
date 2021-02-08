package com.klot3n.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.klot3n.myapplication.activities.RegisterActivity
//import android.widget.Toolbar
import com.klot3n.myapplication.databinding.ActivityMainBinding
import com.klot3n.myapplication.models.User
import com.klot3n.myapplication.ui.fragments.ChatsFragment
import com.klot3n.myapplication.ui.objects.AppDrawer
import com.klot3n.myapplication.utilities.*
import com.theartofdev.edmodo.cropper.CropImage


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    internal lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY=this
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(
                ChatsFragment(),
                false
            )  //addStack (funs.kt)"Залупливает" почему-то ввод телефона
        } else {
            replaceActivity(RegisterActivity())

        }
    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar as androidx.appcompat.widget.Toolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()
        initUser()


    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
                USER= it.getValue(User::class.java)!!?:User()
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(CURRENT_UID)
            path.putFile(uri).addOnCompleteListener {task1 ->
                if(task1.isSuccessful){
                   path.downloadUrl.addOnCompleteListener {tassk2 ->
                       if(tassk2.isSuccessful){
                           val photoUrl = tassk2.result.toString()
                           REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
                               .child(CHILD_PHOTO_URL).setValue(photoUrl)
                               .addOnCompleteListener {
                                   if(it.isSuccessful){
                                       showToast(getString(R.string.toast_data_update))
                                       USER.photoUrl = photoUrl
                                   }
                               }
                       }
                   }
                }
            }
        }
    }

    fun hideKeyBoard(){
        val imm:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken,0)
    }
}