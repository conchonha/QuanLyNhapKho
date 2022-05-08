package com.example.quanlinhapkho;

public class Kho {
    private int ID;
    private String TenKho;
    private String DiaChi;
    private byte[] HinhAnh;

    public Kho(int ID, String tenKho, String diaChi, byte[] hinhAnh) {
        this.ID = ID;
        TenKho = tenKho;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
