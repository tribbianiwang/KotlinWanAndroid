package com.wl.wanandroid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.wl.wanandroid.R

import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.wl.wanandroid.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //隐藏状态栏
        //定义全屏参数
        val flag = WindowManager.LayoutParams.FLAG_FULLSCREEN
        //获得当前窗体对象
        val window = this@SplashActivity.window
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag)


        setContentView(R.layout.activity_splash)

        startAnim()

    }

    private fun startAnim() {
        val aa = AlphaAnimation(0.4f, 1.0f)
        aa.setDuration(1000);
        aa.setFillAfter(true);
        iv_splash.startAnimation(aa)
        aa.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {


            }

            override fun onAnimationEnd(animation: Animation?) {


            }

            override fun onAnimationStart(animation: Animation?) {

                Thread{
                    kotlin.run {

                        Thread.sleep(2000)
                        var intent = Intent(this@SplashActivity,MainActivity::class.java)
                        startActivity(intent)
                        finish();

                    }
                }.start()


            }

        })
    }


}
