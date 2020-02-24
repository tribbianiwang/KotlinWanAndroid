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
import com.wl.wanandroid.utils.ImmerBarUtils
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_splash)
        ImmerBarUtils.initImmerBar(this,R.color.theme_red)
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
