package com.example.quanlinhapkho.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.quanlinhapkho.Model.Chitietphieunhap;
import com.example.quanlinhapkho.Model.PhieuNhap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DAUD extends SQLiteOpenHelper {


    public DAUD(@Nullable Context context) {
        super(context, "QLNK.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlite) {
//        tạo bảng khách hàng trong cơ sở dữ liệu
        sqlite.execSQL("create Table IF NOT EXISTS PhieuNhap (id INTEGER PRIMARY KEY AUTOINCREMENT, ngaynhap NVARCHAR(100),MaKho INTEGER, FOREIGN KEY (MaKho) REFERENCES KHO(ID))");
        sqlite.execSQL("create Table IF NOT EXISTS ChitietPhieuNhap (id INTEGER PRIMARY KEY AUTOINCREMENT,MaVatTu TEXT,DonViTinh TEXT,soluong INTEGER, sophieunhap INTEGER,  FOREIGN KEY (sophieunhap) REFERENCES PhieuNhap(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlite, int i, int i1) {
        sqlite.execSQL("drop Table if exists PhieuNhap");
        sqlite.execSQL("drop Table if exists ChitietPhieuNhap");

    }

    //    Khach hang
    public boolean insertdata(PhieuNhap phieuNhap) {
        SQLiteDatabase sqlite = this.getWritableDatabase();
        String sql = "INSERT INTO PhieuNhap VALUES(null, ?, ?)";
        SQLiteStatement statement = sqlite.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, phieuNhap.getNgaynhap());
        statement.bindString(2, String.valueOf(phieuNhap.getMaKho()));

        long result = statement.executeInsert();
        if (result == -1) return false;
        else
            return true;
    }

    //id INTEGER PRIMARY KEY AUTOINCREMENT, tenbenhnhan NVARCHAR(100),sodienthoai TEXT, namsinh TEXT,gioitinh TEXT
    public PhieuNhap lay_TT_PN(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from PhieuNhap where id = '" + id + "'", null);
        if (cursor != null)
            cursor.moveToFirst();
        PhieuNhap phieuNhap = new PhieuNhap();
        phieuNhap.setId(cursor.getInt(0));
        phieuNhap.setNgaynhap(cursor.getString(1));
        phieuNhap.setMaKho(cursor.getInt(2));
        return phieuNhap;
    }

    public ArrayList<PhieuNhap> lay_PhieuNhap() {
        ArrayList<PhieuNhap> arr = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from PhieuNhap", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            PhieuNhap phieuNhap = new PhieuNhap();
            phieuNhap.setId(cursor.getInt(0));
            phieuNhap.setNgaynhap(cursor.getString(1));
            phieuNhap.setMaKho(cursor.getInt(2));
            arr.add(phieuNhap);
            cursor.moveToNext();
        }
        return arr;
    }

    public Boolean update_tt_phieunhap(PhieuNhap phieuNhap) {
        SQLiteDatabase sqlite = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaynhap", phieuNhap.getNgaynhap());
        contentValues.put("MaKho", phieuNhap.getMaKho());
        long result = sqlite.update("PhieuNhap", contentValues, "id =" + "'" + phieuNhap.getId() + "'", null);
        if (result == -1) return false;
        else
            return true;
    }

    public void delectphieunhap(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("PhieuNhap", "id = " + "'" + id + "'", null);

    }

    public boolean insertChiTiet(Chitietphieunhap tt) {
        SQLiteDatabase sqlite = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sophieunhap", tt.getSophieunhap());
        contentValues.put("MaVatTu", tt.getMaVatTu());
        contentValues.put("DonViTinh", tt.getDonViTinh());
        contentValues.put("soluong", tt.getSoluong());
        long result = sqlite.insert("ChitietPhieuNhap", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean update_Chitiet(Chitietphieunhap tt) {
        SQLiteDatabase sqlite = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaVatTu", tt.getMaVatTu());
        contentValues.put("DonViTinh", tt.getDonViTinh());
        contentValues.put("soluong", tt.getSoluong());
        long result = sqlite.update("ChitietPhieuNhap", contentValues, "id =" + "'" + tt.getId() + "'", null);
        if (result == -1) return false;
        else
            return true;
    }

    public Chitietphieunhap lay_TT(int idtoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from ChitietPhieuNhap where id = '" + idtoa + "'", null);
        if (cursor != null)
            cursor.moveToFirst();
        Chitietphieunhap tt = new Chitietphieunhap();
        tt.setId(cursor.getInt(0));
        tt.setSophieunhap(cursor.getInt(1));
        tt.setMaVatTu(cursor.getString(2));
        tt.setDonViTinh(cursor.getString(3));
        tt.setSoluong(cursor.getInt(4));
        return tt;
    }

    public Set<String> getListMaVatTu() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select MaVatTu from ChitietPhieuNhap", null);
        Set<String> list = new HashSet<>();
        if (cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        Log.d("SangTB", "getListMaVatTu: " + list);
        return list;
    }

    public ArrayList<Chitietphieunhap> lay_fullchitiet(int idphieunhap) {
        ArrayList<Chitietphieunhap> arr = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from ChitietPhieuNhap where sophieunhap = '" + idphieunhap + "'", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Chitietphieunhap tt = new Chitietphieunhap();
            tt.setId(cursor.getInt(0));
            tt.setSophieunhap(cursor.getInt(1));
            tt.setMaVatTu(cursor.getString(2));
            tt.setDonViTinh(cursor.getString(3));
            tt.setSoluong(cursor.getInt(4));
            arr.add(tt);
            cursor.moveToNext();
        }
        return arr;
    }

    public void delectchitiet(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ChitietPhieuNhap", "id = " + "'" + id + "'", null);

    }
}

