package com.example.quanlinhapkho.Model;

public class Chitietphieunhap {
    private  int id;
    private int sophieunhap;
    private String MaVatTu;
    private String DonViTinh;
    private int soluong;

    public Chitietphieunhap(int id, int sophieunhap, String maVatTu, String donViTinh, int soluong) {
        this.id = id;
        this.sophieunhap = sophieunhap;
        MaVatTu = maVatTu;
        DonViTinh = donViTinh;
        this.soluong = soluong;
    }

    public Chitietphieunhap() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSophieunhap() {
        return sophieunhap;
    }

    public void setSophieunhap(int sophieunhap) {
        this.sophieunhap = sophieunhap;
    }

    public String getMaVatTu() {
        return MaVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        MaVatTu = maVatTu;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        DonViTinh = donViTinh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
