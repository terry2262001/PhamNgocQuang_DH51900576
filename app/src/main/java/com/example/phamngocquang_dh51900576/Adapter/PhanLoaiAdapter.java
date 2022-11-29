package com.example.phamngocquang_dh51900576.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phamngocquang_dh51900576.Model.PhanLoai;
import com.example.phamngocquang_dh51900576.R;

import java.util.ArrayList;

public class PhanLoaiAdapter extends ArrayAdapter <PhanLoai> {
    Context mContex;
    ArrayList<PhanLoai> PhanLoaiList;

    public PhanLoaiAdapter(@NonNull Context context, @NonNull ArrayList<PhanLoai> objects) {
        super(context, 0, objects);
        this.mContex = context ;
         this.PhanLoaiList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mContex).inflate(R.layout.item_phanloai,parent,false);
        PhanLoai phanloai = PhanLoaiList.get(position);
        TextView txtPLPhim = convertView.findViewById(R.id.txtPLPhim);
        txtPLPhim.setText(phanloai.getTen());
        return convertView;



    }
}
