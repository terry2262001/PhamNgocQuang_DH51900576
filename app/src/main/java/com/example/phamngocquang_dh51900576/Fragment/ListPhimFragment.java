package com.example.phamngocquang_dh51900576.Fragment;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phamngocquang_dh51900576.Adapter.DSPhimAdapter;
import com.example.phamngocquang_dh51900576.DbHelper;
import com.example.phamngocquang_dh51900576.Model.Phim;
import com.example.phamngocquang_dh51900576.R;

import java.util.ArrayList;


public class ListPhimFragment extends Fragment  {
    DSPhimAdapter dsPhimAdapter;
    ArrayList<Phim> dsPhim;
    ListView lsDSPhim;
    DbHelper dbHelper;
    int position;
    Phim phimL;
    Phim phimN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_phim, container, false);
        lsDSPhim = view.findViewById(R.id.lsDSPhim);
        dbHelper = new DbHelper(getContext());
        dsPhim = new ArrayList<>();
        dsPhimAdapter = new DSPhimAdapter(getContext(), dsPhim);
        lsDSPhim.setAdapter(dsPhimAdapter);
        init();
        lsDSPhim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Phim phim = dsPhim.get(i);
                PhimDetailFragment prodFragment = new PhimDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Product", phim);
                prodFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frg_container, prodFragment).commit();

            }
        });
        lsDSPhim.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                 phimL = dsPhim.get(i);


                return false;
            }
        });
registerForContextMenu(lsDSPhim);
        return view;
    }

    private void init() {

        dsPhim.clear();
        dsPhim.addAll(dbHelper.getAllProductSQL());
        dsPhimAdapter.notifyDataSetChanged();


     }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_context,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_sua:
                try {
                    UpdatePhimFragment updatePhimFragment = new UpdatePhimFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PhimUpdate", phimL);
                    updatePhimFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frg_container, updatePhimFragment).commit();

                }catch (Exception e){
                    System.out.println(e.getMessage() + "tttttt");
                }


                break;
            case R.id.mnu_xoa:
                if (dbHelper.deleteProduct(phimL.getMa())>0)
                {
                    Toast.makeText(getContext(), "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
                    init();

                }else
                {
                    Toast.makeText(getContext(), "Khong The Xoa ", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return super.onContextItemSelected(item);
    }
}