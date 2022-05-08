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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.quanlinhapkho.Adapter.CustomListAdapter;
import com.example.quanlinhapkho.Adapter.CustomListChiTietAdapter;
import com.example.quanlinhapkho.Model.Chitietphieunhap;
import com.example.quanlinhapkho.Model.PhieuNhap;
import com.example.quanlinhapkho.Phieunhap.addPhieumoi;
import com.example.quanlinhapkho.Phieunhap.updatePhieu;
import com.example.quanlinhapkho.database.DAUD;

import java.util.ArrayList;

public class PhieuNhapActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ImageView btnBack;
    EditText editText;
    Button them, btn_search;
    public ArrayList<PhieuNhap> list;
    DAUD db;
    String search = "";
    private ListView listView;
    private CustomListAdapter CustomAdpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_nhap);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBack = (ImageView) findViewById(R.id.btnBack);
        db = new DAUD(this);
        editText = findViewById(R.id.ed_search);
        btn_search = findViewById(R.id.btn_search);
        them = findViewById(R.id.btn_them);
        listView = findViewById(R.id.listView);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhieuNhapActivity.this, addPhieumoi.class);
                startActivity(intent);
            }
        });
        search = editText.getText().toString();
        load(search);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PhieuNhapActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.equals(""))
                {
                    list =db.lay_PhieuNhap();
                    CustomAdpter = new CustomListAdapter(PhieuNhapActivity.this,list);
                    listView.setAdapter(CustomAdpter);

                }
                ArrayList<PhieuNhap> listsearch = new ArrayList<>();
                list = db.lay_PhieuNhap();
                for(PhieuNhap phieuNhap : list){
                    try {
                        if(phieuNhap.getId() == Integer.parseInt(s)){
                            listsearch.add(phieuNhap);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                CustomAdpter = new CustomListAdapter(PhieuNhapActivity.this, listsearch);
                listView.setAdapter(CustomAdpter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_vattu)
        {
            startActivity(new Intent(PhieuNhapActivity.this, VatTuActivity.class));
        }
        if(item.getItemId() == R.id.menu_kho)
        {
            startActivity(new Intent(PhieuNhapActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void load(String searchname) {
        if (searchname.equals("")){
            list = db.lay_PhieuNhap();
            System.out.println(list.size());
            CustomAdpter = new CustomListAdapter(PhieuNhapActivity.this, list);
            listView.setAdapter(CustomAdpter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(PhieuNhapActivity.this, updatePhieu.class);
                    intent.putExtra("id",list.get(i).getId());
                    startActivity(intent);
                }
            });
        }else{
            ArrayList<PhieuNhap> listsearch = new ArrayList<>();
            list = db.lay_PhieuNhap();
            for(PhieuNhap phieuNhap : list){
                if(phieuNhap.getId() == Integer.parseInt(searchname)){
                    listsearch.add(phieuNhap);
                }
            }
            System.out.println(list.size());
            CustomAdpter = new CustomListAdapter(PhieuNhapActivity.this, listsearch);
            listView.setAdapter(CustomAdpter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(PhieuNhapActivity.this, updatePhieu.class);
                    intent.putExtra("id",list.get(i).getId());
                    startActivity(intent);
                }
            });
        }

        CustomAdpter.notifyDataSetChanged();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}