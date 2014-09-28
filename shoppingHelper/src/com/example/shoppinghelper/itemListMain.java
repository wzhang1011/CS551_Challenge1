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

public class itemListMain extends ListActivity{
	private ProgressDialog pD;
	
	// 5 vaules we will parsing to our app
	static final String itemN ="name";
	static final String sPrice="salePrice";
	static final String UPC="upc";
	static final String sDescription ="shortDescription";
	static final String pUrl ="productUrl";

	JSONArray Items=null;
	ArrayList<HashMap<String, String>> itemsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		itemsList = new ArrayList<HashMap<String, String>>();
		ListView dlv=getListView();
		 
       
		dlv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String iname = ((TextView) view.findViewById(R.id.itemN)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.idescription)).getText().toString();
                String sp = ((TextView) view.findViewById(R.id.sPrice)).getText().toString();
                String upc = ((TextView) view.findViewById(R.id.upc)).getText().toString();
                String purl = ((TextView) view.findViewById(R.id.url)).getText().toString();


                 
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), itemDetailMain.class);
                in.putExtra(itemN, iname);
                in.putExtra(sPrice, sp);
                in.putExtra(UPC, upc);
                in.putExtra(sDescription, description);
                in.putExtra(pUrl, purl);
 
                startActivity(in);
 
            }
        });
        new GetItems().execute();
    }
	private class GetItems extends AsyncTask<Void, Void, Void> {
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pD = new ProgressDialog(itemListMain.this);
            pD.setMessage("Loading...");
            pD.setCancelable(false);
            pD.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
        	Bundle b=getIntent().getExtras();
			String item = b.getString("Item");
			System.out.println(">>>>>>> "+item);
            // Creating service handler class instance
            HttpHandler sh = new HttpHandler();
 
            // Making a request to url and getting response
            String js = sh.makeHttpCall("http://walmartlabs.api.mashery.com/v1/search?query="+item+"&format=json&apiKey=b5rhabrapujqfj9nh49n26bj", HttpHandler.GET);
 
            Log.d("Response: ", "> " + js);
 
            if (js != null) {
                try {
                    // Getting JSON Array node
                	JSONObject jsonObj = new JSONObject(js);
                	Items = jsonObj.getJSONArray("items");
 
                    // looping through All recalls
                    for (int i = 0; i < Items.length(); i++) {
                        JSONObject jo1 = Items.getJSONObject(i);
                        String in = jo1.getString(itemN);
                        String sp = jo1.getString(sPrice);
                        String upc = jo1.getString(UPC);
                        String sd = jo1.getString(sDescription);
                        String pu = jo1.getString(pUrl);

                        System.out.println(in+"\n"+sp+"\n"+upc+"\n"+sd+"\n"+pu);
 
                        // tmp hashmap for single recall
                        HashMap<String, String> recall = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        recall.put(itemN, in);
                        recall.put(sPrice, sp);
                        recall.put(UPC, upc);
                        recall.put(sDescription, sd);
                        recall.put(pUrl, pu);
  
 
                        // adding contact to recall list
                        itemsList.add(recall);
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
                    itemListMain.this, itemsList,
                    R.layout.itemlistviewmain, new String[] { itemN,sPrice,UPC,sDescription,pUrl }, new int[] {
                        R.id.itemN , R.id.sPrice, R.id.upc,R.id.idescription,R.id.url});
 
            setListAdapter(adapter);
        }
 
    }
	
}

