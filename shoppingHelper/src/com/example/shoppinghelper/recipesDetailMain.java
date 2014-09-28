package com.example.shoppinghelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class recipesDetailMain extends Activity{
	static final String recipeN ="recipeName";
	static final String ingredient ="ingredients";
	static final String attr = "attributes";
	static final String rating ="rating";
	static final String flavors ="flavors";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_recipe_page);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String rn = in.getStringExtra(recipeN);
        String ig = in.getStringExtra(ingredient);
        String at = in.getStringExtra(attr);
        String rt = in.getStringExtra(rating);
        String fl = in.getStringExtra(flavors);
        System.out.println("%%%%%%%%%%%%%"+rn);
        // Displaying all values on the screen
        TextView rnTV = (TextView) findViewById(R.id.recipeN_label);
        TextView igTV = (TextView) findViewById(R.id.ingredient_label);
        TextView atTV = (TextView) findViewById(R.id.attributes_label);
        TextView rtTV = (TextView) findViewById(R.id.rating_Label);
        TextView flTV = (TextView) findViewById(R.id.Flavors_lable);

        
        rnTV.setText(rn);
        igTV.setText(ig);
        atTV.setText(at);
        rtTV.setText(rt);
        flTV.setText(fl);

        
    }

}
