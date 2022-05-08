package com.example.quanlinhapkho.Model;

public class PhieuNhap {
    private int  id;
    private String ngaynhap;
    private int MaKho;

    public PhieuNhap(int id, String ngaynhap, int maKho) {
        this.id = id;
        this.ngaynhap = ngaynhap;
        MaKho = maKho;
    }

    public PhieuNhap() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getMaKho() {
        return MaKho;
    }

    public void setMaKho(int maKho) {
        MaKho = maKho;
    }
}
