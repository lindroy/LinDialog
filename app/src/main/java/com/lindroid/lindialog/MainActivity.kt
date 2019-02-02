package com.lindroid.lindialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lindroid.lindialog_lib.HintDialog
import com.lindroid.utils.shortToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnHint.setOnClickListener(this)
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnHint -> showHintDialog()
        }
    }

    private fun showHintDialog() {
        HintDialog.build(supportFragmentManager)
//                .setThemeStyle(R.style.HintDialogStyle)
                .setTitle(R.string.app_name)
                .setMessage("这是一个提示对话框")
                .setShowNeuButton(true)
                .setNeutralText("不确定")
                .setCanCancel(true)
                .setCanDismiss(false)
                .setPosClickListener {
                    shortToast("确定")
                    it.dismiss()
                }
                .setNegClickListener {
                    shortToast("取消")
                    it.dismiss()
                }
                .setNeuClickListener {
                    shortToast("不确定")
                    it.dismiss()
                }
                .show()
    }
}
