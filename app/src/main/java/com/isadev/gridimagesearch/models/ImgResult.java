package com.isadev.gridimagesearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Isaac on 7/16/2015.
 */
//public class ImgResult {   //Before
public class  ImgResult implements Serializable{
    private static final long serialVersionUID = -5961377510890524197L;
    public String theUrl;
    public String tbUrl;
    public String title;

    // new ImageResult(.. raw item json...)
    public ImgResult(JSONObject json){
        try{
            this.theUrl = json.getString("url");
            this.tbUrl = json.getString("tbUrl");
            this.title = json.getString("title");

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    // Take an array of json images and return arraylist of image results
    // ImgResult.fromJSONArray([...,...])
    public  static ArrayList<ImgResult> fromJSONArray(JSONArray array){
        ArrayList<ImgResult> results = new ArrayList<ImgResult>();
        for (int i= 0; i < array.length(); i++){
            try{
                results.add(new ImgResult(array.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  results;
    }
}
