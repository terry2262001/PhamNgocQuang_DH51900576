package com.example.phamngocquang_dh51900576;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.phamngocquang_dh51900576.Model.PhanLoai;
import com.example.phamngocquang_dh51900576.Model.Phim;

import java.util.ArrayList;

public class DbHelper {
    String dbName ="dbQuanLyPhim";
    String tblPhim = "tblPhim";
    String tblPhanLoai = "tblPhanLoai";

    public static final String COL_ID= "id";
    public static final String COL_NAME= "name";
    public static final String COL_TYPEID= "typeid";
    public static final String COL_IMAGE= "image";
    public static final String COL_DESC= "description";
    public static final String COL_PRICE= "price";
    Context mContext;

    public DbHelper(Context mContext) {
        this.mContext = mContext;
    }

    public SQLiteDatabase openDB(){
        return mContext.openOrCreateDatabase(dbName,Context.MODE_PRIVATE,null);
    }
    public void closeDB(SQLiteDatabase db ){db.close();    }
    public void createTablePhanLoai(){
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists "+tblPhanLoai+"(" + "" +
                "id INTEGER primary key autoincrement,"
                +"name TEXT )";
        db.execSQL(sql);
        closeDB(db);
    }

    public  void createTableProduct(){
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists " + tblPhim + "(" + "" +
                "id INTEGER primary key autoincrement,"
                +"name TEXT,"
                +"image BLOG,"
                +"description TEXT,"
                +"price TEXT,"
                +"typeid integer references "+ tblPhim +"(" +"id"+")"+")";
        db.execSQL(sql);
        closeDB(db);
    }
    public long insertPhanLoai(PhanLoai phanLoai){
        SQLiteDatabase db = openDB();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(COL_NAME, phanLoai.getTen());
        long    tmp = db.insert(tblPhanLoai,null,contentValues);
        db.close();
        return tmp;
    }
    public long insertProductData(Phim phim){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,phim.getTenphim());
        contentValues.put(COL_IMAGE,phim.getHinhanh());
        contentValues.put(COL_DESC,phim.getMota());
        contentValues.put(COL_PRICE,phim.getGia());
        contentValues.put(COL_TYPEID,phim.getPhanloaiid());
        long tmp  = db.insert(tblPhim, null,contentValues);
        db.close();
        return tmp;


    }





    public ArrayList<PhanLoai> getAllPhanLoai(){

        ArrayList<PhanLoai> mtp = new ArrayList<>();

        SQLiteDatabase db = openDB();
        // tao cau truy vam
        String sql = "SELECT * FROM "+ tblPhanLoai;
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            mtp.add(new PhanLoai(id,name));

        }
        closeDB(db);

        return mtp;

    }

    public ArrayList<Phim> getAllProduct() {
        SQLiteDatabase db = openDB();
        ArrayList<Phim> products = new ArrayList<>();
        String sql = "SELECT * FROM " + tblPhim;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String desc = cursor.getString(3);
            String price = cursor.getString(4);
            int typeid = cursor.getInt(5);
            Phim phim = new Phim(id, name, price, image, desc, typeid);
            products.add(phim);
        }
        closeDB(db);
        return products;


    }
    public ArrayList<Phim> getAllProductSQL(){

        ArrayList<Phim> products = new ArrayList<>();

        SQLiteDatabase db = openDB();
        // tao cau truy vam
        String sql =
                " SELECT "+ tblPhim+".id,"+tblPhim+".name,"+tblPhim+".image,"+tblPhim+".description,"+"tblPhim.price,"+tblPhim+".typeid,"+ tblPhanLoai+".name "
                        +" FROM " + tblPhim +
                        " INNER JOIN " + tblPhanLoai +
                        " ON " + tblPhim +".typeid = " + tblPhanLoai + ".id  ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String desc = cursor.getString(3);
            String price = cursor.getString(4);
            int typeid = cursor.getInt(5);
            String type = cursor.getString(6);
            Phim phim = new Phim(id,name,image,desc,price,typeid,type);
            products.add(phim);
        }
        closeDB(db);

        return products;

    }

    public long updateProduct(Phim newPhim){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,newPhim.getTenphim());
        contentValues.put(COL_IMAGE,newPhim.getHinhanh());
        contentValues.put(COL_DESC,newPhim.getMota());
        contentValues.put(COL_PRICE,newPhim.getGia());
        contentValues.put(COL_TYPEID,newPhim.getPhanloaiid());

        int tmp = db.update(tblPhim,contentValues, "id=?", new String[]{String.valueOf(newPhim.getMa())});
        closeDB(db);
        return  tmp;

    }
    public  int deleteProduct(int id){
        SQLiteDatabase db = openDB();
        String idDelete = String.valueOf(id);
        int tmp = db.delete(tblPhim,"id=?",new String[]{idDelete});
        closeDB(db);
        return  tmp;

    }
    public  int deleteProductType(int id){
        SQLiteDatabase db = openDB();
        String idDelete = String.valueOf(id);
        int tmp = db.delete(tblPhanLoai,"id=?",new String[]{idDelete});
        closeDB(db);
        return  tmp;

    }
    public long updateProductType(PhanLoai newPhanLoai){
        SQLiteDatabase db = openDB();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(COL_NAME, newPhanLoai.getTen());
        int tmp = db.update(tblPhanLoai,contentValues, "id=?", new String[]{String.valueOf(newPhanLoai.getId())});
        closeDB(db);
        return  tmp;

    }






}
