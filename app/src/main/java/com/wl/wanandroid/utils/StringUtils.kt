package com.wl.wanandroid.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Html
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.wl.wanandroid.MyApplication


import java.util.regex.PatternSyntaxException
import kotlin.random.Random


object StringUtils {




    fun getRColor(context: Context, colorId: Int): Int {
        return context.resources.getColor(colorId)
    }

    fun getColor(colorId: Int): Int {
        return MyApplication.instance?.resources?.getColor(colorId)?:0
    }

    fun getString(stringId: Int): String {
        return MyApplication.instance?.resources?.getString(stringId)?:""
    }



    fun setEdittextError(editText: EditText, msg: String) {
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
        editText.error = msg
    }





    fun startCallPhone(context: Context, phoneNum: String) {
        val uri = Uri.parse("tel:$phoneNum")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        context.startActivity(intent)
    }

    fun divisionString(content: String): String {
        val stringBuffer = StringBuffer()
        val contentChars = content.toCharArray()
        for (i in contentChars.indices) {
            stringBuffer.append(contentChars[i])
            if ((i + 1) % 4 == 0) {
                stringBuffer.append(" ")
            }
        }

        return stringBuffer.toString()

    }

    /**过滤HTML里去除img、p、span外的所有标签
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    fun stringFilter(str: String): String {
        var str = str

        str = str.replace("<p>".toRegex(), "<p style=margin:0px 10px;>")

        return str.trim { it <= ' ' } // 返回文本字符串
    }


    /**
     * 隐藏键盘
     */
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val current = activity.window.currentFocus
        if (current != null) {
            imm.hideSoftInputFromWindow(current.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }


    fun showTextViewHtml(tv: TextView, content:String){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tv.text = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
        } else {
            tv.text = Html.fromHtml(content);
        }

    }

    /**
     * 去掉字符串里面的html代码。<br></br>
     * 要求数据要规范，比如大于小于号要配套,否则会被集体误杀。
     *
     * @param content 内容
     * @return 去掉后的内容
     */

    fun stripHtml(content: String): String {
        var content = content
        // <p>段落替换为换行
        content = content.replace("<p .*?>".toRegex(), "\r\n")
        // <br><br/>替换为换行
        content = content.replace("<br\\s*/?>".toRegex(), "\r\n")
        // 去掉其它的<>之间的东西
        content = content.replace("\\<.*?>".toRegex(), "")
        // 还原HTML
        // content = HTMLDecoder.decode(content);
        //&ldquo;&quot;&nbsp;
        content = content.replace("&.dquo;".toRegex(), "\"")
        content = content.replace("&nbsp;".toRegex(), " ")
        return content
    }

    fun parseDouble(stringDouble:String?):Double{
        var doubleResult = 0.toDouble()
        try {
          doubleResult =  stringDouble?.toDouble()?:0.toDouble()
        }catch (exception:Exception){
        }
        return doubleResult
    }

    fun parseFloat(stringFloat:String?):Float{
        var floatResult = 0.toFloat()
        try {
            floatResult =  stringFloat?.toFloat()?:0.toFloat()
        }catch (exception:Exception){
        }
        return floatResult
    }

    fun parseLong(stringLong:String?):Long{
        var longResult = 0.toLong()
        try {
            longResult =  stringLong?.toLong()?:0.toLong()
        }catch (exception:Exception){
        }
        return longResult
    }



    fun parseInt(stringInt:String?):Int{
        var intResult = 0
        try {
            intResult =  stringInt?.toInt()?:0
        }catch (exception:Exception){
        }
        return intResult
    }

    fun getRandomColor():Int{
        val random = Random
        var r = 0
        var g = 0
        var b = 0
        for (i in 0..1) {
            //       result=result*10+random.nextInt(10);
            var temp = random.nextInt(16)
            r = r * 16 + temp
            temp = random.nextInt(16)
            g = g * 16 + temp
            temp = random.nextInt(16)
            b = b * 16 + temp
        }
        return Color.rgb(r, g, b)
    }


}
