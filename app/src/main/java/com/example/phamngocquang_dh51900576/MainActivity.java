package com.example.phamngocquang_dh51900576;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phamngocquang_dh51900576.Adapter.DSPhimAdapter;
import com.example.phamngocquang_dh51900576.Adapter.PhanLoaiAdapter;
import com.example.phamngocquang_dh51900576.Fragment.ListPhimFragment;
import com.example.phamngocquang_dh51900576.Model.PhanLoai;
import com.example.phamngocquang_dh51900576.Model.Phim;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnDSPhim;
    Button btnTLPhim;
    DSPhimAdapter dsphimAdapter;
    PhanLoaiAdapter phanloaiAdapter;
    ArrayList<Phim> danhsachList;
    ArrayList<PhanLoai> phanloaiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        danhsachList = new ArrayList<>();
        phanloaiList = new ArrayList<>();
        dsphimAdapter = new DSPhimAdapter(MainActivity.this, danhsachList);

        phanloaiAdapter = new PhanLoaiAdapter(MainActivity.this, phanloaiList);

//        btnDSPhim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,danhsachphimActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
//        btnTLPhim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,phanloaiActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frg_container,new ListPhimFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_option,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_them:
                startActivity(new Intent(MainActivity.this,them_dsActivity.class));
                break;
            case  R.id.mnu_phanloai:
                startActivity(new Intent(MainActivity.this,phanloaiActivity.class));
                break;
            case R.id.mnu_trove:
                break;
            case R.id.mnu_thongtin:
                break;
            case R.id.mnu_phim:
                getSupportFragmentManager().beginTransaction().replace(R.id.frg_container, new ListPhimFragment()).commit();
        }
        return super.onOptionsItemSelected(item);
    }
}