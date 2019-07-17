package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadSearchAdapter;

import java.util.ArrayList;

import br.com.liveo.searchliveo.SearchLiveo;

public class StadMainSearch extends AppCompatActivity implements SearchLiveo.OnSearchListener {

    private SearchLiveo mSearchLiveo;
    private RecyclerView autoCompleteRv;
    private ApiCalling apiCalling;
    private ArrayList<Result> searchResults;
    private Button seeAll;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private Typeface myfont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stad_main_search);

        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");

        apiCalling = new ApiCalling(this);
        mSearchLiveo = findViewById(R.id.search_liveo);

        autoCompleteRv = findViewById(R.id.search_rv);
        seeAll = findViewById(R.id.seeAll_search);
        seeAll.setTypeface(myfont);
//        seeAll.setVisibility(View.GONE);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StadMainSearch.this,StadAllSearchResults.class);
                startActivity(intent);
            }
        });
        setToolbar();
        autoCompleteRv.setLayoutManager(new LinearLayoutManager(this));
        autoCompleteRv.setAdapter(new StadSearchAdapter(this,null));
        mSearchLiveo.with(this).build();
        setSearchToolbar();
    }

    private void setToolbar() {

        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.search));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//        apiCalling.search(query,this);
        setSearchToolbar();
    }

    private void setSearchToolbar() {
        mSearchLiveo.hideKeyboardAfterSearch();
        mSearchLiveo.hideVoice();
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
}
