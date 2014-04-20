package com.pyro.mobilepolice.mission;

import android.content.Context;

import com.pyro.mobilepolice.data.MissionRequest;

public interface Mission {

	void execute(Context context, MissionRequest mRequest);
}
