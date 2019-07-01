package com.example.myticket.View.Activity;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.Model.Network.DataModel.Search.SearchResponce;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.SearchAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.liveo.searchliveo.SearchLiveo;


public class SearchPage extends AppCompatActivity implements SearchLiveo.OnSearchListener, GeneralListener {
    private SearchLiveo mSearchLiveo;
    private RecyclerView autoCompleteRv;
    private ApiCalling apiCalling;
    private ArrayList<Result> searchResults;
    private Button seeAll;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        apiCalling = new ApiCalling(this);
        mSearchLiveo = findViewById(R.id.search_liveo);
        autoCompleteRv = findViewById(R.id.search_rv);
        seeAll = findViewById(R.id.seeAll_search);
        seeAll.setVisibility(View.GONE);
        setToolbar();
        autoCompleteRv.setLayoutManager(new LinearLayoutManager(this));
        mSearchLiveo.with(this).build();
        setSearchToolbar();
    }
    private void setToolbar() {

        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.search));
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(SearchPage.this,SearchPage.class);
//                startActivity(intent);
                setSearchToolbar();

            }

        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void changedSearch(CharSequence charSequence) {
        Log.e("changed",charSequence.toString());
        String query = charSequence.toString();
        apiCalling.search(query,this);
        setSearchToolbar();
    }

    private void setSearchToolbar() {
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

    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        SearchResponce searchResponce = (SearchResponce) tApiResponse;
        if (searchResponce.getResult() != null) {
            searchResults = (ArrayList<Result>) searchResponce.getResult();
            SearchAdapter searchAdapter = new SearchAdapter(this, searchResults);
            autoCompleteRv.setAdapter(searchAdapter);
            seeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchPage.this, SearchResults.class);
                    //transform to string using gson, recieve it there, transform and and send it to rv
                    String ListDumb = new Gson().toJson(searchResults);
                    intent.setData(Uri.fromParts("schemeSearchResults", ListDumb, null));
                    startActivity(intent);
                }
            });
            seeAll.setVisibility(View.VISIBLE);
        }
    }
}
