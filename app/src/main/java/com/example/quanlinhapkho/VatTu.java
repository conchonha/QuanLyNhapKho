package com.example.quanlinhapkho;

import java.io.Serializable;

public class VatTu  implements Serializable {
    private String tenVT;
    private int soLuong;
    private int maVT;
    private long gia;
    private String hinhAnh;
    private String moTa;

    public VatTu() {

    }

    public VatTu(int maVT, String tenVT, int soLuong, long gia, String hinhAnh, String moTa) {
        this.tenVT = tenVT;
        this.soLuong = soLuong;
        this.maVT = maVT;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaVT() {
        return maVT;
    }

    public void setMaVT(int maVT) {
        this.maVT = maVT;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }


    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
