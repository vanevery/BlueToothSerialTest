package com.example.bluetoothserialtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import cc.arduino.btserial.BtSerial;

public class MainActivity extends Activity implements OnClickListener {

	public static final String LOGTAG = "BlueToothSerialTest";
	public static final String BLUETOOTH_MAC_ADDRESS = "00:06:66:42:1F:DF";
	
	BtSerial btserial;
	
	TextView receivedTextTextView;
	Button sendSomethingButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		receivedTextTextView = (TextView) this.findViewById(R.id.receivedTextTextView);
		sendSomethingButton = (Button) this.findViewById(R.id.sendSomethingButton);
		sendSomethingButton.setOnClickListener(this);
		
		btserial = new BtSerial(this);
		String[] list = btserial.list();
		for (int i = 0; i < list.length; i++) {
			Log.v("BlueToothTest",list[i]);
			/* 
			 * 04-01 15:43:43.023: V/BlueToothTest(2722): 00:06:66:42:1F:DF
			 * 04-01 15:43:43.023: V/BlueToothTest(2722): 30:17:C8:1C:CA:53
			 */
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		btserial.connect("00:06:66:42:1F:DF");
		Log.v(LOGTAG,"Connected");
		
	}
	
	@Override
	protected void onPause()
	{
		btserial.disconnect();
	}

	public void btSerialEvent(BtSerial btserialObject) {
		Log.v(LOGTAG,"Data: " + btserialObject.readString());
	}

	@Override
	public void onClick(View clickedView) {
		btserial.write("button pushed");	
		Log.v(LOGTAG,"Sent: button pushed");
	}
	
	

}
