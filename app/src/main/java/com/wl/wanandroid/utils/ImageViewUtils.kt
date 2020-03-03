package com.wl.wanandroid.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wl.wanandroid.R
import java.util.concurrent.ExecutionException

object ImageViewUtils {

    private val TAG = "ImageViewUtils"
    fun showImage(context: Context, imageView: ImageView, imageUrl: String?, placeHolder: Int, error: Int) {
        LogUtils.d(TAG, "showImage: $imageUrl")
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeHolder)
        requestOptions.error(error)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(context).load(imageUrl).apply(requestOptions).into(imageView)

    }


    fun showImageNoCaches(context: Context, imageView: ImageView, imageUrl: String?, placeHolder: Int, error: Int) {
        LogUtils.d(TAG, "showImage: $imageUrl")
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeHolder)
        requestOptions.error(error)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)

        Glide.with(context).load(imageUrl).apply(requestOptions).into(imageView)

    }

    fun showLocalImage(context: Context, imageView: ImageView,imgId:Int) {

        val requestOptions = RequestOptions()
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(context).load(imgId).apply(requestOptions).into(imageView)

    }



    fun getImgBitmap(context: Context, imgUrl: String): Bitmap? {
        try {
            return Glide.with(context).asBitmap().load(imgUrl).submit().get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
            return null
        } catch (e: InterruptedException) {
            e.printStackTrace()
            return null
        }

    }




    fun showColorIcon(imageView:ImageView){
        imageView.setColorFilter(StringUtils.getColor(R.color.theme_red));

    }

    fun showColorIcon(imageView:ImageView, colorId:Int){
        imageView.setColorFilter(StringUtils.getColor(colorId));

    }
}
