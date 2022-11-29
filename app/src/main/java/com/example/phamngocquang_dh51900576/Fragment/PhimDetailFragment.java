package com.example.phamngocquang_dh51900576.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.phamngocquang_dh51900576.Model.Phim;
import com.example.phamngocquang_dh51900576.R;

public class PhimDetailFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phim_detail, container, false);

       ImageView imgProduct = view.findViewById(R.id.imgProduct);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView  tvPrice = view.findViewById(R.id.tvPrice);
        TextView  tvDesc = view.findViewById(R.id.tvDesc);
        TextView  tvType = view.findViewById(R.id.tvType);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Phim phim = (Phim) bundle.getSerializable("Product");
            byte[] image = phim.getHinhanh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgProduct.setImageBitmap(bitmap);
            tvName.setText(phim.getTenphim());
            tvPrice.setText(phim.getGia());
            tvDesc.setText(phim.getMota());

            tvType.setText(phim.getPhanloaiString());


        }
        return view;
    }




}