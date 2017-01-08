package com.elf.elfstudent.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elf.elfstudent.R;
import com.elf.elfstudent.model.AllTestModels;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by ELF on 07-01-2017.
 *
 */

public class TesteViewerAdapter extends RecyclerView.Adapter<TesteViewerAdapter.TestViewHolder>{


    private Context mContext;
    private List<AllTestModels> data =  null;
    public TesteViewerAdapter(Context mContext, List<AllTestModels> data) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.test_view_item,parent,false);



        return new TestViewHolder(v);

    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        holder.mText.setText(data.get(position).getmSubjectName());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class  TestViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.test_view_item_desc)
        TextView mText;
        public TestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
