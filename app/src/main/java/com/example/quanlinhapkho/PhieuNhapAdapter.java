package com.example.quanlinhapkho;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlinhapkho.Model.PhieuNhap;

import java.util.ArrayList;
import java.util.List;

public class PhieuNhapAdapter extends RecyclerView.Adapter<PhieuNhapAdapter.UserViewHolder> {

    private Context mContext;
    private int layout;
    private List<PhieuNhap> mListPhieuNhap;

    public PhieuNhapAdapter(Context mContext, int item_report, List<PhieuNhap> arrPhieuNhap) {
        this.mContext = mContext;
        this.layout = item_report;
        this.mListPhieuNhap = arrPhieuNhap;
    }

    public void setData(List<PhieuNhap> list) {
        this.mListPhieuNhap  = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(mListPhieuNhap != null) {
            return mListPhieuNhap.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        PhieuNhap phieuNhap = mListPhieuNhap.get(position);
        if(phieuNhap == null)
        {
            return;
        }

        holder.txtSoPhieu.setText(String.valueOf(phieuNhap.getId()));
        holder.txtNgayLap.setText(phieuNhap.getNgaynhap());
        holder.txtMakho.setText(String.valueOf(phieuNhap.getMaKho()));
    }

    public  class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView  txtSoPhieu, txtNgayLap, txtMakho;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSoPhieu = (TextView) itemView.findViewById(R.id.textviewSoPhieu);
            txtNgayLap = (TextView) itemView.findViewById(R.id.textviewNgayLap);
            txtMakho = (TextView) itemView.findViewById(R.id.textviewMaKho);
        }
    }
}
