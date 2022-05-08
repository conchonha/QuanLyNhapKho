package com.example.quanlinhapkho.ChitietNhapKho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlinhapkho.MainActivity;
import com.example.quanlinhapkho.Model.Chitietphieunhap;
import com.example.quanlinhapkho.Phieunhap.addPhieumoi;
import com.example.quanlinhapkho.R;
import com.example.quanlinhapkho.database.DAUD;

public class addChitiet extends AppCompatActivity {
    EditText mavattu,soluong,donvitinh;
    Button btn_add;
    int id;
    DAUD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chitiet);
        Intent intent = getIntent();
        db = new DAUD(this);
        id =intent.getIntExtra("id", 0);
        mavattu = findViewById(R.id.ed_MavatTu);
        soluong = findViewById(R.id.ed_SoLuong);
        donvitinh = findViewById(R.id.ed_DonViTinh);
        isEmpty(mavattu);
        isEmpty(soluong);
        isEmpty(donvitinh);
        btn_add = findViewById(R.id.button);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chitietphieunhap chitietphieunhap = new Chitietphieunhap();
                chitietphieunhap.setMaVatTu(mavattu.getText().toString());
                chitietphieunhap.setSoluong(Integer.parseInt(soluong.getText().toString()));
                chitietphieunhap.setDonViTinh(donvitinh.getText().toString());
                chitietphieunhap.setSophieunhap(id);
                db.insertChiTiet(chitietphieunhap);
                Intent intent = new Intent(addChitiet.this, ChitietNhapActivity.class);
                intent.putExtra("id",id);
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