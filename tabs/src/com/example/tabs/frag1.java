package com.example.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class frag1 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		
		View v;
		v=inflater.inflate(R.layout.fra1_main, container,false);
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
	return v;
	
	}
}
