package com.pyrocrypt.mobilepolice.mission;

import android.content.Context;

import com.pyrocrypt.mobilepolice.data.MissionRequest;

public interface Mission {

	void execute(Context context, MissionRequest mRequest);
}
