package com.example.phamngocquang_dh51900576;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phamngocquang_dh51900576.Adapter.SpinnerTypeAdapter;
import com.example.phamngocquang_dh51900576.Model.PhanLoai;
import com.example.phamngocquang_dh51900576.Model.Phim;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class them_dsActivity extends AppCompatActivity {
    EditText etTenPhim;
    EditText etTenPL;
    EditText etGia,etMoTa;
    ImageView imgeHinhanh;
    Button btnLuu;
    Calendar calendar;
    final int IMAGE_REQUEST = 0;
    Uri imageUri;
    Spinner product_type;
    SpinnerTypeAdapter spinnerTypeAdapter;
    DbHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ds);
        dbHelper = new DbHelper(them_dsActivity.this);

        etTenPhim = findViewById(R.id.etTenPhim);
        etMoTa = findViewById(R.id.etMoTa);

        etGia = findViewById(R.id.etGia);
        imgeHinhanh = findViewById(R.id.imgeHinhanh);
        btnLuu = findViewById(R.id.btnLuu);
        calendar = Calendar.getInstance();
        product_type = findViewById(R.id.product_type);

        imgeHinhanh.setOnClickListener(view -> openImage());

        loadData();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String tenphim = etTenPhim.getText().toString();
                    String gia = etGia.getText().toString();
                    String mota = etMoTa.getText().toString();
                    PhanLoai phanLoai = (PhanLoai) product_type.getSelectedItem();
                    Phim phim = new Phim(tenphim,ImagetoByte(imgeHinhanh),gia,mota,phanLoai.getId());

                    //  Intent returnIntent = new Intent(them_dsActivity.this, MainActivity.class);

                    finish();
                    if (dbHelper.insertProductData(phim) >0){
                        Toast.makeText(them_dsActivity.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(them_dsActivity.this, "Khong Them Duoc ", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e ){
                    Toast.makeText(them_dsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        try {
            Intent intent = getIntent();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    //    Intent intent = getIntent();
//        if (intent.getSerializableExtra("phimedit") != null){
//            Phim  phim = (Phim) intent.getSerializableExtra("phimedit");
//            etTenPhim.setText(phim.getTenphim());
//            etMoTa.setText(phim.getMa());
//            etGia.setText(phim.getGia());
//            product_type.setSelection(phim.getPhanloaiid()-1);
//            byte[] image = phim.getHinhanh();
//            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
//            imgeHinhanh.setImageBitmap(bitmap);
//
//        }
    }


        public void loadData() {
        ArrayList<PhanLoai> phanLoai = dbHelper.getAllPhanLoai();

        if (!phanLoai.isEmpty()) {

            spinnerTypeAdapter = new SpinnerTypeAdapter(them_dsActivity.this, R.layout.item_phanloai, phanLoai);
            product_type.setAdapter(spinnerTypeAdapter);
            spinnerTypeAdapter.notifyDataSetChanged();

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgeHinhanh.setImageURI(imageUri);
        }
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);

    }

    private byte[] ImagetoByte(ImageView imgProduct) {
        Bitmap bitmap = ((BitmapDrawable) imgProduct.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }
}
