package com.lindroid.lindialogdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroid.iosdialog.IAlertDialog
import com.lindroid.iosdialog.IAlertListDialog
import com.lindroid.iosdialog.IBottomSheetDialog
import kotlinx.android.synthetic.main.activity_iosdialog.*

/**
 * @author Lin
 * @date 2019/5/20
 * @function iOS风格的对话框
 * @Description
 */
class IOSDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iosdialog)
        btnIAlert.setOnClickListener {
            IAlertDialog.build(supportFragmentManager)
                    .setTitle("提示")
                    .setMessage("这是一段很长的说明文字这是一段很长的说明文字这是一段很长的说明文字这是一段很长的说明文字这是一段很长的说明文字")
                    .setPosClickListener {
                        shortToast("确定")
                    }
                    .setNegClickListener {
                        shortToast("取消")
                    }
                    .show()
        }
        btnIAlertList.setOnClickListener {
            IAlertListDialog.build(supportFragmentManager)
                    .setTitle("多选对话框")
                    .setMessage("请选择你喜欢的颜色")
                    .addItem("红色", Color.RED)
                    .addItem("黄色", Color.YELLOW)
                    .addItem("蓝色", Color.BLUE)
                    .addItem("绿色", Color.GREEN)
                    .setOnItemClickListener { position, text, itemView, dialog ->
                        shortToast("你选择了${itemView.text}")
                    }
                    .show()
        }
        btnIBottom.setOnClickListener {
            IBottomSheetDialog.build(supportFragmentManager)
                    .setTitle("底部对话框")
                    .setMessage("请选择你喜欢的颜色")
                    .addItem("红色", Color.RED)
                    .addItem("黄色", Color.YELLOW)
                    .addItem("蓝色", Color.BLUE)
                    .addItem("绿色", Color.GREEN)
                    .setOnItemClickListener { position, text, itemView, dialog ->
                        shortToast("你选择了${itemView.text}")
                    }
                    .show()
        }

    }
}
