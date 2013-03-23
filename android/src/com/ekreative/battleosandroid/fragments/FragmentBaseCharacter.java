package com.ekreative.battleosandroid.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ekreative.battleosandroid.BattleActivity;
import com.ekreative.battleosandroid.R;

public class FragmentBaseCharacter extends Fragment implements OnClickListener{
	protected ToggleButton btnHead, btnBody, btnLeftHand, btnRightHand, btnLeftFoot, btnRightFoot;
	protected BattleActivity mActivity;
	protected int health, level, exp;
	protected TextView txtHealth, txtLevel, txtExp;
	private SharedPreferences mSharedPreferences;
	private final String TAG_HEALTH = "health";
	private final String TAG_LEVEL = "level";
	private final String TAG_EXP = "exp";
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = (BattleActivity)getActivity();
		btnHead.setOnClickListener(this);
		btnBody.setOnClickListener(this); 
		btnLeftHand.setOnClickListener(this);
		btnRightHand.setOnClickListener(this); 
		btnLeftFoot.setOnClickListener(this); 
		btnRightFoot.setOnClickListener(this);
		mSharedPreferences = mActivity.getSharedPreferences("userdata", 0);
		health = mSharedPreferences.getInt(TAG_HEALTH, 1000);
		level = mSharedPreferences.getInt(TAG_LEVEL, 0);
		exp = mSharedPreferences.getInt(TAG_EXP, 0);
		
	}

	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
		View view = inflater.inflate(R.layout.fragment_character, container, false);  
        TextView textView = (TextView) view.findViewById(R.id.textView);  
        textView.setText(getArguments().getString("title"));        
        btnHead = (ToggleButton)view.findViewById(R.id.btnHead);	        
		btnBody = (ToggleButton)view.findViewById(R.id.btnBody);
		btnLeftHand = (ToggleButton)view.findViewById(R.id.btnLeftHand);
		btnRightHand = (ToggleButton)view.findViewById(R.id.btnRightHand); 
		btnLeftFoot = (ToggleButton)view.findViewById(R.id.btnLeftFoot); 
		btnRightFoot = (ToggleButton)view.findViewById(R.id.btnRightFoot);	
		txtHealth = (TextView) view.findViewById(R.id.txtHealth);
		txtLevel = (TextView) view.findViewById(R.id.txtLevel);
		txtExp = (TextView) view.findViewById(R.id.txtExp);		
		
        return view;  
    }
	
	

	@Override
	public void onClick(View v) {}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		txtHealth.setText(Integer.toString(health));
//		if (txtHealth != null){
//			Toast.makeText(mActivity, "!= null", Toast.LENGTH_SHORT).show();
//		}else{
//			Toast.makeText(mActivity, "== null", Toast.LENGTH_SHORT).show();
//				
//		}
			
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		//if (txtLevel != null)
			txtLevel.setText(Integer.toString(level));
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
		//if (txtExp != null)
			txtExp.setText(Integer.toString(exp));
	}
	
	
	
}
