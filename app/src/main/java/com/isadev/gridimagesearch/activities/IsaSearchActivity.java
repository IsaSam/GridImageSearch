package com.isadev.gridimagesearch.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.isadev.gridimagesearch.adapters.ImgResultsAdapter;
import com.isadev.gridimagesearch.models.ImgResult;
import com.isadev.gridimagesearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class IsaSearchActivity extends ActionBarActivity {
    private EditText EtQuery;
    private GridView GvResult;
    private ArrayList<ImgResult> imgResults;
    private ImgResultsAdapter anImgResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isa_search);
        setupIsaViews();
        // Create the data source
        imgResults = new ArrayList<ImgResult>();
        // Attaches the data source to an adapter
        anImgResults = new ImgResultsAdapter(this, imgResults);
        // Link the adapter to the adapterview (gridview)
        GvResult.setAdapter(anImgResults);
    }
    private void setupIsaViews() {
        EtQuery = (EditText) findViewById(R.id.idEtQuery);
        GvResult = (GridView) findViewById(R.id.idGvResult);
        GvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                // Launch the image display activity
                // Creating an intent
                Intent in = new Intent(IsaSearchActivity.this, ImgDisplayActivity.class);
                // Get the image result to display
                ImgResult result = imgResults.get(pos);
                // Pass the image result to display
                //in.putExtra("url", result.theUrl);  //Before
                in.putExtra("result", result); // need to either be serializable or parcelable
                // Launch the new activity
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_isa_search, menu);
        return true;
    }
    // Fired whenever the button is pressed (android:onclick property)
    public void onImgSearch(View v){
        String onQuery = EtQuery.getText().toString();
        //Toast.makeText(this, "Search for: "+onQuery, Toast.LENGTH_SHORT).show();
        AsyncHttpClient Client = new AsyncHttpClient();
        //https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android&rsz=8
        String isaSearchUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + onQuery + "&rsz=8";
        Client.get(isaSearchUrl, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", response.toString());
                JSONArray imgResultsJson = null;
                try {
                    // Get and store decoded array of image results
                    imgResultsJson = response.getJSONObject("responseData").getJSONArray("results");
                    imgResults.clear(); // clear the existing images from the array (in cases where its a new search)
                    //When you make changes to the adapter, it does modify the underlying data
                    anImgResults.addAll(ImgResult.fromJSONArray(imgResultsJson)); // add new items
                    //anImgResults.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("INFO", imgResults.toString());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
