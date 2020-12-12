package com.example.api_crud_mock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.android.volley.toolbox.JsonArrayRequest;

import java.util.ArrayList;

public class CustomUsersAdapter extends BaseAdapter {
    Context ctx;
    int layoutItem;
    ArrayList<User> arrayList;

    public CustomUsersAdapter(Context ctx, int layoutItem, ArrayList<User> arrayList) {
        this.ctx = ctx;
        this.layoutItem = layoutItem;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(ctx).inflate(layoutItem, viewGroup, false);

        TextView txtId = view.findViewById(R.id.txtId);
        TextView txtFname = view.findViewById(R.id.txtFName);
        TextView txtLname = view.findViewById(R.id.txtLName);
        TextView txtG = view.findViewById(R.id.txtG);
        TextView txtS= view.findViewById(R.id.txtS);

        txtId.setText(arrayList.get(i).getId());
        txtFname.setText(arrayList.get(i).getFIRSTNAME());
        txtLname.setText(arrayList.get(i).getLASTNAME());
        txtG.setText(arrayList.get(i).getGENDER());
        txtS.setText(arrayList.get(i).getSALARY());

        return view;
    }
}
