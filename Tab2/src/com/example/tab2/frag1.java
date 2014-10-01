package com.example.tab2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class frag1 extends Fragment{
	View v;
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		
		v=inflater.inflate(R.layout.frag1_main, container,false);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
