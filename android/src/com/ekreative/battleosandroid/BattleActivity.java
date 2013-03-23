package com.ekreative.battleosandroid;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bump.api.BumpAPIIntents;
import com.bump.api.IBumpAPI;
import com.ekreative.battleosandroid.adapters.FragmentAdapter;
import com.ekreative.battleosandroid.fragments.FragmentEnemy;
import com.ekreative.battleosandroid.fragments.FragmentEnemy.onChangeAttackEventListener;
import com.ekreative.battleosandroid.fragments.FragmentYou;
import com.ekreative.battleosandroid.fragments.FragmentYou.onChangeDefenceEventListener;

public class BattleActivity extends FragmentActivity  implements onChangeAttackEventListener, onChangeDefenceEventListener  {
	private final String tag ="!!!CHEB!!!";
	private IBumpAPI api;
	private ViewPager pager;
	private String udid, enemyUDID;
	private final String TAG_HEALTH = "health";
	private final String TAG_LEVEL = "level";
	private final String TAG_EXP = "exp";
	private final String TAG_NAME = "name";
	private final String jos = "os";
	private final String judid = "udid";
	private final String jname = "name";
	private final String jdef0 = "def0";
	private final String jdef1 = "def1";
	private final String jattack = "attack";
	private final String jhealth = "health";
	private final String jexp = "exp";
	private final String jlevel = "level";
	private String name;
	private int attack, def0, def1, health, level, exp;
	private int attackE, def0E, def1E,healthE, levelE, expE;
	private int bonus;
	private TextView txtInternetStatus;
	//private FragmentBaseCharacter fragMe, fragEnemy;
	private FragmentYou fragMe;
	private FragmentEnemy fragEnemy;
	private SharedPreferences mSharedPreferences;
	private JSONObject jRoot, jEnemy;
	private TelephonyManager tm;
	private FragmentAdapter fadapter;
	
//	public interface onChangeHealthEventListener {
//	    public void changeHealth(int attack);
//	  }
//	onChangeHealthEventListener changeHealthEventListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		txtInternetStatus = (TextView)findViewById(R.id.txtInternetStatus);
		pager = (ViewPager)findViewById(R.id.viewpager);
		fadapter = new FragmentAdapter(getSupportFragmentManager());		
		pager.setAdapter(fadapter);
		pager.setPageMargin(10);
		fragMe = (FragmentYou)fadapter.getItem(0);
		
		fragEnemy = (FragmentEnemy)fadapter.getItem(1);	
		initData();
		initBump();		
	}
	
	private void initData(){
		tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		udid = tm.getDeviceId()+":" + Integer.toString(this.hashCode());
		Log.i("UDID",udid);
		enemyUDID = null;
		mSharedPreferences = getSharedPreferences("userdata", 0);
		attack = 0;
		def0 = 0;
		def1 = 1;
		bonus = 0;
		health = mSharedPreferences.getInt(TAG_HEALTH, 1000);
		level = mSharedPreferences.getInt(TAG_LEVEL, 0);
		exp = mSharedPreferences.getInt(TAG_EXP, 0);
		if (exp > 1200000){
			level = 7;
		}else if (exp > 600000){
			level = 6;
		}else if (exp > 300000){
			level = 5;
		}else if (exp > 150000){
			level = 4;
		}else if (exp > 70000){
			level = 3;
		}else if (exp > 30000){
			level = 2;
		}else if (exp > 10000){
			level = 1;
		}

		name = mSharedPreferences.getString(TAG_NAME, "Player 1");
		updateJSON();
	}
	
	private JSONObject updateJSON(){
		try{
			jRoot = new JSONObject();
			jRoot.put(jos, "android");
			jRoot.put(judid, udid);
			jRoot.put(jname, name);
			jRoot.put(jdef0, def0);
			jRoot.put(jdef1, def1);
			jRoot.put(jattack, attack);
			jRoot.put(jhealth, health);
			jRoot.put(jexp, exp);
			jRoot.put(jlevel, level);
		}catch (JSONException e) {
			Toast.makeText(this, "JSON error", Toast.LENGTH_LONG).show();
		}
		return jRoot;
	}
	
	@Override
	public void changeDefence(int def0, int def1) {
		this.def0 = def0;
		this.def1 = def1;
		updateJSON();
	}

	@Override
	public void changeAttack(int attack) {
		this.attack = attack;
		updateJSON();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	private void initBump(){
		bindService(new Intent(IBumpAPI.class.getName()), connection, Context.BIND_AUTO_CREATE);    
		IntentFilter filter = new IntentFilter();
        filter.addAction(BumpAPIIntents.CHANNEL_CONFIRMED);
        filter.addAction(BumpAPIIntents.DATA_RECEIVED);
        filter.addAction(BumpAPIIntents.NOT_MATCHED);
        filter.addAction(BumpAPIIntents.MATCHED);
        filter.addAction(BumpAPIIntents.CONNECTED);
        registerReceiver(receiver, filter);
	}
	
	private void parseResult(String res){
		Log.i("Res", res);
		int dmg = 0;
		int dmgE = 0;
		try{
			jEnemy = new JSONObject(res);				
		}catch (JSONException e) {
			Toast.makeText(this, "Enemy JSON error", Toast.LENGTH_LONG).show();
		}
		expE = jEnemy.optInt(jexp);
		levelE = jEnemy.optInt(jlevel);
		healthE = jEnemy.optInt(jhealth);
		fragEnemy.setHealth(healthE);
		fragEnemy.setLevel(levelE);
		fragEnemy.setExp(expE);
		if (enemyUDID == null){
			enemyUDID = jEnemy.optString(judid);
			Toast.makeText(this, "Fight started", Toast.LENGTH_LONG).show();
		}else{
			if (enemyUDID.equals(jEnemy.optString(judid))){
				def0E = jEnemy.optInt(jdef0);
				def1E = jEnemy.optInt(jdef1);
				attackE = jEnemy.optInt(jattack);				
				if ((attack == def0E)||(attack == def1E)){//you missed
				//	Toast.makeText(this, "You missed!", Toast.LENGTH_LONG).show();
				}else{//you hit him
					dmgE = (int)Math.round((level+1)*7 + 7 * 0.02*health);		
					Log.i("dmgE", Integer.toString(dmgE) + "level " + Integer.toString(level));
					
					bonus = bonus + (int)Math.round(dmgE/3);	
				}
				if ((attackE == def0)||(attackE == def1)){//enemy missed
				//	Toast.makeText(this, "Enemy missed!", Toast.LENGTH_LONG).show();
					bonus = bonus + (int)Math.round(dmgE/9);
				}else{//enemy hit you
					dmg = (int)Math.round((levelE+1)*7 + 7 * 0.02*healthE);	
					Log.i("dmg", Integer.toString(dmg) + "levelE " + Integer.toString(levelE));
					
				}
				healthE = healthE - dmgE;
				health = health - dmg;
				fragMe.setHealth(health);
				fragEnemy.setHealth(healthE);
				if ((health <= 0) &&(healthE <= 0)){
					//Toast.makeText(this, "Score is tied!", Toast.LENGTH_LONG).show();
					Intent intent = new Intent();
				    intent.putExtra("result", FirstActivity.RES_TIDE);
				    setResult(RESULT_OK, intent);
				    finish();
				}else if (health <= 0){
					//Toast.makeText(this, "You lose!", Toast.LENGTH_LONG).show();
					exp = exp + bonus;
					mSharedPreferences.edit().putInt(TAG_EXP, exp).commit();
					Intent intent = new Intent();
				    intent.putExtra("result", FirstActivity.RES_LOSE);
				    setResult(RESULT_OK, intent);
				    finish();
				}else if (healthE <= 0 ){
					//Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();
					exp = exp + bonus + 100;
					mSharedPreferences.edit().putInt(TAG_EXP, exp).commit();
					Intent intent = new Intent();
				    intent.putExtra("result", FirstActivity.RES_WIN);
				    setResult(RESULT_OK, intent);
				    finish();
				}else{
				//	Toast.makeText(this, "Show must go on!", Toast.LENGTH_LONG).show();
					
				}
			}else{
				//Toast.makeText(this, "You win", Toast.LENGTH_LONG).show();
				exp = exp + bonus + 100;
				mSharedPreferences.edit().putInt(TAG_EXP, exp).commit();
				Intent intent = new Intent();
			    intent.putExtra("result", FirstActivity.RES_WIN);
			    setResult(RESULT_OK, intent);
			    finish();
			}			
		}
		updateJSON();
	}
	
	private final BroadcastReceiver receiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        final String action = intent.getAction();
	        try {
	        	Log.i(tag,"Recive something");
	        	if (action.equals(BumpAPIIntents.DATA_RECEIVED)) {
	        		parseResult(new String(intent.getByteArrayExtra("data")));  	  
	            } else if (action.equals(BumpAPIIntents.MATCHED)) {
	                api.confirm(intent.getLongExtra("proposedChannelID", 0), true);
	            } else if (action.equals(BumpAPIIntents.CHANNEL_CONFIRMED)) {	            	
	            	api.send(intent.getLongExtra("channelID", 0), jRoot.toString().getBytes());
	            } else if (action.equals(BumpAPIIntents.CONNECTED)) {
	                api.enableBumping();
	                txtInternetStatus.setText("OK");
	                txtInternetStatus.setBackgroundColor(getResources().getColor(R.color.green));
	            } else{
	            	Log.i(tag,"Get this action: "+action.toString());
	            	Toast.makeText(getApplicationContext(), "No contact, try again", Toast.LENGTH_SHORT).show();		            
	            }	            
	        } catch (RemoteException e) {}
	    }
	};
	
	private final ServiceConnection connection = new ServiceConnection() {	    
		public void onServiceConnected(ComponentName className, IBinder binder) {
			Log.i(tag,"onServiceConnected");
	        api = IBumpAPI.Stub.asInterface(binder);	        
	        Log.i(tag,"after IBumpAPI.Stub.asInterface(binder)");
	        try {
	            new Thread(new Runnable() {					
					public void run() {
						try{
							Log.i(tag,"Inner Try! before api.configured");
							api.configure("de703e6680454adbbf3d1ac99727c9b0", "Cheb");//Max ID
							//api.configure("004d36464fba4d8a99db91ab389929c7", "Cheb");//New Cheb ID
							//api.configure("b00609a8b2f143edba70f8e0bee2754e", "Cheb");//Old ChebID
							Log.i(tag,"Inner Try! after api.configured");
						}catch (RemoteException e) {
							Log.i(tag,"RemoteException error: "+ e.toString());
						}
					}
				}).start();        	
	        } catch (Exception e) {
	        	Log.i(tag,"catch error: "+ e.toString());	
	        }	        	
	    }
		public void onServiceDisconnected(ComponentName name) {}
	};
	
	public void onDestroy() {
        //Log.i(tag, "onDestroy");
		try{
			unbindService(connection);
        	unregisterReceiver(receiver);        
		}catch (Exception e) {
			// TODO: handle exception
		}
        super.onDestroy();
     }	
}
