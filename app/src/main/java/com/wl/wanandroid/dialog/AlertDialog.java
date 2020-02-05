package com.wl.wanandroid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.wl.wanandroid.R;


public class AlertDialog extends Dialog {


	/**
	 * 设置成自定义view，不可点击消失
	 * 
	 * @param context
	 * @param theme
	 */

	public AlertDialog(final Context context, int theme) {
		super(context, theme);
		this.setContentView(R.layout.alert_dialog);
		this.setCanceledOnTouchOutside(false);
		this.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK
						&& event.getRepeatCount() == 0) {
					dismiss();


					return true;
				} else {
					return false; // 默认返回 false
				}
			}
		});
	}

}
