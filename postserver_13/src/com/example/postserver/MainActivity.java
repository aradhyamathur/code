package com.example.postserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText e1,e2;
	Button b1;
	String name,no,data;
	TextView t1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	e1=(EditText) findViewById(R.id.in_name);
	e2=(EditText) findViewById(R.id.in_no);
	b1=(Button) findViewById(R.id.button1);
	t1=(TextView) findViewById(R.id.textView1);
	
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				try {
					putdata();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
	}

	public void putdata() throws UnsupportedEncodingException{
		
		name=e1.getText().toString();
		no=e2.getText().toString();
      

 /*
	   
	   data = URLEncoder.encode("name", "UTF-8")
               + "=" + URLEncoder.encode(name, "UTF-8");
	   
	   data += "&" + URLEncoder.encode("phno", "UTF-8") + "="
	               + URLEncoder.encode(no, "UTF-8");
	*/
	
	
	try{  
		JSONObject object = new JSONObject();
		object.put("name", name);
		object.put("phno", no);
		JSONArray postjson=new JSONArray();
		postjson.put(object);
		List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);    
		nvp.add(new BasicNameValuePair("json", object.toString()));
	
		String url ="http://192.168.1.105/test3.php";
       //JSONArray array=new JSONArray();
       //array.put(object);
       // Send POST data request

		HttpClient httpclient = new DefaultHttpClient(); 
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("json",object.toString());
		httppost.getParams().setParameter("jsonpost", postjson);
		System.out.print(object);
		
		//httppost.setEntity(new UrlEncodedFormEntity(nvp));
		HttpResponse response = httpclient.execute(httppost); 
		InputStream is;
    
        is = response.getEntity().getContent();
      //    Toast.makeText(getApplicationContext(), "received", Toast.LENGTH_LONG).show();
      
      
      
	  
	  
	  /*
       URLConnection conn = url.openConnection();
       conn.setDoOutput(true);
       OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write( object.toString());
        wr.flush();
    */
        // Get the server response
         String text = "";
         BufferedReader reader=null;
         reader = new BufferedReader(new InputStreamReader(is));
      StringBuilder sb = new StringBuilder();
      String line = null;
      
      // Read Server Response
      while((line = reader.readLine()) != null)
          {
                 // Append server response in string
                 sb.append(line + "\n");
          }
          
          
          text = sb.toString();
		reader.close();
		t1.setText(text);
		
	}catch(Exception ex){
		
	}
	
	}	
}
