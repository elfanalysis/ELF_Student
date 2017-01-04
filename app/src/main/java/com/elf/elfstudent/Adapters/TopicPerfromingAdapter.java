package com.elf.elfstudent.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.elf.elfstudent.CustomUI.HelviticaLight;
import com.elf.elfstudent.R;
import com.elf.elfstudent.Utils.ScreenUtil;
import com.elf.elfstudent.model.Topic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kri on 02-01-2017.
 */
public class TopicPerfromingAdapter  extends RecyclerView.Adapter<TopicPerfromingAdapter.TopicViewer> {



    private android.content.Context Context = null;
    private List<Topic> topicList = null;
    LayoutInflater inflater = null;
    private int Animated_item_count = 0;
    private int last_pos = -1 ;

    public TopicPerfromingAdapter(Context mContext, List<Topic> mList) {
        this.Context = mContext;
        this.topicList = mList;
        Animated_item_count = mList.size();
    }

    @Override
    public TopicPerfromingAdapter.TopicViewer onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(Context);

        }

        return null;
//        return new TopicPerfromingAdapter.TopicViewer(inflater.inflate(R.layout.topic_recom_item_row,parent,false));
    }

    private void runEnterAnimations(TopicPerfromingAdapter.TopicViewer holder, int position) {


        if (position >= Animated_item_count){

            return;
        }

        if (position>last_pos){


            last_pos=position;
            holder.itemView.setTranslationY(ScreenUtil.getScreenHeight(Context));
            holder.itemView.setScaleX(0.2f);

            holder.itemView.animate().translationY(0)
                    .scaleX(1)

                    .setInterpolator(new DecelerateInterpolator(2f))
                    .setDuration(600)
                    .start();
        }
    }
    @Override
    public void onBindViewHolder(TopicPerfromingAdapter.TopicViewer holder, int position) {
        runEnterAnimations(holder,position);
//        holder.TopicName.setText(topicList.get(position).getTopicName());
//        holder.mPoints.setText(String.valueOf((int)Float.parseFloat(topicList.get(position).getPoints())));


    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static class TopicViewer extends RecyclerView.ViewHolder{


        //Topic Name
//        @BindView(R.id.topi_list_item_title)
//        HelviticaLight TopicName;
        //Points in Topic
//        @BindView(R.id.points_topic_item) TextView mPoints;



        public TopicViewer(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}