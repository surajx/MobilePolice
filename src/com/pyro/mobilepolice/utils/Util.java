package com.pyro.mobilepolice.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Window;

public class Util {
	public static ProgressDialog showProgressDialogWithMessage(String message,
			Context context) {
		
		ProgressDialog myProgressDialog = new ProgressDialog(context);
		try 
		{
		myProgressDialog.setMessage(message);
		myProgressDialog.setCancelable(false);
		myProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		myProgressDialog.show();
		myProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_SEARCH
						&& event.getRepeatCount() == 0) {
					return true;
				}
				if (keyCode == KeyEvent.KEYCODE_MENU) {
					return true;
				}
				return false;
			}

		});
		}catch(Exception ex)
		{
		ex.printStackTrace();
		}
		return myProgressDialog;
	}
	public static void dismissProgressDialog(ProgressDialog myProgressDialog) {
		if (myProgressDialog != null) {
			try {
				myProgressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
