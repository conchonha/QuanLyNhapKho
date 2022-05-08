package com.example.quanlinhapkho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // truy va kq: Create,insert, update, delete
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void INSERT_KHO(String ten, String diaChi, byte[] hinhAnh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO KHO VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, ten);
        statement.bindString(2, diaChi);
        statement.bindBlob(3, hinhAnh);

        statement.executeInsert();
    }

    public int UPDATE_KHO(int id, String ten, String diaChi, byte[] hinhAnh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE KHO SET TENKHO=?, DIACHI=?, HINHANH=? WHERE ID=?";
        SQLiteStatement statement = database.compileStatement(sql);
        //statement.clearBindings();

        statement.bindString(1, ten);
        statement.bindString(2, diaChi);
        statement.bindBlob(3, hinhAnh);
        statement.bindLong(4, id);

        return statement.executeUpdateDelete();
    }

    // truy va co kq: select
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

