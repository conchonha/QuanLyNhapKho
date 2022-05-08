package com.example.quanlinhapkho;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlinhapkho.Model.Chitietphieunhap;

import java.util.List;

public class AdapterCTHD extends RecyclerView.Adapter<AdapterCTHD.ViewHolder> {

    Context context;
    List<Chitietphieunhap> listCTHD;

    public AdapterCTHD(Context context, List<Chitietphieunhap> listCTHD) {
        this.context = context;
        this.listCTHD = listCTHD;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cthd, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listCTHD != null && listCTHD.size() > 0) {
            Chitietphieunhap cthd = listCTHD.get(position);
            holder.mavt.setText(String.valueOf(cthd.getMaVatTu()));
            holder.tenvt.setText("TTT");
            holder.soluong.setText(String.valueOf(cthd.getSoluong()));
            holder.dvt.setText(cthd.getDonViTinh());
        } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return listCTHD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mavt, tenvt, soluong, dvt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mavt = itemView.findViewById(R.id.mavt);
            tenvt = itemView.findViewById(R.id.tenvt);
            soluong = itemView.findViewById(R.id.soluong);
            dvt = itemView.findViewById(R.id.dvt);

        }
    }
}
