package com.example.quanlinhapkho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.quanlinhapkho.Model.Chitietphieunhap;
import com.example.quanlinhapkho.database.DAUD;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Database database;

    ListView lvKho;
    ArrayList<Kho> arrKho;
    ArrayList<Chitietphieunhap> arrPN;
    KhoAdapter adapter;
    Button btnReport, btnAdd;
    EditText edtSearch;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnAdd = (Button) findViewById(R.id.buttonPDF);
        btnReport  = (Button) findViewById(R.id.buttonReport);

        lvKho = (ListView) findViewById(R.id.listviewReport);
        arrKho = new ArrayList<>();

        adapter = new KhoAdapter(this, R.layout.dong_kho, arrKho);
        lvKho.setAdapter(adapter);

        //tao database
        database = new Database(this, "QLNK", null, 1);

        //tao bang
        //database.QueryData("DROP TABLE KHO");
        //database.QueryData("DROP TABLE PHIEUNHAP");
        database.QueryData("CREATE TABLE IF NOT EXISTS KHO(ID INTEGER PRIMARY KEY AUTOINCREMENT, TENKHO VARCHAR, DIACHI VARCHAR, HINHANH BLOB)");
        database.QueryData("CREATE TABLE IF NOT EXISTS VATTU(MAVT INTEGER PRIMARY KEY AUTOINCREMENT, TENVT VARCHAR, XUATXU VARCHAR, SOLUONG INTEGER, HINHANH VARCHAR)");
        //database.QueryData("CREATE TABLE IF NOT EXISTS PHIEUNHAP(SOPHIEU INTEGER PRIMARY KEY AUTOINCREMENT, NGAYLAP VARCHAR, MAKHO INTERGER, FOREIGN KEY (MAKHO) REFERENCES KHO(ID))");
        database.QueryData("create Table IF NOT EXISTS PhieuNhap (id INTEGER PRIMARY KEY AUTOINCREMENT,MaKho INTEGER, ngaynhap NVARCHAR(100), FOREIGN KEY (MaKho) REFERENCES KHO(ID) )");
        database.QueryData("create Table IF NOT EXISTS ChitietPhieuNhap (id INTEGER PRIMARY KEY AUTOINCREMENT,MaVatTu INTEGER,DonViTinh TEXT,soluong INTEGER, sophieunhap INTEGER,  FOREIGN KEY (sophieunhap) REFERENCES PhieuNhap(id) )");
        //database.QueryData("INSERT INTO PHIEUNHAP VALUES (null, '30/7/2021', 1)");
        //database.QueryData("DROP TABLE PhieuNhap");
        //database.QueryData("DROP TABLE ChitietPhieuNhap");
        //database.QueryData("INSERT INTO VATTU VALUES(null, 'Bút xóa', 'Thien Long', 10, 'https://anlocviet.vn/upload/product/butxoanuocthienlong-4572.jpg')");
       //database.QueryData("INSERT INTO VATTU VALUES(null, 'Vở', 'Thien Long', 12, 'https://product.hstatic.net/1000362139/product/hongha_05804_e2e77ef654554394858151595d01ffd4.jpg')");


        //select data
        GetData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });

        //them
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThemActivity.class));
            }
        });

        // Report
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, activity_reportphieunhaptheokho.class));
            }
        });
    }

    private void GetData()
    {
        Cursor data = database.GetData("SELECT * FROM KHO");
        arrKho.clear();
        while(data.moveToNext()){
            arrKho.add(new Kho(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getBlob(3)
            ));
        }
        adapter.notifyDataSetChanged();
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
                if(s.equals("") == true){
                    GetData();
                }else {
                    Cursor dataSearch = database.GetData("SELECT * FROM KHO WHERE TENKHO LIKE '%" + s.trim() +"%'");
                    arrKho.clear();
                    while(dataSearch.moveToNext()){
                        arrKho.add(new Kho(
                                dataSearch.getInt(0),
                                dataSearch.getString(1),
                                dataSearch.getString(2),
                                dataSearch.getBlob(3)
                        ));
                    }
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_vattu)
        {
            startActivity(new Intent(MainActivity.this, VatTuActivity.class));
        }
        if(item.getItemId() == R.id.menu_phieunhap)
        {
            startActivity(new Intent(MainActivity.this, PhieuNhapActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void ShowActivitySua(int id){
        Intent intent = new Intent(this, SuaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void DialogDelete(String ten, int id){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Bạn có muốn xóa kho " + ten + " không?" );
        dialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int i) {
                database.QueryData("DELETE FROM KHO WHERE ID='"+ id +"'");
                Toast.makeText(MainActivity.this, "Đã xóa"+ ten, Toast.LENGTH_SHORT).show();
                GetData();
            }
        });
        dialogDelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {

            }
        });
        dialogDelete.show();
    }
}

