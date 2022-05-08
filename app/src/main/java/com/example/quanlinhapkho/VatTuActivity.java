package com.example.quanlinhapkho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VatTuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_tu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView;
        ArrayList<VatTu> arrayList;
        AdapterVatTu adapter;
        ImageView btnBack = (ImageView) findViewById(R.id.btnBack);
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



        adapter = new AdapterVatTu( VatTuActivity.this, R.layout.layout_vattu, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                VatTu vatTuHienTai = arrayList.get(position);
                Intent intent = new Intent();
                intent.setClass(VatTuActivity.this, CTVatTu.class);
                intent.putExtra("vatTu", vatTuHienTai);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(VatTuActivity.this, CTVatTu.class);
                startActivity(intent);
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(VatTuActivity.this, DSPNActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VatTuActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
////                if(s.equals("") == true){
////                    GetData();
////                }else {
////                    Cursor dataSearch = database.GetData("SELECT * FROM KHO WHERE TENKHO LIKE '%" + s.trim() +"%'");
////                    arrKho.clear();
////                    while(dataSearch.moveToNext()){
////                        arrKho.add(new Kho(
////                                dataSearch.getInt(0),
////                                dataSearch.getString(1),
////                                dataSearch.getString(2),
////                                dataSearch.getBlob(3)
////                        ));
////                    }
////                    adapter.notifyDataSetChanged();
////                }
//                return false;
//            }
//        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_kho)
        {
            startActivity(new Intent(VatTuActivity.this, MainActivity.class));
        }
        if(item.getItemId() == R.id.menu_phieunhap)
        {
            startActivity(new Intent(VatTuActivity.this, PhieuNhapActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}