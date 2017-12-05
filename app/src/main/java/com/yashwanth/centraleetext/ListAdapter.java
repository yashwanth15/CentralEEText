package com.yashwanth.centraleetext;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by yashw on 05-12-2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public ListAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ListItem listItem=listItems.get(position);

        holder.mCompany.setText(listItem.getCompany());
        holder.mDate.setText(listItem.getDate());
        holder.mEmployee.setText(listItem.getEmployee());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mCompany;
        public TextView mDate;
        public TextView mEmployee;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mCompany=(TextView)itemView.findViewById(R.id.company);
            mDate=(TextView)itemView.findViewById(R.id.date);
            mEmployee=(TextView)itemView.findViewById(R.id.employee);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,CompanyInfo.class);
            context.startActivity(intent);
        }
    }
}
