package com.example.phamngocquang_dh51900576.Model;

import java.io.Serializable;

public class Phim implements Serializable {
    private  int ma;
    private String tenphim;
    private String gia;
    private byte[] hinhanh;
    private String mota;
    private int phanloaiid;
    private String phanloaiString;
    public Phim() {
    }

    public Phim(int ma, String tenphim, byte[] hinhanh, String mota, String gia, int phanloaiid, String phanloaiString) {
        this.ma = ma;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.gia = gia;
        this.phanloaiid = phanloaiid;
        this.phanloaiString = phanloaiString;
    }


    public Phim(String tenphim,byte[] hinhanh ,String gia,String mota, int phanloaiid) {
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.gia = gia;
        this.mota = mota;
        this.phanloaiid = phanloaiid;
    }

    public Phim(int ma, String tenphim, String gia, byte[] hinhanh, String mota, int phanloaiid) {
        this.ma = ma;
        this.tenphim = tenphim;
        this.gia = gia;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.phanloaiid = phanloaiid;
    }

    public Phim(String tenphim, String gia, byte[] imagetoByte, int id) {
    }

    public int getMa() {
        return ma;
    }

    public Phim(String tenphim, String gia, String mota, int phanloaiid) {
        this.tenphim = tenphim;
        this.gia = gia;
        this.mota = mota;
        this.phanloaiid = phanloaiid;
    }

    public String getPhanloaiString() {
        return phanloaiString;
    }

    public void setPhanloaiString(String phanloaiString) {
        this.phanloaiString = phanloaiString;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getPhanloaiid() {
        return phanloaiid;
    }

    public void setPhanloaiid(int phanloaiid) {
        this.phanloaiid = phanloaiid;
    }

    @Override
    public String toString() {
        return  ma +tenphim +  gia + hinhanh.toString()+ mota +  phanloaiid ;
    }
}
