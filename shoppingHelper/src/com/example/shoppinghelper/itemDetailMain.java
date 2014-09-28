package com.example.shoppinghelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class itemDetailMain extends Activity{
	static final String itemN ="name";
	static final String sPrice="salePrice";
	static final String UPC="upc";
	static final String sDescription ="shortDescription";
	static final String pUrl ="productUrl";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_page);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String IN = in.getStringExtra(itemN);
        String SP = in.getStringExtra(sPrice);
        String U = in.getStringExtra(UPC);
        String sD = in.getStringExtra(sDescription);
        String PU = in.getStringExtra(pUrl);
        System.out.println("))"+IN);
        // Displaying all values on the screen
        TextView inTV = (TextView) findViewById(R.id.itemname_label);
        TextView spTV = (TextView) findViewById(R.id.salep_label);
        TextView uTV = (TextView) findViewById(R.id.UPC_Label);
        TextView idTV = (TextView) findViewById(R.id.itemdescription_label);
        TextView puTV = (TextView) findViewById(R.id.url_label);

        
        inTV.setText(IN);
        spTV.setText(SP);
        uTV.setText(U);
        idTV.setText(sD);
        puTV.setText(PU);

        
    }
}
