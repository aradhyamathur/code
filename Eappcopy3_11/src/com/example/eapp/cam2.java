package com.example.eapp;

import com.example.eapp.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class cam2 extends Activity {
	Button b1,b3;
	TextView t1,t2;
	ImageView img;
	Bitmap bmp;
	String path,lat,lng;
	Uri imgsave =null;
	Bundle extras; 
	private int requestCode=100;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.rec_main);
	    
	   // lat=Double.toString(extras.getDouble("lati"));
	    //lng=Double.toString(extras.getDouble("long"));
	   // lng=extras.getString("shake");

	    lat=getIntent().getExtras().getString("lati");
	    lng=getIntent().getExtras().getString("long");
	   
	    b1=(Button) findViewById(R.id.recC);
	    b3=(Button) findViewById(R.id.recV2);
	    
	    t1=(TextView) findViewById(R.id.textView3);
	    t2=(TextView) findViewById(R.id.textView4);
	    t1.setText(lat);
	    t2.setText(lng);
	    
	    
	    img=(ImageView) findViewById(R.id.imageView1);
	    b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(i, requestCode);
				
			}
		});
	  
	    
	    
	    b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent semail=new Intent(Intent.ACTION_VIEW);
				int code=200;
				
				semail.putExtra(Intent.EXTRA_EMAIL	,new String[]{"aradhyamathur@outlook.com"});
				semail.putExtra(Intent.EXTRA_SUBJECT, "eapp");
				semail.putExtra(Intent.EXTRA_TEXT, "lat long");
				semail.setType("message");
				semail.putExtra(Intent.EXTRA_STREAM, imgsave);
				semail.setClassName("com.google.android.gm"	,"com.google.android.gm.ConversationListActivity");
			//	startActivity(Intent.createChooser(semail, "choose ur client"));
	startActivity(semail);
				//startActivityForResult(semail, code);

				finish();
				
			}
		});
	    
	    
	   
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	
	if(requestCode==100){
	bmp=(Bitmap) data.getExtras().get("data");
	img.setImageBitmap(bmp);
	img.setVisibility(View.VISIBLE);
	  path = Images.Media.insertImage(getContentResolver(), bmp,"title", null);
	    imgsave = Uri.parse(path);
	}
	}
	

}/*
if (requestCode == ) {
    if (resultCode == RESULT_OK) {
        // Image captured and saved to fileUri specified in the Intent
        Toast.makeText(this, "Image saved to:\n" +
                 data.getData(), Toast.LENGTH_LONG).show();
    } else if (resultCode == RESULT_CANCELED) {
        // User cancelled the image capture
    } else {
        // Image capture failed, advise user
    }
}}

}*/
