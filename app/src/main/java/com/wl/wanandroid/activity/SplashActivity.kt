package com.wl.wanandroid.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import com.wl.wanandroid.MainActivity
import com.wl.wanandroid.R
import com.wl.wanandroid.utils.ImmerBarUtils
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_splash)
        ImmerBarUtils.initImmerBar(this,R.color.DarkGray)
//        ImageViewUtils.showImageNoCaches(this,iv_splash,"https://api.ooopn.com/image/bing/api.php?v=2&type=jump",R.drawable.icon_jetpack,R.drawable.icon_jetpack)

        startAnim()

        GlobalScope.launch(Dispatchers.Main) {
            delay(2000L)
            var intent = Intent(this@SplashActivity,MainActivity::class.java)
                        startActivity(intent)
                        finish();
            println("Hello World")
        }

    }

    private fun startAnim() {
        val anim1 = ObjectAnimator.ofFloat(iv_splash, "scaleX", 0.4f, 1.0f)
        anim1.repeatCount = -1
        val anim2 = ObjectAnimator.ofFloat(iv_splash, "scaleY", 0.4f, 1.0f)
        anim2.repeatCount = -1
        val set = AnimatorSet()
        set.play(anim1).with(anim2)
        set.duration = 1000
        set.start()

//
//        iv_splash.startAnimation(scaleAnim)
//        scaleAnim.setAnimationListener(object:Animation.AnimationListener{
//            override fun onAnimationRepeat(animation: Animation?) {
//
//
//            }
//
//            override fun onAnimationEnd(animation: Animation?) {
//
//
//            }
//
//            override fun onAnimationStart(animation: Animation?) {
//
//                Thread{
//                    kotlin.run {
//
//                        Thread.sleep(2000)
//                        var intent = Intent(this@SplashActivity,MainActivity::class.java)
//                        startActivity(intent)
//                        finish();
//
//                    }
//                }.start()
//
//
//            }
//
//        })
    }


}
