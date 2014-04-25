package com.pyro.mobilepolice.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

public class MyDialogueActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Uri ringtoneURI = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		AudioManager mAudioManager = (AudioManager) this
				.getSystemService(Context.AUDIO_SERVICE);
		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		mAudioManager.setStreamVolume(AudioManager.STREAM_RING, 100,
				AudioManager.FLAG_PLAY_SOUND
						| AudioManager.FLAG_ALLOW_RINGER_MODES);
		final Ringtone r = RingtoneManager.getRingtone(this, ringtoneURI);
		r.play();
		//TODO Make Generic, reuse activity to create an all purpose Dialog Activity - @surajx
		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
		dlgAlert.setMessage("Playing Ringtone...");
		dlgAlert.setTitle("Mobile Police");
		dlgAlert.setPositiveButton("STOP",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						r.stop();
						finish();
					}
				});
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
	}

}
