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

import java.util.List;

public class SpinnerTypeAdapter extends ArrayAdapter<PhanLoai> {
    private Context context;
    private int layout;
    private List<PhanLoai> productTypes;

    public SpinnerTypeAdapter(@NonNull Context context, int rescource, @NonNull List<PhanLoai> objects) {
        super(context, 0, objects);
        this.context = context;
        this.layout = rescource;
        this.productTypes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PhanLoai phanLoai = productTypes.get(position);
        View view = LayoutInflater.from(context).inflate(layout,null);
        TextView textView   = view.findViewById(R.id.txtPLPhim);
        textView.setText(phanLoai.getTen());
       return view;

    }

}
