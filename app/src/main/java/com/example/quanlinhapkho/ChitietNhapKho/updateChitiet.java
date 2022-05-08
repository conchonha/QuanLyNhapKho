package com.example.quanlinhapkho.ChitietNhapKho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlinhapkho.Model.Chitietphieunhap;
import com.example.quanlinhapkho.R;
import com.example.quanlinhapkho.database.DAUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class updateChitiet extends AppCompatActivity {
    private static final String MAVATU = "Chọn Mã Vật Tư";
    EditText soluong, donvitinh;
    Button btn_add, btn_delete;
    int id, chitietid;
    DAUD db;
    Spinner mavattu;
    Set<String> listMaVatTu;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_chitiet);
        db = new DAUD(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        mavattu = findViewById(R.id.spinerMaVatTu);
        soluong = findViewById(R.id.ed_SoLuong);
        donvitinh = findViewById(R.id.ed_DonViTinh);
        btn_add = findViewById(R.id.button);
        btn_delete = findViewById(R.id.button2);

        //get list mã vật tư và set vào spinner
        listMaVatTu = db.getListMaVatTu();
        if (listMaVatTu == null) {
            listMaVatTu.add(MAVATU);
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                new ArrayList<>(listMaVatTu)
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mavattu.setAdapter(adapter);

        isEmpty(soluong);
        isEmpty(donvitinh);
        Chitietphieunhap chitietphieunhap = new Chitietphieunhap();
        chitietphieunhap = db.lay_TT(id);
        System.out.println();
        chitietid = chitietphieunhap.getId();
        soluong.setText(chitietphieunhap.getSoluong() + "");
        donvitinh.setText(chitietphieunhap.getDonViTinh());
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = adapter.getItem(mavattu.getSelectedItemPosition());
                Log.d("SangTB", "onClick: "+value);
                if(value == MAVATU){
                    Toast.makeText(updateChitiet.this, "Mã Vật Tư Trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                Chitietphieunhap chitietphieunhap = new Chitietphieunhap();
                chitietphieunhap.setMaVatTu(value);
                chitietphieunhap.setSoluong(Integer.parseInt(soluong.getText().toString()));
                chitietphieunhap.setDonViTinh(donvitinh.getText().toString());
                chitietphieunhap.setId(chitietid);
                db.update_Chitiet(chitietphieunhap);
                Intent intent = new Intent(updateChitiet.this, ChitietNhapActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delectchitiet(chitietid);
                Intent intent = new Intent(updateChitiet.this, ChitietNhapActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }

    private boolean isEmpty(EditText etText) {
        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etText.getText().toString().trim().length() == 0) {
                    etText.setError("Không thể để trống");
                    etText.findFocus();
                    btn_add.setEnabled(false);
                    return;

                } else {
                    btn_add.setEnabled(true);
                }
            }
        });
        return true;
    }
}