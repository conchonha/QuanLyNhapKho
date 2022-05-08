package com.example.quanlinhapkho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DSPNActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dspnactivity);

        ListView listView;
        ArrayList<VatTu> arrayList;
        AdapterVatTu adapter;
        listView = findViewById(R.id.listviewvattu);
        arrayList = new ArrayList<>();
        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        FloatingActionButton btnReport = findViewById(R.id.report_phieunhap);
        Cursor cursor = MainActivity.database.GetData("SELECT * FROM VATTU");
        while (cursor.moveToNext()) {
            VatTu temp = new VatTu();
            temp.setHinhAnh(cursor.getString(4));
            temp.setSoLuong(cursor.getInt(3));
            temp.setMaVT(cursor.getInt(0));
            temp.setTenVT(cursor.getString(1));
            temp.setMoTa(cursor.getString(2));
            arrayList.add(temp);
        }


        adapter = new AdapterVatTu( DSPNActivity.this, R.layout.layout_vattu, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                VatTu vatTuHienTai = arrayList.get(position);
                Intent intent = new Intent();
                intent.setClass(DSPNActivity.this, CTVatTu.class);
                intent.putExtra("vatTu", vatTuHienTai);
                startActivity(intent);
            }
        });
    }
}