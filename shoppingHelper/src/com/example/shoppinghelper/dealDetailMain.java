package com.example.shoppinghelper;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class dealDetailMain extends Activity{

	static final String originalP ="dealOriginalPrice";
	static final String PriceN ="dealPrice";
	static final String localUrl="storeURL";
	static final String dealT="dealTitle";
	static final String dealInfo ="dealinfo";
	static final String expirationD ="expirationDate";
	static final String store ="name";
	static final String address="address";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_deal_page);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String op = in.getStringExtra(originalP);
        String Dp = in.getStringExtra(PriceN);
        String SN = in.getStringExtra(store);
        String SA = in.getStringExtra(address);
        String title = in.getStringExtra(dealT);
        String description = in.getStringExtra(dealInfo);
        String date = in.getStringExtra(expirationD);
        String link = in.getStringExtra(localUrl);
        // Displaying all values on the screen
        TextView lblTitle = (TextView) findViewById(R.id.title_label);
        TextView lblDesc = (TextView) findViewById(R.id.description_label);
        TextView lblDate = (TextView) findViewById(R.id.expiration_Label);
        TextView lblLink = (TextView) findViewById(R.id.location_label);
        TextView lblProvider = (TextView) findViewById(R.id.provider_label);
        TextView lblOP = (TextView) findViewById(R.id.op);
        TextView lblSP = (TextView) findViewById(R.id.sp_label);
        TextView lblAddress = (TextView) findViewById(R.id.address_label);

        
        lblTitle.setText(title);
        lblDesc.setText(description);
        lblDate.setText(date);
        lblLink.setText(link);
        lblProvider.setText(SN);
        lblOP.setText(op);
        lblSP.setText(Dp);
        lblAddress.setText(SA);
        
    }
}



