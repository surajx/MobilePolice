package com.pyro.mobilepolice.mission;

import android.content.Context;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.pyro.mobilepolice.data.MissionRequest;

public class PlayRingtoneMission implements Mission {

	@Override
	public void execute(Context context, MissionRequest mRequest) {
		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		AudioManager mAudioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		mAudioManager.setStreamVolume(AudioManager.STREAM_RING, 100,
				AudioManager.FLAG_PLAY_SOUND
						| AudioManager.FLAG_ALLOW_RINGER_MODES);
		Ringtone r = RingtoneManager.getRingtone(context, notification);
		r.play();
	}

}
