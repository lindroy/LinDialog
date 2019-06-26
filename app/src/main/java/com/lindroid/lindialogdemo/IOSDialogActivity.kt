package com.lindroid.lindialogdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroid.iosdialog.IAlertDialog
import com.lindroid.iosdialog.IAlertListDialog
import com.lindroid.iosdialog.IBottomListDialog
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
        val customView = View.inflate(this, R.layout.custom_view, null)
        btnIAlert.setOnClickListener {
            IAlertDialog
                    .build(supportFragmentManager) //创建和配置对话框的入口
                    .setTitle("提示")
                    .setMessage("确定要退出登录吗？")
                    .setPosButtonText(R.string.ios_dialog_positive_text)
                    .setNegButtonText(R.string.ios_dialog_negative_text)
                    .setPosClickListener {
                        shortToast("确定")
                    }
                    .setNegClickListener {
                        shortToast("取消")
                    }
                    .show() //显示对话框
        }
        btnIAlertList.setOnClickListener {
            IAlertListDialog.build(supportFragmentManager)
                    .setTitle("选项对话框")
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
        btnIAlertListMore.setOnClickListener {
            IAlertListDialog.build(supportFragmentManager)
                    .setTitle("选项对话框")
                    .setMessage("这是一个具有大量选项的列表对话框")
                    .addItem("红色按钮", Color.RED)
                    .addItem("长文字选项长文字选项长文字选项长文字选项长文字选项长文字选项长文字选项")
                    .addItems((1..30).map { "按钮$it" })
                    .setCancelOutside(false)
//                    .setCancelText(R.string.ios_dialog_negative_text)
//                    .setCancelTextColorId(R.color.ios_dialog_text_color_blue)
//                    .setCancelTextSize(16F)
                    .setCancelButton(getString(R.string.ios_dialog_negative_text), Color.BLUE, 16F) {
                        shortToast("关闭对话框")
                    }
                    .setOnItemClickListener { position, text, itemView, dialog ->
                        shortToast("你选择了${itemView.text}")
                    }
                    .show()
        }
        btnIBottom.setOnClickListener {
            IBottomListDialog.build(supportFragmentManager)
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
        btnIBottomMore.setOnClickListener {
            IBottomListDialog.build(supportFragmentManager)
                    .setTitle("底部对话框")
                    .setMessage("这是一个具有大量选项的底部列表对话框")
                    .addItem("长文字选项长文字选项长文字选项长文字选项长文字选项长文字选项长文字选项")
                    .addItems((1..30).map { "按钮$it" })
                    .setCancelButton(textColor = Color.BLUE) {
                        shortToast("关闭对话框")
                    }
                    .setOnItemClickListener { position, text, itemView, dialog ->
                        shortToast("你选择了${itemView.text}")
                    }
                    .show()
        }
        btnIAlertCus.setOnClickListener {
            IAlertDialog
                    .build(supportFragmentManager) //创建和配置对话框的入口
//                    .setTitle("提示")
//                    .setMessage("确定要退出登录吗？")
                    .setCustomView(customView)
                    .setPosButtonText(R.string.ios_dialog_positive_text)
                    .setNegButtonText(R.string.ios_dialog_negative_text)
                    .setPosClickListener {
                        shortToast("确定")
                    }
                    .setNegClickListener {
                        shortToast("取消")
                    }
                    .setViewHandler { holder, dialog ->
                        holder.setOnClickListener(R.id.imageView, View.OnClickListener {
                            shortToast("点击图标")
                        })
                    }
                    .show() //显示对话框
        }

        btnIAlertListCus.setOnClickListener {
            IAlertListDialog.build(supportFragmentManager)
                    .setTitle("选项对话框")
                    .setMessage("请选择你喜欢的图书")
                    .setCustomView(R.layout.custom_view)
                    .addItem("红楼梦")
                    .addItem("西游记")
                    .addItem("水浒传")
                    .addItem("三国演义")
                    .setOnItemClickListener { position, text, itemView, dialog ->
                        shortToast("你选择了${itemView.text}")
                    }
                    .show()
        }

        btnIBottomCus.setOnClickListener {
            IBottomListDialog.build(supportFragmentManager)
                    .setCustomView(customView)
                    .addItem("红楼梦")
                    .addItem("西游记")
                    .addItem("水浒传")
                    .addItem("三国演义")
                    .setOnItemClickListener { position, text, itemView, dialog ->
                        shortToast("你选择了${itemView.text}")
                    }
                    .show()
        }

    }
}
