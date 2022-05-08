package com.example.quanlinhapkho;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ThemActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText edtTenKho, edtDiaChi;
    ImageButton ibtnFolder;
    ImageView imgHinh;

    ActivityResultLauncher<String> mTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        AnhXa();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTenKho.getText().toString().length()==0 || edtDiaChi.getText().toString().length()==0)
                {
                    Toast.makeText(ThemActivity.this, "Vui lòng điền đầy đủ thông tin !" , Toast.LENGTH_SHORT).show();
                }
                else {
                    // chuyen data cua imageview sang byte[]
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG.PNG, 100, byteArray);
                    byte[] hinhAnh = byteArray.toByteArray();

                    MainActivity.database.INSERT_KHO(
                            edtTenKho.getText().toString().trim(),
                            edtDiaChi.getText().toString().trim(),
                            hinhAnh
                    );
                    Toast.makeText(ThemActivity.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ThemActivity.this, MainActivity.class));
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemActivity.this, MainActivity.class));
            }
        });

        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        imgHinh.setImageURI(result);
                    }
                }
        );

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto.launch("image/*");
            }
        });
    }

    private void AnhXa()
    {
        btnAdd = (Button) findViewById(R.id.buttonUpdate);
        btnCancel = (Button) findViewById(R.id.buttonHuy);
        edtTenKho = (EditText) findViewById(R.id.edtTenKhoSua);
        edtDiaChi = (EditText) findViewById(R.id.editTextDicChiSua);
        imgHinh = findViewById(R.id.imageViewHinhAnhSua);
        ibtnFolder = (ImageButton) findViewById(R.id.imageButtonFolderSua);
    }
}