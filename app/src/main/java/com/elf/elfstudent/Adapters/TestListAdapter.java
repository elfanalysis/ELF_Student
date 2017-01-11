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
 * Created by ELF on 08-01-2017.
 *
 */
public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.TestListHolder>{

    private Context mContext;
    private List<AllTestModels> mlist ;

    public TestListAdapter(Context mContext, List<AllTestModels> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }

    @Override
    public TestListAdapter.TestListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View  v  = LayoutInflater.from(mContext).inflate(R.layout.test_list_item_view,parent,false);
        return new TestListHolder(v);
    }

    @Override
    public void onBindViewHolder(TestListAdapter.TestListHolder holder, int position) {
        holder.mText.setText(mlist.get(position).getmTestDetail());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class  TestListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.test_list_item_ts)
        TextView mText;

        public TestListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
