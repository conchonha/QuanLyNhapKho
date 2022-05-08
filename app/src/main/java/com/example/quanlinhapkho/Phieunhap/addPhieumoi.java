package com.example.quanlinhapkho.Phieunhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlinhapkho.MainActivity;
import com.example.quanlinhapkho.Model.PhieuNhap;
import com.example.quanlinhapkho.PhieuNhapActivity;
import com.example.quanlinhapkho.R;
import com.example.quanlinhapkho.database.DAUD;

public class addPhieumoi extends AppCompatActivity {
EditText ed_ngay,ed_makho;
Button btn_add;
    DAUD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phieumoi);
        db = new DAUD(this);
        ed_ngay = findViewById(R.id.ed_NgayNhap);
        ed_makho = findViewById(R.id.ed_MaKho);
        btn_add = findViewById(R.id.btn_add);
        isEmpty(ed_makho);
        isEmpty(ed_ngay);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuNhap phieuNhap = new PhieuNhap();
                phieuNhap.setMaKho(Integer.valueOf(ed_makho.getText().toString().trim()));
                phieuNhap.setNgaynhap(ed_ngay.getText().toString());
                db.insertdata(phieuNhap);
                Intent intent = new Intent(addPhieumoi.this, PhieuNhapActivity.class);
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