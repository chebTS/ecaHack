package com.ekreative.battleosandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity {
	private EditText txtNick;
	private Button btnDone;
	
	private SharedPreferences mSharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		mSharedPreferences = getSharedPreferences("userdata", 0);
		txtNick = (EditText)findViewById(R.id.txtNick);
		txtNick.setText(mSharedPreferences.getString("nick", "Player1"));
		btnDone = (Button)findViewById(R.id.btnDone);
		btnDone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mSharedPreferences.edit().putString("nick", txtNick.getText().toString()).commit();
	}
	
	

}
