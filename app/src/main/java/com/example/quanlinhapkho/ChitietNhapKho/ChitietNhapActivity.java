package com.example.quanlinhapkho.ChitietNhapKho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlinhapkho.Adapter.CustomListAdapter;
import com.example.quanlinhapkho.Adapter.CustomListChiTietAdapter;
import com.example.quanlinhapkho.MainActivity;
import com.example.quanlinhapkho.Model.Chitietphieunhap;
import com.example.quanlinhapkho.Model.PhieuNhap;
import com.example.quanlinhapkho.Phieunhap.addPhieumoi;
import com.example.quanlinhapkho.Phieunhap.updatePhieu;
import com.example.quanlinhapkho.R;
import com.example.quanlinhapkho.database.DAUD;

import java.util.ArrayList;

public class ChitietNhapActivity extends AppCompatActivity {
    Button them, taotoa;
    TextView idPhieuNhap;
    private ArrayList<Chitietphieunhap> list;
    DAUD db;
    private ListView listView;
    private CustomListChiTietAdapter CustomAdpter;
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_nhap);
        idPhieuNhap = findViewById(R.id.textView6);
        db = new DAUD(this);
        Intent intent = getIntent();
        id =intent.getIntExtra("id", 0);
        idPhieuNhap.setText("Phiếu Nhập : " + id);
        them = findViewById(R.id.btn_them);
        listView = findViewById(R.id.listView);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChitietNhapActivity.this, addChitiet.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        load();
    }
    public void load() {
        list = db.lay_fullchitiet(id);
        System.out.println(list.size());
        CustomAdpter = new CustomListChiTietAdapter(ChitietNhapActivity.this, list);
        listView.setAdapter(CustomAdpter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ChitietNhapActivity.this, updateChitiet.class);
                intent.putExtra("id",list.get(i).getId());
                startActivity(intent);
            }
        });
        CustomAdpter.notifyDataSetChanged();
    }
}