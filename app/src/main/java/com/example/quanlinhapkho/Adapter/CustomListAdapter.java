package com.example.quanlinhapkho.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlinhapkho.Model.PhieuNhap;
import com.example.quanlinhapkho.R;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {

    private ArrayList<PhieuNhap> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, ArrayList<PhieuNhap> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.viewuser, null);
            holder = new ViewHolder();
            holder.sophieu = (TextView) convertView.findViewById(R.id.textView_SoPhieu);
            holder.ngay = (TextView) convertView.findViewById(R.id.textView_ngaynhap);
            holder.mahopdong = (TextView) convertView.findViewById(R.id.txtMaKhachHang);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PhieuNhap BN = this.listData.get(position);
        holder.sophieu.setText("Số Phiếu : "+BN.getId()+"");
        holder.ngay.setText("Ngày Nhập: "+BN.getNgaynhap());
        holder.mahopdong.setText("Mã Kho :" + BN.getMaKho());

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    static class ViewHolder {
        TextView sophieu,ngay,mahopdong;
    }

}


