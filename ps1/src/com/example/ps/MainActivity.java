package com.example.ps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
ListView l1;
TextView t,t1;
String mem,cpu,c;
ArrayAdapter<Integer> arrayAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		int i=0;
		File fi=new File("/proc");
		File f2=new File("/proc/cpuinfo");
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(fi.list()));
		String[] names2=new String[]{};
		String pnames[]=new String[]{};
		
		try {
			Scanner scan2=new Scanner(new FileReader(f2));
			do{
				c=scan2.nextLine();
				
			}while(false);
			
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		ArrayList<Integer> pname=new ArrayList<Integer>();
		
		names2=fi.list();
				String namer=Arrays.toString(names2);	
		t=(TextView) findViewById(R.id.textView1);
		t.setText(c);
		t1=(TextView) findViewById(R.id.textView2);	
		
		
		for (int index=0,j=0;index<names.size();index++){
			
			try{
				
				pname.add(j, Integer.parseInt(names.get(index)));
				j++;
				
			}catch(Exception e){
				
			}
			
			
		}
		    
		
		
		
		l1=(ListView) findViewById(R.id.listView1);
	
		arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1, pname);
	l1.setAdapter(arrayAdapter);
	
	
	l1.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int pos,
				long id) {
			String s;
			s=arrayAdapter.getItem(pos).toString();
			 Bundle det=new Bundle();
			 det.putString("details", s);
			 Intent i=new Intent(MainActivity.this,det.class);
			 i.putExtras(det);
			 startActivity(i);
			
			
		}
	});
	
	
	memusage m1=new memusage();
	m1.execute();
	
		
	}



public class memusage extends AsyncTask<Void, String, Void>{
	
	@Override
	protected void onPreExecute() {
	
		super.onPreExecute();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
	while(true){
		File f1=new File("/proc/meminfo");
	
		
		try {
			Scanner scan=new Scanner(new FileReader(f1));
			while(scan.hasNext()){
				String s;
				long val;
				s=scan.next();
				if(s.equals("MemFree:")){
			//	val=Long.parseLong(scan.next())/1024;
					mem=s+scan.next()+"kB";
					}
			
			
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	//	t1.setText(mem);
		publishProgress(mem);
		
		
		}
	}

	@Override
	protected void onProgressUpdate(String... values) {
	String message = null;
	super.onProgressUpdate(values);
	for(String str: values){
		message=str;
	}
	
	
	update(message);
	
	
}



		}


public void update(String str){
	t1.setText(str);
}



}
