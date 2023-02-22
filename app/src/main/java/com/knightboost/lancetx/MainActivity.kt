package com.knightboost.lancetx

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImplA().testMethod()

        init_method_insert_test.setOnClickListener {
            ConstructorTest(" original call^");
        }
        btn_create_bitmap.setOnClickListener {
            val createBitmap = Bitmap.createBitmap(300, 600, Bitmap.Config.RGB_565)
            Bitmap.createBitmap(310, 412, Bitmap.Config.RGB_565)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "onResume")
    }
}