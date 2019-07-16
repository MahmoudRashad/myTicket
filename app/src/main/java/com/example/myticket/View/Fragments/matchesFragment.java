package com.example.myticket.View.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.HomeStadiumMainAdapter;

public class matchesFragment extends Fragment {
    private RecyclerView mainBtolatRv;
    private HomeStadiumMainAdapter adapter;
    public matchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        mainBtolatRv = view.findViewById(R.id.btolat_rv);
        adapter = new HomeStadiumMainAdapter(getContext());
        mainBtolatRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainBtolatRv.setAdapter(adapter);
        return view;
    }



}
