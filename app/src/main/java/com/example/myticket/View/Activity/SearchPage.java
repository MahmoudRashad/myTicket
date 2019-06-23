package com.example.myticket.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.Model.Network.DataModel.Search.SearchResponce;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.SearchAdapter;

import java.util.ArrayList;

import br.com.liveo.searchliveo.SearchLiveo;


public class SearchPage extends AppCompatActivity implements SearchLiveo.OnSearchListener, GeneralListener {
    private SearchLiveo mSearchLiveo;
    private RecyclerView autoCompleteRv;
    private ApiCalling apiCalling;
    private ArrayList<Result> searchResults;
    private Button seeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        apiCalling = new ApiCalling(this);
        mSearchLiveo = findViewById(R.id.search_liveo);
        autoCompleteRv = findViewById(R.id.search_rv);
        seeAll = findViewById(R.id.seeAll_search);
        seeAll.setVisibility(View.GONE);
        autoCompleteRv.setLayoutManager(new LinearLayoutManager(this));
        mSearchLiveo.with(this).build();
        mSearchLiveo.show();
        if (mSearchLiveo.isShown()){
            autoCompleteRv.setVisibility(View.VISIBLE);
        }
        mSearchLiveo.with(this).
                hideSearch(new SearchLiveo.OnHideSearchListener() {
                    @Override
                    public void hideSearch() {
                        autoCompleteRv.setVisibility(View.GONE);
                        seeAll.setVisibility(View.GONE);
                    }
                }).
                build();
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this,SearchResults.class);
                //transform to string using gson, recieve it there, transform and and send it to rv
                startActivity(intent);
            }
        });
    }

    @Override
    public void changedSearch(CharSequence charSequence) {
        Log.e("changed",charSequence.toString());
        String query = charSequence.toString();
        apiCalling.search(query,this);
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        SearchResponce searchResponce = (SearchResponce) tApiResponse;
        searchResults = (ArrayList<Result>) searchResponce.getResult();
        SearchAdapter searchAdapter = new SearchAdapter(this,searchResults);
        autoCompleteRv.setAdapter(searchAdapter);
        seeAll.setVisibility(View.VISIBLE);
    }
}
