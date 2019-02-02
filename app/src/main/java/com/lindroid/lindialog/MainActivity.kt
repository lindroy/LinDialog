package com.lindroid.lindialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lindroid.lindialog_lib.HintDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnHint.setOnClickListener {
            HintDialog.build(supportFragmentManager)
                    .show()
        }
    }
}
