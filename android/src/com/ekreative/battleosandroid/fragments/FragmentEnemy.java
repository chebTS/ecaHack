package com.ekreative.battleosandroid.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ekreative.battleosandroid.R;

public class FragmentEnemy extends FragmentBaseCharacter {
	private int attackState;
	
	public interface onChangeAttackEventListener {
	    public void changeAttack(int attack);
	  }
	  
	onChangeAttackEventListener changeAttackEventListener;
	
	public static FragmentEnemy newInstance(String title) {
		FragmentEnemy pageFragment = new FragmentEnemy();		
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState); 
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			changeAttackEventListener = (onChangeAttackEventListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement onSomeEventListener");
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btnHead:
			if (attackState == 0)
				btnHead.setChecked(true);					
			btnBody.setChecked(false);
			btnLeftHand.setChecked(false);
			btnRightHand.setChecked(false);
			btnLeftFoot.setChecked(false);
			btnRightFoot.setChecked(false);
			attackState = 0;
		break;
	case R.id.btnBody:
			if (attackState == 1)
				btnBody.setChecked(true);					
			btnHead.setChecked(false);
			btnLeftHand.setChecked(false);
			btnRightHand.setChecked(false);
			btnLeftFoot.setChecked(false);
			btnRightFoot.setChecked(false);
			attackState = 1;
		break;
	case R.id.btnLeftHand:
			if (attackState == 2)
				btnLeftHand.setChecked(true);					
			btnHead.setChecked(false);
			btnBody.setChecked(false);
			btnRightHand.setChecked(false);
			btnLeftFoot.setChecked(false);
			btnRightFoot.setChecked(false);
			attackState = 2;
		break;
	case R.id.btnRightHand:
			if (attackState == 3)
				btnRightHand.setChecked(true);					
			btnHead.setChecked(false);
			btnBody.setChecked(false);
			btnLeftHand.setChecked(false);
			btnLeftFoot.setChecked(false);
			btnRightFoot.setChecked(false);
			attackState = 3;
		break;
	case R.id.btnLeftFoot:
			if (attackState == 4)
				btnLeftFoot.setChecked(true);					
			btnHead.setChecked(false);
			btnBody.setChecked(false);
			btnLeftHand.setChecked(false);
			btnRightHand.setChecked(false);
			btnRightFoot.setChecked(false);
			attackState = 4;
		break;
	case R.id.btnRightFoot:
			if (attackState == 5)
				btnRightFoot.setChecked(true);					
			btnHead.setChecked(false);
			btnBody.setChecked(false);
			btnLeftHand.setChecked(false);
			btnRightHand.setChecked(false);
			btnLeftFoot.setChecked(false);
			attackState = 5;
		break;
	default:
		break;
		}
		changeAttackEventListener.changeAttack(attackState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		btnHead.toggle();
		attackState = 0;
//		setHealth(health);
//		setLevel(level);
//		setExp(exp);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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
