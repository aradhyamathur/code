package com.example.tab2;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements TabListener {

	ActionBar actionbar;
	ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	pager=(ViewPager) findViewById(R.id.pager);
	pager.setAdapter(new myfragadap(getSupportFragmentManager()));
		
	actionbar=getActionBar();
	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
	ActionBar.Tab tab1=actionbar.newTab();
	tab1.setText("tab1");
	tab1.setTabListener(this);
	
	ActionBar.Tab tab2=actionbar.newTab();
	tab2.setText("tab2");
	tab2.setTabListener(this);
	
	actionbar.addTab(tab1);
	actionbar.addTab(tab2);
	
	
	
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	public class myfragadap extends FragmentPagerAdapter{

		public myfragadap(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			
			Fragment fragment=null;
			
			if(arg0==0){
				fragment=new frag1();
			}
			else if(arg0==1){
				fragment=new frag2();
			}
			
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		
	}
	
}
