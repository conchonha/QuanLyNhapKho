package com.example.quanlinhapkho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlinhapkho.Model.PhieuNhap;

import java.util.List;

public class AdapterDSPN extends BaseAdapter {

    private Context context;
    private int layout;
    private List<PhieuNhap> arrayList;

    public AdapterDSPN(Context context, int layout, List<PhieuNhap> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return 0;
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
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);
        PhieuNhap pn = arrayList.get(position);

        TextView maHD = view.findViewById(R.id.maPN);
        TextView ngayLap = view.findViewById(R.id.ngaylap);

        maHD.setText(String.valueOf(pn.getId()));
        maHD.setText(pn.getNgaynhap());
        return view;
    }
}
