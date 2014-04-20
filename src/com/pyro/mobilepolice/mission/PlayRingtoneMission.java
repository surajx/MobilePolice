package com.pyro.mobilepolice.mission;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.pyro.mobilepolice.activity.MyDialogueActivity;
import com.pyro.mobilepolice.data.MissionRequest;

public class PlayRingtoneMission implements Mission {

	@SuppressLint("InlinedApi")
	@Override
	public void execute(Context context, MissionRequest mRequest) {
		Intent i = new Intent(context, MyDialogueActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		context.getApplicationContext().startActivity(i);
	}
}
