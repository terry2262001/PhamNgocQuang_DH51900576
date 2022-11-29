package com.example.phamngocquang_dh51900576.Adapter;

import static android.os.Build.VERSION_CODES.S;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phamngocquang_dh51900576.Model.Phim;
import com.example.phamngocquang_dh51900576.R;

import java.util.ArrayList;

public class DSPhimAdapter extends ArrayAdapter<Phim> {
    Context mContex;
    ArrayList<Phim> DSPhimList;
    public DSPhimAdapter(@NonNull Context context, @NonNull ArrayList<Phim> objects) {
        super(context, 0, objects);
        this.mContex = context;
        this.DSPhimList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mContex).inflate(R.layout.item_danhsachphim,parent,false);
        Phim phim = DSPhimList.get(position);
        TextView txtTenPhim = convertView.findViewById(R.id.txtTenPhim);
        TextView txtTenPL=convertView.findViewById(R.id.txtTenPL);
        TextView txtGia=convertView.findViewById(R.id.txtGia);
        ImageView imgPhim = convertView.findViewById(R.id.imgHinh);
        byte[] image = phim.getHinhanh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        imgPhim.setImageBitmap(bitmap);

        txtTenPhim.setText(phim.getTenphim());
        txtTenPL.setText(phim.getPhanloaiString());
        txtGia.setText(phim.getGia() );
        return convertView;



    }
}
