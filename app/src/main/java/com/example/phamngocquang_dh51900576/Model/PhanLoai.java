package com.example.phamngocquang_dh51900576.Model;

import java.io.Serializable;

public class PhanLoai implements Serializable {
    private  int id;
    private  String ten;

    public PhanLoai() {
    }

    public PhanLoai(String ten) {
        this.ten = ten;
    }

    public PhanLoai(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
