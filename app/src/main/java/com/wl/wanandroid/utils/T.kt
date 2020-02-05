package com.wl.wanandroid.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

class T {
      companion object{


     var toast: Toast? = null
    var isShow = true
     var lastClickTime: Long = 0
    fun showShort(context: Context, message: CharSequence?) {
        val time = System.currentTimeMillis()
        if (time - lastClickTime < 2 * AppConstants.TOAST_DOUBLE_TIME_LIMIT) {
            return
        }
        lastClickTime = time

        if (isShow&&(!TextUtils.isEmpty(message))){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast?.show()
        }
        //		toast.setGravity(Gravity.BOTTOM, 0, 0);

    }



          private fun logError(info: String, errorCode: Int) {
              print(LINE)//start
              kotlin.io.print("                                   错误信息                                     ")
              print(LINE)//title
              kotlin.io.print(info)
              kotlin.io.print("错误码: $errorCode")
              kotlin.io.print("                                                                               ")
              kotlin.io.print("如果需要更多信息，请根据错误码到以下地址进行查询")
              kotlin.io.print("  http://lbs.amap.com/api/android-sdk/guide/map-tools/error-code/")
              kotlin.io.print("如若仍无法解决问题，请将全部log信息提交到工单系统，多谢合作")
              print(LINE)//end
          }

             val TAG ="Toast"
                  internal val LINE_CHAR = "="
          internal val BOARD_CHAR = "|"
          internal val LENGTH = 80
          internal lateinit var LINE: String

           fun printLog(s: String) {
              if (s.length < LENGTH - 2) {
                  val sb = StringBuilder()
                  sb.append(BOARD_CHAR).append(s)
                  for (i in 0 until LENGTH - 2 - s.length) {
                      sb.append(" ")
                  }
                  sb.append(BOARD_CHAR)
                  print(sb.toString())
              } else {
                  val line = s.substring(0, LENGTH - 2)
                  print(BOARD_CHAR + line + BOARD_CHAR)
                  printLog(s.substring(LENGTH - 2))
              }
          }

          private fun print(s: String) {

              LogUtils.d(TAG, s)
          }

      }
}