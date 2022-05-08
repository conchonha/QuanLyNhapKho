package com.example.quanlinhapkho;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class SuaActivity extends AppCompatActivity {

    Button btnUpdate, btnHuy;
    EditText edtTenKho, edtDiaChi;
    ImageButton ibtnFolder;
    ImageView imgHinh;
    int id;

    ActivityResultLauncher<String> mTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);

        AnhXa();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getInt("ID", 0);
        }

        setUp(id);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTenKho.getText().toString().length() == 0 || edtDiaChi.getText().toString().length() == 0) {
                    Toast.makeText(SuaActivity.this, "Vui lòng điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    // chuyen data cua imageview sang byte[]
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG.PNG, 100, byteArray);
                    byte[] hinhAnh = byteArray.toByteArray();

                    int kq = MainActivity.database.UPDATE_KHO(
                            id,
                            edtTenKho.getText().toString().trim(),
                            edtDiaChi.getText().toString().trim(),
                            hinhAnh
                    );
                    if (kq == 1) {
                        Toast.makeText(SuaActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SuaActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(SuaActivity.this, MainActivity.class));
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SuaActivity.this, MainActivity.class));
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
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        btnHuy = (Button) findViewById(R.id.buttonHuy);
        edtTenKho = (EditText) findViewById(R.id.edtTenKhoSua);
        edtDiaChi = (EditText) findViewById(R.id.editTextDicChiSua);
        imgHinh = findViewById(R.id.imageViewHinhAnhSua);
        ibtnFolder = (ImageButton) findViewById(R.id.imageButtonFolderSua);
    }

    private void setUp(int id){
        Cursor data = MainActivity.database.GetData("SELECT * FROM KHO WHERE ID =" + id);
        while(data.moveToNext()){
            edtTenKho.setText(data.getString(1));
            edtDiaChi.setText(data.getString(2));
            Bitmap bitmap = BitmapFactory.decodeByteArray(data.getBlob(3), 0, data.getBlob(3).length);
            imgHinh.setImageBitmap(bitmap);
        }
    }
}