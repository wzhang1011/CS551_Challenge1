package com.example.shoppinghelper;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	static final String itemN ="item_name";
	static final String brandN ="brand_name";
	static final String itemType="item_description";
	static final String itemD="nf_ingredient_statement";
	static final String calories ="nf_calories";
	static final String totalF ="nf_total_fat";
	static final String cholesterol ="nf_cholesterol";
	static final String sodium="nf_sodium";
	static final String totalC="nf_total_carbohydrate";
	static final String protein="nf_protein";
	static final String vitaminA="nf_vitamin_a_dv";
	static final String vitaminC="nf_vitamin_c_dv";
	static final String calcium="nf_calcium_dv";
	static final String iron="nf_iron_dv";
	static final String jsonstring="";
	Button b1,b2,b3,b4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		addListenerOnButton();
	}

	public void addListenerOnButton() {
		 
		final Context context = this;
 
		b2 = (Button) findViewById(R.id.button2);
		b1 = (Button) findViewById(R.id.button1);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
		
		
		
		b2.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				EditText ZIP = (EditText) findViewById(R.id.editText2);
				String ZText = ZIP.getText().toString();
				System.out.println(">>>>>>>>>>>>>>>>>>"+ZText);
				HttpHandler sh = new HttpHandler();
				 
	            // Making a request to url and getting response
	            String js = sh.makeHttpCall("https://api.nutritionix.com/v1_1/item?upc="+ZText+"&appId=f22a197c&appKey=661b96936ff9f65d764f45e055cfe6c9", HttpHandler.GET);
	 
	            Log.d("Response: ", "> " + js);
	 
	            if (js != null) {
	                try {
	                		JSONObject jsob = new JSONObject(js);
	                        //JSONObject jo1 = jsob.getJSONObject(jsonstring);
	                        String iName = jsob.getString(itemN);
	                        String bName = jsob.getString(brandN);
	                        String iDescrp = jsob.getString(itemD);
	                        String cal = jsob.getString(calories);
	                        String tFat = jsob.getString(totalF);
	                        String chol = jsob.getString(cholesterol);
	                        String sod = jsob.getString(sodium);
	                        String tCar = jsob.getString(totalC);
	                        String prot = jsob.getString(protein);
	                        String vA = jsob.getString(vitaminA);
	                        String vC = jsob.getString(vitaminC);
	                        String Calcium = jsob.getString(calcium);
	                        String ir = jsob.getString(iron);
	                        System.out.println(iName+"\n"+bName+"\n"+iDescrp+"\n"+cal+"\n"+tFat+"\n"+chol+"\n"+sod+"\n"+tCar+"\n"+prot+"\n"+vA+"\n"+vC+"\n"+Calcium+"\n"+ir);
	                        Intent intent = new Intent(context, UPCActivity.class);
	                        Bundle a= new Bundle();
	        				a.putString("IN", iName);
	        				intent.putExtras(a);
	        				
	        				Bundle b= new Bundle();
	        				b.putString("BN", bName);
	        				intent.putExtras(b);
	        				
	        				Bundle c= new Bundle();
	        				c.putString("ID", iDescrp);
	        				intent.putExtras(c);
	        				
	        				Bundle d= new Bundle();
	        				d.putString("CA", cal);
	        				intent.putExtras(d);
	        				
	        				Bundle e= new Bundle();
	        				e.putString("TF", tFat);
	        				intent.putExtras(e);
	        				
	        				Bundle f= new Bundle();
	        				f.putString("CH", chol);
	        				intent.putExtras(f);
	        				
	        				Bundle g= new Bundle();
	        				g.putString("SO", sod);
	        				intent.putExtras(g);
	        				
	        				Bundle h= new Bundle();
	        				h.putString("TC", tCar);
	        				intent.putExtras(h);
	        				
	        				Bundle i= new Bundle();
	        				i.putString("PR", prot);
	        				intent.putExtras(i);
	        				
	        				Bundle j= new Bundle();
	        				j.putString("VA", vA);
	        				intent.putExtras(j);
	        				
	        				Bundle k= new Bundle();
	        				k.putString("VC", vC);
	        				intent.putExtras(k);
	        				
	        				Bundle l= new Bundle();
	        				l.putString("CAL", Calcium);
	        				intent.putExtras(l);
	        				
	        				Bundle m= new Bundle();
	        				m.putString("IR", ir);
	        				intent.putExtras(m);
	        				
	        				
	        				
	        				
	        				
	        				
	        				System.out.println(iName+"-------"+a);
	        	            startActivity(intent);
				    }catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            } else {
	                Log.e("ServiceHandler", "Couldn't get any data from the API");
	            }
	 
	            return ;
				
			}
 
		});
		b1.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				EditText UPC = (EditText) findViewById(R.id.editText1);
			    int UPCText =0;
			    UPCText = Integer.parseInt(UPC.getText().toString());
				Intent intent = new Intent(context, dailyDealMain.class);
				Bundle z= new Bundle();
				z.putInt("UPC", UPCText);
				intent.putExtras(z);
				System.out.println(UPCText+"-------"+z);
               startActivity(intent);   
 
			}
 
		});
		b3.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				EditText food = (EditText) findViewById(R.id.editText3);
			    String fText = food.getText().toString();
				Intent intent = new Intent(context, recipesMainActivity.class);
				Bundle z= new Bundle();
				z.putString("Food", fText);
				intent.putExtras(z);
				System.out.println(fText+"-------"+z);
               startActivity(intent);   
 
			}
 
		});
		b4.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				EditText items = (EditText) findViewById(R.id.editText4);
			    String iText = items.getText().toString();
				Intent intent = new Intent(context, itemListMain.class);
				Bundle z= new Bundle();
				z.putString("Item", iText);
				intent.putExtras(z);
				System.out.println(iText+"-------"+z);
               startActivity(intent);   
 
			}
 
		});
}
}