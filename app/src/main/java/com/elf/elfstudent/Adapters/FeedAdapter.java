package com.elf.elfstudent.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elf.elfstudent.R;
import com.elf.elfstudent.model.Feed;

import java.util.List;

/**
 * Created by ELF on 05-01-2017.
 *
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    List<Feed> data  =  null;
    private Context mContext  = null;
    public
    FeedAdapter(List<Feed> data,Context mContext) {
        data = data;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 *2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:View v = LayoutInflater.from(mContext).inflate(R.layout.image_feed,parent,false);
                 return  new ImageHodler(v);
            case 2: View vi = LayoutInflater.from(mContext).inflate(R.layout.text_feed,parent,false);
                return new TextFeed(vi);

        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }


    public static class ImageHodler extends RecyclerView.ViewHolder{


        public ImageView  img  = null;

        public ImageHodler(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.image_view_feed);
        }
    }

    public static class TextFeed extends RecyclerView.ViewHolder{


        public TextView mText;

        public TextFeed(View itemView) {
            super(itemView);
            mText = (TextView)itemView.findViewById(R.id.text_feeddd);

        }
    }
}
