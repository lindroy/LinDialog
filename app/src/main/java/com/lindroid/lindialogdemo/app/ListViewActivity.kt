package com.lindroid.lindialogdemo.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.lindroid.lindialogdemo.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val data = (1..30).map { "这是数据$it" }
        listView.apply {
            dividerHeight = 1
            adapter = ArrayAdapter<String>(this@ListViewActivity,android.R.layout.simple_list_item_1,android.R.id.text1, data)
        }

    }
}
