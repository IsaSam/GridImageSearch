package com.isadev.gridimagesearch.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isadev.gridimagesearch.R;
import com.isadev.gridimagesearch.models.ImgResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Isaac on 7/16/2015.
 */
public class ImgResultsAdapter extends ArrayAdapter<ImgResult> {
    public ImgResultsAdapter(Context context, List<ImgResult> img) {
        //super(context, android.R.layout.simple_list_item_1, img);
        super(context, R.layout.item_img_result, img);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        ImgResult imgInfo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_img_result,parent, false);
        }
        ImageView ivImg = (ImageView) convertView.findViewById(R.id.ivImg);
        TextView tvTtitle = (TextView) convertView.findViewById(R.id.tvTitle);
        // Clear out image from last time
        ivImg.setImageResource(0);
        //Populate title and remote download image url
        tvTtitle.setText(Html.fromHtml(imgInfo.title));
        // Remotely download the image data in the background (with Picasso)
        Picasso.with(getContext()).load(imgInfo.tbUrl).into(ivImg);
        // Return the completed view to be displayed
        return convertView;
    }
}
