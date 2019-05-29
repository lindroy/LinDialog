package com.lindroid.lindialogdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.lindroid.androidutilskt.extension.dp2px
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroid.lindialog.BottomDialog
import com.lindroid.lindialog.BottomListDialog
import com.lindroid.lindialog.CustomDialog
import com.lindroid.lindialog.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "LinDialog"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnHint.setOnClickListener(this)
        btnIOS.setOnClickListener(this)
        btnEdit.setOnClickListener(this)
        btnBottom.setOnClickListener(this)
        btnPay.setOnClickListener(this)
        btnBottomList.setOnClickListener(this)
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            //提示对话框
            R.id.btnHint -> showMaterialDialog()
            R.id.btnIOS -> showCustomDialog()
            R.id.btnBottom -> showBottomDialog()
            R.id.btnPay -> showPayDialog()
            R.id.btnEdit -> showEditDialog()
            R.id.btnBottomList -> showBottomListDialog()
        }
    }

    private fun showMaterialDialog() {
        MaterialDialog.build(supportFragmentManager)
//                .setThemeStyle(R.style.MaterialDialogStyle)
            .setTitle(R.string.app_name)
            .setMessage("这是一个提示对话框")
            .setShowNeuButton(true)
            .setNeutralText("不确定")
            .setCancelOutside(true)
            .setCanDismiss(false)
            .setPosClickListener {
                shortToast("确定")
                it.dismiss()
            }
            .setNegClickListener {
                this.shortToast("取消")
                it.dismiss()
            }
            .setNeuClickListener {
                this.shortToast("不确定")
                it.dismiss()
            }
            .show()
    }

    private fun showCustomDialog() {
        CustomDialog.build(supportFragmentManager)
            .setContentView(R.layout.dialog_ios)
            .setWidthScale(0.8F)
            .setAnimStyle(R.style.ScaleAnimationStyle)
            .setViewHandler { holder, dialog ->
                holder.setText(R.id.tvTitle, "提示")
                holder.setText(R.id.tvMessage, "这是一个仿ios风格的对话框")
                holder.setOnClickListener(R.id.tvCancel, View.OnClickListener {
                    shortToast(R.string.cancel)
                    dialog.dismiss()
                })
                holder.setOnClickListener(R.id.tvConfirm, View.OnClickListener {
                    shortToast(R.string.confirm)
                    dialog.dismiss()
                })
            }
            .show()
    }

    private fun showBottomDialog() {
        BottomDialog.build(supportFragmentManager)
            .setContentView(R.layout.dialog_choose_gender)
            .setViewHandler { holder, dialog ->
                holder.setOnClickListener(R.id.tvMale, View.OnClickListener {
                    shortToast("男")
                    dialog.dismiss()
                })
                holder.setOnClickListener(R.id.tvFemale, View.OnClickListener {
                    shortToast("女")
                    dialog.dismiss()
                })
            }
            .setDismissListener {
                Log.d(TAG, "BottomDialog关闭")
            }
            .show()
    }

    private fun showPayDialog() {
        PayDialog.build(supportFragmentManager)
            .setAliPayListener {
                shortToast("支付宝")
                it.dismiss()
            }
            .setWeChatListener {
                shortToast("微信")
                it.dismiss()
            }
            .show()
    }

    private fun showEditDialog() {
        EditDialog.build(supportFragmentManager)
            .setMinLength(5)
            .setMaxLength(15)
            .setTextChangeListener { content, length ->
                Log.d(TAG, "content:$content")
            }
            .setFinishListener { content, dialog ->
                shortToast("提交成功")
            }
            .setDismissListener {
                Log.d(TAG, "输入对话框关闭")
            }
            .show()
    }

    private fun showBottomListDialog() {
        BottomListDialog.build(supportFragmentManager)
            .setBackgroundResource(R.drawable.shape_bottom_dialog)
            .setDivider(R.drawable.shape_divider)
            .setDividerPadding(dp2px(20))
            .setItemHeight(dp2px(50))
            .setItemTextColorId(R.color.text_gray)
            .setItemTextSize(16F)
            .addItems(resources.getStringArray(R.array.Cities))
            .setItemTextCenter()
            .setOnItemClickListener { position, name, itemView, dialog ->
                shortToast("你点击了第${position}个：$name")
                dialog.dismiss()
            }
            .show()

    }


}
