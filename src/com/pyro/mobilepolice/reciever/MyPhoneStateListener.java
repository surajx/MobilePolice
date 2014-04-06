package com.pyro.mobilepolice.reciever;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyPhoneStateListener extends PhoneStateListener {
	Context context;

	public MyPhoneStateListener(Context context) {
		this.context = context;
	}

	public void onCallStateChanged(int state, String incomingNumber) {
    
        Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

        if (state == 1) {
        	
        	if(incomingNumber.equals("04713041000")|| incomingNumber.equals("+914713041000") 
        			||incomingNumber.equals("04713041500")|| incomingNumber.equals("+914713041500")
        			|| incomingNumber.equals("04713041247")|| incomingNumber.equals("+914713041247"))
        	{
        		
            String msg = "Call from KIMS . Incomming Number : "+incomingNumber;
            String[] numbers= {"09633716611","09633261025"};
            SMSSender.sendSMS(numbers, msg);
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, msg, duration);
            toast.show();
        	}
        	

        }
    }
	public static String getMyPhoneNumber(Context context)
	{
		TelephonyManager tMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		
		return tMgr.getLine1Number();
	}
}
