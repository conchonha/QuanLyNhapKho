package com.example.quanlinhapkho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import com.example.quanlinhapkho.Model.PhieuNhap;

public class activity_reportphieunhaptheokho extends AppCompatActivity {

    ArrayList<PhieuNhap> arrPhieuNhap;
    PhieuNhapAdapter adapter;
    RecyclerView rcvReport;
    Spinner spnCategory;
    CategoryAdapter categoryAdapter;
    Button btnPDF;
    ImageView btnBack;

    String kho_pick = "";
    String makho_pick = "";

    int pageHeight = 980;
    int pagewidth = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportphieunhaptheokho);

        btnBack = (ImageView) findViewById(R.id.btnBack);

        //spinner
        spnCategory = findViewById(R.id.spinner_category);
        categoryAdapter  = new CategoryAdapter(this, R.layout.item_selected, getListCategory());
        spnCategory.setAdapter(categoryAdapter);

        //recycleview
        rcvReport = (RecyclerView) findViewById(R.id.rcv_report);
        arrPhieuNhap = new ArrayList<>();
        adapter = new PhieuNhapAdapter(this, R.layout.item_report, arrPhieuNhap);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvReport.setLayoutManager(linearLayoutManager);

        rcvReport.setAdapter(adapter);

        GetData();

        //PDF
        btnPDF = (Button) findViewById(R.id.buttonPDF);
        btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data;
                if(makho_pick.equals("") == true)
                {
                    data = MainActivity.database.getReadableDatabase().rawQuery("SELECT * FROM PHIEUNHAP", null);
                } else {
                    data = MainActivity.database.getReadableDatabase().rawQuery("SELECT * FROM PHIEUNHAP WHERE MAKHO = '" + makho_pick + "'", null);
                }
                try {
                    data.moveToFirst();
                    createPDF(data);
                    Toast.makeText(activity_reportphieunhaptheokho.this, "Đã xuất thành công!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(activity_reportphieunhaptheokho.this, "Lỗi xuất file!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_reportphieunhaptheokho.this, MainActivity.class));
            }
        });

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(categoryAdapter.getItem(i).equals("All") == true){
                    GetData();
                    kho_pick = "Tất cả";
                    makho_pick = "";
                    adapter.notifyDataSetChanged();
                    return;
                }else {
                    kho_pick = categoryAdapter.getItem(i).trim();
                    Cursor cs = MainActivity.database.GetData("SELECT ID FROM KHO WHERE TENKHO = '" + categoryAdapter.getItem(i).trim() +"'");
                    String maKho = "";
                    while(cs.moveToNext()){
                        maKho = cs.getString(0).trim();
                    }
                    makho_pick = maKho;
                    Cursor dataSearch = MainActivity.database.GetData("SELECT * FROM PHIEUNHAP WHERE MAKHO = '" + maKho +"'");
                    arrPhieuNhap.clear();
                    while(dataSearch.moveToNext()){
                        arrPhieuNhap.add(new PhieuNhap(
                                dataSearch.getInt(0),
                                dataSearch.getString(1),
                                dataSearch.getInt(2)
                        ));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void GetData()
    {
        Cursor data = MainActivity.database.GetData("SELECT * FROM PHIEUNHAP");
        arrPhieuNhap.clear();
        while(data.moveToNext()){
            arrPhieuNhap.add(new PhieuNhap(
                    data.getInt(0),
                    data.getString(1),
                    data.getInt(2)
            ));
        }
        adapter.notifyDataSetChanged();
    }

    private List<String> getListCategory() {
        List<String> list  = new ArrayList<>();
        list.add("All");
        Cursor data = MainActivity.database.GetData("SELECT TENKHO FROM KHO");
        while(data.moveToNext()){
            list.add(data.getString(0)
            );
        }
        return list;
    }

    private void createPDF(Cursor data) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        System.out.println(pdfPath);
        File file = new File(pdfPath, "myPDF.pdf");

        PdfDocument pdfDocument = new PdfDocument();

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        myPage.getCanvas().drawText("Báo cáo phiếu nhập của kho: " + kho_pick, 90, 50, new Paint());

        myPage.getCanvas().drawText("Số phiếu", 90, 100, new Paint());
        myPage.getCanvas().drawText("Ngày lập", 175, 100, new Paint());
        myPage.getCanvas().drawText("Mã kho", 280, 100, new Paint());
        int y = 130;
        while(data.moveToNext()){
            myPage.getCanvas().drawText(String.valueOf(data.getInt(0)), 110, y, new Paint());
            myPage.getCanvas().drawText(data.getString(1), 170, y, new Paint());
            myPage.getCanvas().drawText(String.valueOf(data.getInt(2)), 295, y, new Paint());
            y+=30;
        }


        pdfDocument.finishPage(myPage);

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfDocument.close();
    }
}