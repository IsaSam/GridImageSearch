package com.isadev.gridimagesearch.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.isadev.gridimagesearch.R;
import com.isadev.gridimagesearch.models.ImgResult;
import com.squareup.picasso.Picasso;


public class ImgDisplayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_display);
        // Remove the actionbar on the image display activity
        //getActionBar().hide(); //After...
        getSupportActionBar().hide();
        // Pull out the url from the intent
        //String urlKey = getIntent().getStringExtra("url");  //Before
        ImgResult result = (ImgResult) getIntent().getSerializableExtra("result");
        // Find the image view
        ImageView ivImgResult = (ImageView) findViewById(R.id.ivImgResult);
        // Load the image url into the image view using Picasso
        Picasso.with(this).load(result.theUrl).into(ivImgResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_img_display, menu);
        return true;
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
