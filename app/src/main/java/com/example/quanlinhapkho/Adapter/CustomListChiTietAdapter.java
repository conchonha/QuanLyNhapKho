package com.example.quanlinhapkho.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlinhapkho.Model.Chitietphieunhap;
import com.example.quanlinhapkho.Model.PhieuNhap;
import com.example.quanlinhapkho.R;

import java.util.ArrayList;


public class CustomListChiTietAdapter extends BaseAdapter {

    private ArrayList<Chitietphieunhap> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListChiTietAdapter(Context aContext, ArrayList<Chitietphieunhap> listData) {
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
            convertView = layoutInflater.inflate(R.layout.viewuserchitiet, null);
            holder = new ViewHolder();
            holder.sophieu = (TextView) convertView.findViewById(R.id.textView_SoPhieu);
            holder.ngay = (TextView) convertView.findViewById(R.id.textView_ngaynhap);
            holder.mahopdong = (TextView) convertView.findViewById(R.id.txtMaKhachHang);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Chitietphieunhap BN = this.listData.get(position);
        holder.sophieu.setText("Mã Vật Tư : "+BN.getMaVatTu()+"");
        holder.ngay.setText("Số Lượng Nhập: "+BN.getSoluong() + "");
        holder.mahopdong.setText("Đơn Vị Tính :" + BN.getDonViTinh());

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    static class ViewHolder {
        TextView sophieu,ngay,mahopdong;
    }

}


