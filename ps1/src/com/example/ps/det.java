package com.example.ps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class det extends Activity {

	TextView t;
	String s1,fname,data;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.det_main);
	    
	    
	    Bundle details=new Bundle();
	    details=getIntent().getExtras();
	    String s=details.getString("details");
	
	   fname="/proc/"+s+"/status";
	   File fi=new File(fname);
	   try {
		Scanner scan=new Scanner(new FileReader(fi));
		while(scan.hasNext()){
			
			s1=scan.next();
			
			if(s1.equals("Name:")||s1.equals("Pid:")||s1.equals("Threads:")){
				if(s1.equals("null"))
				data+=scan.next()+"\n";
				else 
					data+=s1+scan.next()+"\n";
			}
			
			
		}
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    
	    
	    t=(TextView) findViewById(R.id.textView1);
	    t.setText(data);
	
	
	}

}
