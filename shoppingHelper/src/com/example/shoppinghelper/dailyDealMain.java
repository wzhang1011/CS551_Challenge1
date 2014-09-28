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

public class dailyDealMain extends ListActivity{
	private ProgressDialog pD;
	//static final String URL = "http://api.8coupons.com/v1/getdeals?key=82c09490a25345e7df1a3462c6577bfcbffb758038553f8026a4ebd40d581af68d7f8b8319e3994783ad10191f344605&zip=66103&mileradius=20&limit=50&orderby=radius&categoryid=6";
	// 5 vaules we will parsing to our app
	static final String originalP ="dealOriginalPrice";
	static final String PriceN ="dealPrice";
	static final String localUrl="storeURL";
	static final String dealT="dealTitle";
	static final String dealInfo ="dealinfo";
	static final String expirationD ="expirationDate";
	static final String store ="name";
	static final String address="address";
	static final String deal="";
	static final String ZIP="editText1";
	JSONArray deals=null;
	ArrayList<HashMap<String, String>> dealsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deal_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		dealsList = new ArrayList<HashMap<String, String>>();
		ListView dlv=getListView();
		 
       
		dlv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String title = ((TextView) view.findViewById(R.id.title)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
                String eDate = ((TextView) view.findViewById(R.id.expiration)).getText().toString();
                String location = ((TextView) view.findViewById(R.id.link)).getText().toString();
                String price = ((TextView) view.findViewById(R.id.Price)).getText().toString();
                String Oprice = ((TextView) view.findViewById(R.id.Oprice)).getText().toString();
                String Name = ((TextView) view.findViewById(R.id.provider)).getText().toString();
                String Address = ((TextView) view.findViewById(R.id.address)).getText().toString();

                 
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), dealDetailMain.class);
                in.putExtra(dealT, title);
                in.putExtra(dealInfo, description);
                in.putExtra(localUrl, location);
                in.putExtra(expirationD, eDate);
                in.putExtra(PriceN, price);
                in.putExtra(originalP, Oprice);
                in.putExtra(store, Name);
                in.putExtra(address, Address);
                startActivity(in);
 
            }
        });
        new GetDeals().execute();
    }
	private class GetDeals extends AsyncTask<Void, Void, Void> {
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pD = new ProgressDialog(dailyDealMain.this);
            pD.setMessage("Loading...");
            pD.setCancelable(false);
            pD.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
        	Bundle b=getIntent().getExtras();
			int zip = b.getInt("zip");
			System.out.println(">>>>>>> "+zip);
            // Creating service handler class instance
            HttpHandler sh = new HttpHandler();
 
            // Making a request to url and getting response
            String js = sh.makeHttpCall("http://api.8coupons.com/v1/getdeals?key=82c09490a25345e7df1a3462c6577bfcbffb758038553f8026a4ebd40d581af68d7f8b8319e3994783ad10191f344605&zip="+zip+"&mileradius=20&limit=50&orderby=radius&categoryid=6", HttpHandler.GET);
 
            Log.d("Response: ", "> " + js);
 
            if (js != null) {
                try {
                    // Getting JSON Array node
                    deals = new JSONArray(js);
 
                    // looping through All recalls
                    for (int i = 0; i < deals.length(); i++) {
                        JSONObject jo1 = deals.getJSONObject(i);
                        String name = jo1.getString(store);
                        String title = jo1.getString(dealT);
                        String description = jo1.getString(dealInfo);
                        String Dprice = jo1.getString(PriceN);
                        String eDate = jo1.getString(expirationD);
                        String Address = jo1.getString(address);
                        String OP = jo1.getString(originalP);
                        String link = jo1.getString(localUrl);
                        System.out.println(name+"\n"+title+"\n"+description+"\n"+Dprice+"\n"+OP+"\n"+eDate+"\n"+Address+"\n"+link);
 
                        // tmp hashmap for single recall
                        HashMap<String, String> recall = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        recall.put(store, name);
                        recall.put(dealT, title);
                        recall.put(dealInfo, description);
                        recall.put(PriceN, Dprice);
                        recall.put(expirationD, eDate);
                        recall.put(originalP, OP);
                        recall.put(localUrl, link);
                        recall.put(address, Address);
 
                        // adding contact to recall list
                        dealsList.add(recall);
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
                    dailyDealMain.this, dealsList,
                    R.layout.dealviewmain, new String[] { store,dealT,dealInfo,PriceN,expirationD,originalP,address,localUrl }, new int[] {
                        R.id.provider , R.id.title, R.id.description, R.id.Price,R.id.expiration,R.id.Oprice,R.id.address,R.id.link });
 
            setListAdapter(adapter);
        }
 
    }
	
}
