package com.ekreative.battleosandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.ekreative.battleosandroid.helper.Helper;

public class FirstActivity extends Activity implements OnClickListener {
	private Button btnFight, btnSettigns, btnExit;
	private final int REQ = 100;
	public static final int RES_WIN = 200;
	public static final int RES_LOSE = 201;
	public static final int RES_TIDE = 202;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);	
		btnFight = (Button)findViewById(R.id.btnFight);
		btnSettigns = (Button)findViewById(R.id.btnSettings);
		btnExit = (Button)findViewById(R.id.btnExit);

		btnFight.setOnClickListener(this);
		btnSettigns.setOnClickListener(this);
		btnExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnFight:
			startFight();
			break;
		case R.id.btnSettings:
			startActivity(new Intent(this, SettingsActivity.class));			
			break;
		case R.id.btnExit:
			finish();			
			break;
		default:
			break;
		}
	}
	
	private void startFight(){
		if (Helper.isConnectingToInternet(this)){
			//startActivity(new Intent(this, BattleActivity.class));
			startActivityForResult(new Intent(this, BattleActivity.class), REQ);
		}else{
			Toast.makeText(this, "To start fight You must connect to internet", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data ==null){
			//TODO 
		}else{
			int res = data.getIntExtra("result", RES_TIDE);
			switch (res) {
			case RES_WIN:
				Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();
				break;
			case RES_LOSE:
				Toast.makeText(this, "You lose!", Toast.LENGTH_LONG).show();
				break;
			case RES_TIDE:
				Toast.makeText(this, "Score is tied!", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
	}
}
//if (data == null) {return;}
//String name = data.getStringExtra("name");
//tvName.setText("Your name is " + name);
