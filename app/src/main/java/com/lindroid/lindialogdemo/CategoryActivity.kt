package com.lindroid.lindialogdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lindroid.androidutilskt.extension.launchActivity
import com.lindroid.lindialogdemo.app.ListViewActivity
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        btnMain.setOnClickListener { launchActivity<MainActivity>() }
        btnList.setOnClickListener { launchActivity<ListViewActivity>() }
    }
}
