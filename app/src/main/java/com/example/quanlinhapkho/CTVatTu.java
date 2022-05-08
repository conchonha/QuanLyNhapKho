package com.example.quanlinhapkho;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class CTVatTu extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ct_vattu);
        VatTu vatTu = (VatTu) getIntent().getSerializableExtra("vatTu");

        TextView textV1 = this.findViewById(R.id.name);
        TextView textV2 = this.findViewById(R.id.mota);
        TextView textV4 = this.findViewById(R.id.maVT);
        TextView textV5 = this.findViewById(R.id.soluong);
        TextView textV7 = this.findViewById(R.id.imglink);
        ImageView imageV = this.findViewById(R.id.imageHinh);
        Button luu = this.findViewById(R.id.savebtn);
        Button xoa = this.findViewById(R.id.deletebtn);


        if(vatTu != null) {
            textV1.setText(vatTu.getTenVT());
            textV2.setText(vatTu.getMoTa());
            textV4.setText(String.valueOf(vatTu.getMaVT()));
            textV5.setText(String.valueOf(vatTu.getSoLuong()));
            Picasso.get().load(vatTu.getHinhAnh()).into(imageV);
            String imgUrl = vatTu.getHinhAnh();
            textV7.setText(imgUrl);
        } else {
            xoa.setText("Huy");
        }
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((textV1.getText().length() != 0) && (textV2.getText().length() != 0) && (textV5.getText().length() != 0) && (textV7.getText().length() != 0)) {
                    if(vatTu == null) {
                        String sql = "INSERT INTO VATTU VALUES(null, '"+textV1.getText()+"', '"+textV2.getText()+"', "+textV5.getText()+", '" +textV7.getText()+"')";
                        MainActivity.database.QueryData(sql);
                    } else {
                        MainActivity.database.QueryData("UPDATE VATTU SET TENVT ='" +textV1.getText()+ "' , XUATXU='" +textV2.getText()+
                                "' , SOLUONG=" +textV5.getText()+" , HINHANH= '" +textV7.getText()+ "' WHERE MAVT ="+ textV4.getText());
                    }
                    Intent intent = new Intent();
                    intent.setClass(CTVatTu.this, VatTuActivity.class);
                    startActivity(intent);
                } //else hien thong bao
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vatTu != null) {
                    MainActivity.database.QueryData("DELETE FROM VATTU WHERE MAVT ="+ textV4.getText());
                }
                Intent intent = new Intent();
                intent.setClass(CTVatTu.this, VatTuActivity.class);
                startActivity(intent);
            }
        });

    }
}
