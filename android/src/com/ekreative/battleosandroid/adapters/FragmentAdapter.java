package com.ekreative.battleosandroid.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.ekreative.battleosandroid.fragments.FragmentYou;
import com.ekreative.battleosandroid.fragments.FragmentEnemy;

public class FragmentAdapter extends FragmentPagerAdapter {
	FragmentYou fragmentYou;
	FragmentEnemy fragmentEnemy;

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
		fragmentYou = FragmentYou.newInstance("It`s You");
		fragmentEnemy = FragmentEnemy.newInstance("It`s enemy");
	}

	@Override
	public Fragment getItem(int index) {
		Log.i("getItem",Integer.toString(index));
		if (index == 0){
			//return FragmentYou.newInstance("It`s You");
			return fragmentYou;
		}else{
			return fragmentEnemy;
			//return FragmentEnemy.newInstance("It`s enemy");
		}	
	}
	

	@Override
	public float getPageWidth(int position) {
		//return super.getPageWidth(position);
		return(0.85f); 
	}

	@Override
	public int getCount() {
		return 2;
	}

}
