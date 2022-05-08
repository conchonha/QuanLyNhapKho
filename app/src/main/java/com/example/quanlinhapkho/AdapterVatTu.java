package com.example.quanlinhapkho;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterVatTu extends BaseAdapter {
    private Context context;
    private int layout;
    private List<VatTu> arrayList;

    public AdapterVatTu(Context context, int layout, List<VatTu> arrayList) {
        this.context = context;
        this.layout = layout;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        VatTu vatTu = arrayList.get(position);

        TextView textV1 = convertView.findViewById(R.id.name);
        TextView textV2 = convertView.findViewById(R.id.mota);
        TextView textV4 = convertView.findViewById(R.id.maVT);
        TextView textV5 = convertView.findViewById(R.id.soluong);
        TextView textV6 = convertView.findViewById(R.id.donvi);

        ImageView imageV = convertView.findViewById(R.id.imageHinh);

        textV1.setText(vatTu.getTenVT());
        textV2.setText(vatTu.getMoTa());
        textV4.setText(String.valueOf(vatTu.getMaVT()));
        textV5.setText(String.valueOf(vatTu.getSoLuong()));
//        imageV.setImageResource(vatTu.getHinhAnh());
        Picasso.get().load(vatTu.getHinhAnh()).into(imageV);
        return convertView;
    }
}
