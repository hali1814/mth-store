package com.example.mthshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mthshop.R;
import com.example.mthshop.adapter.ProductAdapter;
import com.example.mthshop.model.Product;

import java.util.ArrayList;

public class ListProductFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        initWidgets(view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(productAdapter);



        return view;
    }

    private void initWidgets(View view) {
        recyclerView = view.findViewById(R.id.fListProduct_recycleView);
        productAdapter = new ProductAdapter(getActivity(), new ArrayList<Product>());
    }
}
