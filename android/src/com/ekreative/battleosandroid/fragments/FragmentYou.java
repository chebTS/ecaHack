package com.ekreative.battleosandroid.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ekreative.battleosandroid.R;

public class FragmentYou extends FragmentBaseCharacter {
	private int defenceState0, defenceState1;
	private ToggleButton btnDef0, btnDef1;
	
	
	public interface onChangeDefenceEventListener {
	    public void changeDefence(int def0, int def1);
	  }
	  
	onChangeDefenceEventListener changeDefenceEventListener;
	
	public static FragmentYou newInstance(String title) {
		FragmentYou pageFragment = new FragmentYou();
		Bundle bundle = new Bundle();
        bundle.putString("title", title);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			changeDefenceEventListener = (onChangeDefenceEventListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement onSomeEventListener");
		}
	}
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState); 
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		btnHead.toggle();
		btnDef0 = btnHead;
		btnBody.toggle();
		btnDef1 = btnBody; 
		defenceState0 = 0;
		defenceState1 = 1;
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
		
		setHealth(health);
		setLevel(level);
		setExp(exp);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btnHead:
			if ((defenceState0 == 0) || (defenceState1 == 0 )){
				btnHead.setChecked(true);
			}else{
				btnDef0.setChecked(false);
				defenceState0 = defenceState1;
				defenceState1 = 0;
				btnDef0 = btnDef1;
				btnDef1 = btnHead;
			}
			break;
		case R.id.btnBody:
			if ((defenceState0 == 1) || (defenceState1 == 1 )){
				btnBody.setChecked(true);
			}else{
				btnDef0.setChecked(false);
				defenceState0 = defenceState1;
				defenceState1 = 1;
				btnDef0 = btnDef1;
				btnDef1 = btnBody;
			}
			break;
		case R.id.btnLeftHand:
			if ((defenceState0 == 2) || (defenceState1 == 2 )){
				btnLeftHand.setChecked(true);
			}else{
				btnDef0.setChecked(false);
				defenceState0 = defenceState1;
				defenceState1 = 2;
				btnDef0 = btnDef1;
				btnDef1 = btnLeftHand;
			}
			break;
		case R.id.btnRightHand:
			if ((defenceState0 == 3) || (defenceState1 == 3 )){
				btnRightHand.setChecked(true);
			}else{
				btnDef0.setChecked(false);
				defenceState0 = defenceState1;
				defenceState1 = 3;
				btnDef0 = btnDef1;
				btnDef1 = btnRightHand;
			}
			break;
		case R.id.btnLeftFoot:
			if ((defenceState0 == 4) || (defenceState1 == 4 )){
				btnLeftFoot.setChecked(true);
			}else{
				btnDef0.setChecked(false);
				defenceState0 = defenceState1;
				defenceState1 = 4;
				btnDef0 = btnDef1;
				btnDef1 = btnLeftFoot;
			}
			break;
		case R.id.btnRightFoot:
			if ((defenceState0 == 5) || (defenceState1 == 5 )){
				btnRightFoot.setChecked(true);
			}else{
				btnDef0.setChecked(false);
				defenceState0 = defenceState1;
				defenceState1 = 5;
				btnDef0 = btnDef1;
				btnDef1 = btnRightFoot;
			}
			break;	
		default:
			break;
		}
		changeDefenceEventListener.changeDefence(defenceState0, defenceState1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return super.getHealth();
	}

	@Override
	public void setHealth(int health) {
		// TODO Auto-generated method stub
		super.setHealth(health);
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return super.getLevel();
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub
		super.setLevel(level);
	}

	@Override
	public int getExp() {
		// TODO Auto-generated method stub
		return super.getExp();
	}

	@Override
	public void setExp(int exp) {
		// TODO Auto-generated method stub
		super.setExp(exp);
	}  	
	
	
}
