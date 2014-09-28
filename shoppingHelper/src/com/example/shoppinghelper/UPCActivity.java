package com.example.shoppinghelper;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



public class UPCActivity extends Activity{
	// 5 vaules we will parsing to our app


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upc_display_main);

        // getting intent data
        Intent in = getIntent();
     // Get XML values from previous intent
        String IN = in.getStringExtra("IN");
        String BN = in.getStringExtra("BN");
        String ID = in.getStringExtra("ID");
        String CA = in.getStringExtra("CA");
        String TF = in.getStringExtra("TF");
        String CH = in.getStringExtra("CH");
        String SO = in.getStringExtra("SO");
        String TC = in.getStringExtra("TC");
        String PR = in.getStringExtra("PR");
        String VA = in.getStringExtra("VA");
        String VC = in.getStringExtra("VC");
        String CAL = in.getStringExtra("CAL");
        String IR = in.getStringExtra("IR");
        
        

     // Displaying all values on the screen
        TextView inlTitle = (TextView) findViewById(R.id.itemName_label);
        TextView bnlTitle = (TextView) findViewById(R.id.brandN_label);
        TextView idlTitle = (TextView) findViewById(R.id.itemD_label);
        TextView calTitle = (TextView) findViewById(R.id.calories_label);
        TextView tflTitle = (TextView) findViewById(R.id.totalF_label);
        TextView chlTitle = (TextView) findViewById(R.id.cholesterol_label);
        TextView solTitle = (TextView) findViewById(R.id.sodium_label);
        TextView tclTitle = (TextView) findViewById(R.id.totalC_label);
        TextView prlTitle = (TextView) findViewById(R.id.protein_label);
        TextView valTitle = (TextView) findViewById(R.id.vitaminA_label);
        TextView vclTitle = (TextView) findViewById(R.id.vitaminC_label);
        TextView callTitle = (TextView) findViewById(R.id.calcium_label);
        TextView irlTitle = (TextView) findViewById(R.id.iron_label);
        
        inlTitle.setText(IN);
        bnlTitle.setText(BN);
        idlTitle.setText(ID);
        calTitle.setText(CA);
        tflTitle.setText(TF);
        chlTitle.setText(CH);
        solTitle.setText(SO);
        tclTitle.setText(TC);
        prlTitle.setText(PR);
        valTitle.setText(VA);
        vclTitle.setText(VC);
        callTitle.setText(CAL);
        irlTitle.setText(IR);
}

}