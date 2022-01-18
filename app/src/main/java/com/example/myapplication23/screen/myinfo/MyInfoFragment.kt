package com.example.myapplication23.screen.myinfo

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.contentValuesOf
import androidx.core.view.isVisible
import com.example.myapplication23.databinding.FragmentMyInfoBinding
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.home.homelist.HomeCategory
import com.example.myapplication23.screen.home.homelist.HomeListFragment
import com.example.myapplication23.screen.like.LikeFragment
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import com.example.myapplication23.widget.adapter.HomeListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.zxing.qrcode.encoder.QRCode
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class MyInfoFragment : BaseFragment<MyInfoViewModel, FragmentMyInfoBinding>() {
    override val viewModel by viewModel<MyInfoViewModel>()

    val requestCode = 101;
    val requestCode_qr = -1;
    private val check = true;

    override fun getViewBinding() =
        FragmentMyInfoBinding.inflate(layoutInflater)

    override fun observeData() = with(binding) {
             initViewPager()
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LocationState.Success -> {
                //    initViewPager()
                }
            }
        }
    }

    companion object {
        const val TAG = "MyInfoFragment"

        fun newInstance() : MyInfoFragment {
            return MyInfoFragment().apply{

            }
        }
    }
    private fun initViewPager() = with(binding) {
        binding.profileImageBtn.setOnClickListener { loadImage()}
        binding.darkSwitch.setOnClickListener { darkMode() }

        }

    private fun loadImage(){

        var intent_iamge = Intent()
        intent_iamge.type = "image/*"
        intent_iamge.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent_iamge,"Load Picture"),requestCode)
    }

    private fun loadQR(){
        var intent_qr = Intent()
        startActivityForResult(Intent.createChooser(intent_qr,"Load QR"),requestCode_qr)
    }

   public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

       if(requestCode == requestCode){
           if(resultCode == RESULT_OK){
           val dataUri : Uri? = data?.data
           try {
                val bitmap : Bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver,dataUri)
           }  catch (e: Exception) {
               Toast.makeText(context,"$e",Toast.LENGTH_SHORT).show()
           }
           }
           else{

           }
       }


    }

    private fun darkMode(){
    if(check  == binding.darkSwitch.isChecked){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
        else{
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    }



    override fun initViews() {
        super.initViews()
    }




}