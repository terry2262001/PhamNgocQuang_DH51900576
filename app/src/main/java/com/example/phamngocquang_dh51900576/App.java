package com.example.phamngocquang_dh51900576;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper db;
        db = new DbHelper(this);
        db.createTablePhanLoai();
        db.createTableProduct();
    }
}
