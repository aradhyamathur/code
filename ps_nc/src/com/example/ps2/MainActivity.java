package com.example.ps2;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
ListView l1;
TextView t1,t;
String mem,fname,s1;
proinfo p1;
ArrayAdapter<Integer> arrayAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t1=(TextView) findViewById(R.id.textView1);
		t=(TextView) findViewById(R.id.textView3);
		File fi=new File("/proc");
		p1=new proinfo();
		String[] names2=new String[]{};
		try {
			Scanner scan=new Scanner(new FileReader(fi));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		l1=(ListView) findViewById(R.id.listView1);
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(fi.list()));
		

		ArrayList<Integer> pname=new ArrayList<Integer>();
	for (int index=0,j=0;index<names.size();index++){
			
			try{
				
				pname.add(j, Integer.parseInt(names.get(index)));
				j++;
				
			}catch(Exception e){
				
			}
		}
		    
	arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1, pname);
	l1.setAdapter(arrayAdapter);
	
		
	l1.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int pos,
				long id) {

			String s;
			s=arrayAdapter.getItem(pos).toString();
		/*	 Bundle det=new Bundle();
			 det.putString("details", s);
			 Intent i=new Intent(MainActivity.this,proc.class);
			 i.putExtras(det);
			 startActivity(i);	*/
			 fname="/proc/"+s+"/status";
				File f1=new File(fname);
			
				  try {
						Scanner scan=new Scanner(new FileReader(f1));
					
						while(scan.hasNext()){
							
							s1=scan.next();
							
							switch(s1){
							
							case "Name:":
								p1.setName(scan.next());
							break;
							
							case "Pid:":
								p1.setPid(Integer.parseInt(scan.next()));
							break;
							
							case "Threads:":
									p1.setThread(Integer.parseInt(scan.next()));
							break;
							
							case "State:":
								p1.setState(scan.next());
								break;
			
								default:;
							
							}
						}	    
				    } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				  String message=new String();
				   message="Name:"+p1.getName()+"\t"+"  Pid:"+p1.getPid()+"\t"+"Threads:"+p1.getThread()+"\t"+"State:"+p1.getState();
				   
				   t.setText(message);	
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
						mem=s+Integer.parseInt(scan.next())/1000+"MB";
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
