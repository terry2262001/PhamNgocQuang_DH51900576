package com.example.phamngocquang_dh51900576;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phamngocquang_dh51900576.Model.PhanLoai;

import java.util.Calendar;

public class them_theloaiActivity extends AppCompatActivity {
    EditText etTL;
    Calendar calendar;
    Button btnLuuPL,btSua;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_theloai);
        etTL =findViewById(R.id.etTL);
        btnLuuPL=findViewById(R.id.btnLuuPL);
        calendar=Calendar.getInstance();
        btSua = findViewById(R.id.btSua);
        dbHelper = new DbHelper(them_theloaiActivity.this);
        Intent intent = getIntent();
        
        if (intent.getSerializableExtra("phanloai") != null){
            btnLuuPL.setVisibility(View.GONE);
            btSua.setVisibility(View.VISIBLE);
            PhanLoai phanLoai = (PhanLoai) intent.getSerializableExtra("phanloai");
            etTL.setText(phanLoai.getTen());

            btSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phanLoai.setTen(etTL.getText().toString());
                    if (dbHelper.updateProductType(phanLoai) > 0){
                        Toast.makeText(them_theloaiActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(them_theloaiActivity.this, "Khong Sua Duoc ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        btnLuuPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String tenphim=etTL.getText().toString();
//                Intent returnIntent=new Intent(them_theloaiActivity.this,MainActivity.class);
                PhanLoai phanLoai = new PhanLoai(etTL.getText().toString());
                if (dbHelper.insertPhanLoai(phanLoai) > 0){
                    Toast.makeText(them_theloaiActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(them_theloaiActivity.this, "khong the them", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }
}