package com.example.nishan.donar_list.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nishan.donar_list.Model.Donar;
import com.example.nishan.donar_list.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishan on 12/21/17.
 */

public class ListDonarAdapter extends BaseAdapter{
    private Context mcontext;
    private List<Donar> mDonarList;

    public ListDonarAdapter(Context mcontext, List<Donar> mDonarList) {
        this.mcontext = mcontext;
        this.mDonarList = mDonarList;
    }

    @Override
    public int getCount() {
        return mDonarList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDonarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mcontext, R.layout.item_listview, null);

        TextView tvName = (TextView) v.findViewById(R.id.tv_name);
        TextView tvEmail = (TextView) v.findViewById(R.id.tv_email);
        TextView tvPA = (TextView) v.findViewById(R.id.tv_permanent_address);
        TextView tvCA = (TextView) v.findViewById(R.id.tv_current_address);
        TextView tvPhone = (TextView) v.findViewById(R.id.tv_phone_number);
        TextView tvbloodGroup = (TextView) v.findViewById(R.id.tv_bloodgroup);

        tvName.setText("Name: "+mDonarList.get(position).getName());
        tvEmail.setText("Email: "+mDonarList.get(position).getEmail());
        tvPA.setText("Permanent Address: "+mDonarList.get(position).getPermanentAddress());
        tvCA.setText("Current Address: "+mDonarList.get(position).getCurrentAddresss());
        tvPhone.setText("Phone Number: "+mDonarList.get(position).getPhoneNumber());
        tvbloodGroup.setText("Blood Group: "+mDonarList.get(position).getBloodGroup());

        return v;
    }

    public void setFilter (ArrayList<Donar> newList){
        mDonarList = new ArrayList<>();
        mDonarList.addAll(newList);
        notifyDataSetChanged();
    }
}
