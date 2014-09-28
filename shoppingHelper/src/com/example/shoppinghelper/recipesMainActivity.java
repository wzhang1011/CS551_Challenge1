package com.example.shoppinghelper;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class recipesMainActivity extends ListActivity{
	private ProgressDialog pD;
	static final String recipeN ="recipeName";
	static final String ingredient ="ingredients";
	static final String attr = "attributes";
	static final String rating ="rating";
	static final String flavors ="flavors";
	String ab="attributes";
	JSONArray recipes=null;
	ArrayList<HashMap<String, String>> recipesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		recipesList = new ArrayList<HashMap<String, String>>();
		ListView dlv=getListView();
		 
       
		dlv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String rName = ((TextView) view.findViewById(R.id.recipeN)).getText().toString();
                String ing = ((TextView) view.findViewById(R.id.ingredients)).getText().toString();
                String at = ((TextView) view.findViewById(R.id.attributes)).getText().toString();
                String rate = ((TextView) view.findViewById(R.id.rating)).getText().toString();
                String fal = ((TextView) view.findViewById(R.id.flavors)).getText().toString();

                 
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), recipesDetailMain.class);
                in.putExtra(recipeN, rName);
                in.putExtra(ingredient, ing);
                in.putExtra(attr, at);
                in.putExtra(rating, rate);
                in.putExtra(flavors, fal);
                startActivity(in);
 
            }
        });
        new GetRecipes().execute();
    }
	private class GetRecipes extends AsyncTask<Void, Void, Void> {
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pD = new ProgressDialog(recipesMainActivity.this);
            pD.setMessage("Loading...");
            pD.setCancelable(false);
            pD.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
        	Bundle b=getIntent().getExtras();
			String fd = b.getString("Food");
			System.out.println(">>>>>>> "+fd);
            // Creating service handler class instance
            HttpHandler sh = new HttpHandler();
 
            // Making a request to url and getting response
            String js = sh.makeHttpCall("http://api.yummly.com/v1/api/recipes?_app_id=4ff85b29&_app_key=072c955feef654685ad30542fafc754a&q="+fd+"&maxResult=50&start=1", HttpHandler.GET);
 
            Log.d("Response: ", "> " + js);
 
            if (js != null) {
                try {
                    // Getting JSON Array node
                	JSONObject jsonObj = new JSONObject(js);
                	JSONArray recipes = jsonObj.getJSONArray("matches");

                    // looping through All recalls
                    for (int i = 0; i < recipes.length(); i++) {
                        JSONObject jo1 = recipes.getJSONObject(i);
                        String rn = jo1.getString(recipeN);
                        String ingt = jo1.getString(ingredient);
                        String attra = jo1.getString(attr);
                        String rat = jo1.getString(rating);
                        String flav = jo1.getString(flavors);
                        //System.out.println(rn+"\n"+ingt+"\n"+cos+"\n"+cuis+"\n"+rat+"\n"+flav+"\n"+image);
                        System.out.println(rn+"\n"+ingt+"\n"+rat+"\n"+flav+"\n"+attra);
                        // tmp hashmap for single recall
                        HashMap<String, String> recipe = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        recipe.put(recipeN, rn);
                        recipe.put(ingredient, ingt);
                        recipe.put(attr, attra);
                        recipe.put(rating, rat);
                        recipe.put(flavors, flav);

 
                        // adding contact to recall list
                        recipesList.add(recipe);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the API");
            }
 
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pD.isShowing())
                pD.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    recipesMainActivity.this, recipesList,
                    R.layout.recipesviewmain, new String[] { recipeN,ingredient,attr,rating,flavors }, new int[] {
                        R.id.recipeN , R.id.ingredients, R.id.attributes,R.id.rating,R.id.flavors });
 
            setListAdapter(adapter);
        }
 
    }
	
}

