package com.example.phamngocquang_dh51900576.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.phamngocquang_dh51900576.Adapter.SpinnerTypeAdapter;
import com.example.phamngocquang_dh51900576.DbHelper;
import com.example.phamngocquang_dh51900576.Model.PhanLoai;
import com.example.phamngocquang_dh51900576.Model.Phim;
import com.example.phamngocquang_dh51900576.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class UpdatePhimFragment extends Fragment {
    EditText etTenPhim;
    EditText etGia,etMoTa;
    ImageView imgeHinhanh;
    Button btUpdate;
    final int IMAGE_REQUEST = 0;
    Uri imageUri;
    Spinner product_type;
    SpinnerTypeAdapter spinnerTypeAdapter;
    DbHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_update_phim, container, false);
       etTenPhim = view.findViewById(R.id.etTenPhim);
        etGia = view.findViewById(R.id.etGia);
        etMoTa = view.findViewById(R.id.etMoTa);
        btUpdate = view.findViewById(R.id.btUpdate);
        imgeHinhanh = view.findViewById(R.id.imgeHinhanh);
        dbHelper = new DbHelper(getContext());
        product_type = view.findViewById(R.id.product_type);
        loadData();
        Bundle bundle = this.getArguments();
        if (bundle != null){
            Phim phim = (Phim) bundle.getSerializable("PhimUpdate");
            product_type.setSelection(phim.getPhanloaiid()-1);

            etTenPhim.setText(phim.getTenphim());
            etMoTa.setText(phim.getMota());
            etGia.setText(phim.getGia());

            byte[] image = phim.getHinhanh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgeHinhanh.setImageBitmap(bitmap);





            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phim.setTenphim(etTenPhim.getText().toString());
                    phim.setGia(etGia.getText().toString());
                    phim.setMota(etMoTa.getText().toString());
                    phim.setHinhanh(ImagetoByte(imgeHinhanh));
                    PhanLoai phanLoai = (PhanLoai) product_type.getSelectedItem();
                    phim.setPhanloaiid(phanLoai.getId());
                    if (dbHelper.updateProduct(phim)> 0){
                        Toast.makeText(getContext(), "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frg_container, new ListPhimFragment()).commit();
                    }else {
                        Toast.makeText(getContext(), "Khong The Sua", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


       return view;
    }
    private byte[] ImagetoByte(ImageView imgProduct) {
        Bitmap bitmap = ((BitmapDrawable)imgProduct.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }
    public void loadData() {
        ArrayList<PhanLoai> productTypes = dbHelper.getAllPhanLoai();
        if (!productTypes.isEmpty()) {

            spinnerTypeAdapter = new SpinnerTypeAdapter(getContext(), R.layout.item_phanloai, productTypes);
            product_type.setAdapter(spinnerTypeAdapter);
            spinnerTypeAdapter.notifyDataSetChanged();

        }
    }

}