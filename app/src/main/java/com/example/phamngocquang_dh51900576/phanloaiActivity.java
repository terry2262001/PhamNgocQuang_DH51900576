package com.example.phamngocquang_dh51900576;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phamngocquang_dh51900576.Adapter.PhanLoaiAdapter;
import com.example.phamngocquang_dh51900576.Model.PhanLoai;

import java.util.ArrayList;

public class phanloaiActivity extends AppCompatActivity {
    Button btnThemTL;
    ListView lsPL;
    PhanLoaiAdapter plAdapter;
    ArrayList<PhanLoai> plList;
    DbHelper dbHelper;
    PhanLoai phanLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanloai);
        btnThemTL = findViewById(R.id.btnThemTL);
        dbHelper = new DbHelper(phanloaiActivity.this);
        lsPL = findViewById(R.id.lsPL);
        plList = new ArrayList<>();
        plAdapter = new PhanLoaiAdapter(phanloaiActivity.this,plList);
        lsPL.setAdapter(plAdapter);
        init();
        registerForContextMenu(lsPL);

        btnThemTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(phanloaiActivity.this,them_theloaiActivity.class));


            }
        });
        lsPL.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                phanLoai = plList.get(i);
                return false;
            }
        });
    }
    public void init(){
        plList.clear();
        plList.addAll(dbHelper.getAllPhanLoai());
        plAdapter.notifyDataSetChanged();
    }
    

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.mnu_sua:
                Intent intent = new Intent(phanloaiActivity.this, them_theloaiActivity.class);
                intent.putExtra("phanloai",phanLoai);
                startActivity(intent);
                break;
            case  R.id.mnu_xoa:
                if (dbHelper.deleteProductType(phanLoai.getId())> 0){
                    Toast.makeText(this, "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
                    init();
                }else {
                    Toast.makeText(this, "Khong Xoa Duoc ", Toast.LENGTH_SHORT).show();
                }
                break;
                
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}