package com.example.quanlinhapkho;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class KhoAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<Kho> khoList;

    public KhoAdapter(MainActivity context, int layout, List<Kho> khoList) {
        this.context = context;
        this.layout = layout;
        this.khoList = khoList;
    }

    @Override
    public int getCount() {
        return khoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTen, txtDiaChi;
        ImageView imgHinhAnh, imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTen = (TextView) view.findViewById(R.id.textviewTen);
            holder.txtDiaChi = (TextView) view.findViewById(R.id.textviewDiaChi);
            holder.imgHinhAnh = (ImageView) view.findViewById(R.id.imageviewHinhAnh);
            holder.imgDelete  = (ImageView) view.findViewById(R.id.imageviewDelete);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageviewEdit);
            view.setTag(holder);
        }else {
            holder  = (ViewHolder) view.getTag();
        }

        Kho kho = khoList.get(i);

        holder.txtTen.setText(kho.getTenKho());
        holder.txtDiaChi.setText(kho.getDiaChi());
        //chuyen byte[] ve bitmap
        byte[] hinhAnh = kho.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 ,hinhAnh.length);
        holder.imgHinhAnh.setImageBitmap(bitmap);

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.ShowActivitySua(kho.getID());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDelete(kho.getTenKho(), kho.getID());
            }
        });

        return view;
    }

}
