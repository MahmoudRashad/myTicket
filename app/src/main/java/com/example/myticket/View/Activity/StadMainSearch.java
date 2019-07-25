package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadDetails;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumListMain;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.MatchesAdapter;
import com.example.myticket.View.Adapter.StadSearchAdapter;
import com.example.myticket.View.Adapter.StadiumsAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.liveo.searchliveo.SearchLiveo;

public class StadMainSearch extends AppCompatActivity implements SearchLiveo.OnSearchListener, GeneralListener {

    private SearchLiveo mSearchLiveo;
    private RecyclerView autoCompleteRv;
    private ApiCalling apiCalling;
    private ArrayList<Result> searchResults;
    private Button seeAll;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private Typeface myfont;
    private Intent intent;
    private String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stad_main_search);

        intent = getIntent();
        if (intent.hasExtra("tag") && intent.getStringExtra("tag").equals("stadiums")) {
            tag = intent.getStringExtra("tag");

        }
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");

        apiCalling = new ApiCalling(this);
        mSearchLiveo = findViewById(R.id.search_liveo);

        autoCompleteRv = findViewById(R.id.search_rv);
        seeAll = findViewById(R.id.seeAll_search);
        seeAll.setTypeface(myfont);
        seeAll.setVisibility(View.GONE);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StadMainSearch.this,StadAllSearchResults.class);
                startActivity(intent);
            }
        });
        setToolbar();
        autoCompleteRv.setLayoutManager(new LinearLayoutManager(this));
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
        String query = charSequence.toString();
        doSearch(query);

    }

    private void doSearch(String query){
        setSearchToolbar();
        Log.e("changed", query);
        if (!query.equals("")) {
            if (tag != null) {
                apiCalling.getStadiumsSerachResults(query, this);

            } else {

                apiCalling.getTeamSearchResults(query, this);
            }
        }

    }

    private void setSearchToolbar() {
        mSearchLiveo.hideVoice();
        mSearchLiveo.show();
        if (tag != null){
            mSearchLiveo.hint("Search Stadiums");
        }
        if (mSearchLiveo.isShown()){
            autoCompleteRv.setVisibility(View.VISIBLE);
        }
        mSearchLiveo.minToSearch(1);
        mSearchLiveo.removeSearchDelay();

//        mSearchLiveo.imeActionSearch();
//        mSearchLiveo.hideKeyboardAfterSearch();


    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof StadiumListMain){
            StadiumListMain stadiumListMain = (StadiumListMain) tApiResponse;
            ArrayList<StadDetails> stadDetails = (ArrayList<StadDetails>) stadiumListMain.getStadDetails();
            autoCompleteRv.setAdapter(new StadiumsAdapter(this,stadDetails,1));
//            if (stadDetails.size() <= 5){
//                seeAll.setVisibility(View.GONE);
//            }
//            else {
//                seeAll.setVisibility(View.VISIBLE);
//            }

        }
        else if (tApiResponse instanceof MainMatches){
            MainMatches mainMatches = (MainMatches) tApiResponse;
            ArrayList<MatchDetails> matches = (ArrayList<MatchDetails>) mainMatches.getResult();
            autoCompleteRv.setAdapter(new MatchesAdapter(this,matches));
//            if (matches.size() <= 5){
//                seeAll.setVisibility(View.GONE);
//            }
//            else {
//                seeAll.setVisibility(View.VISIBLE);
//            }
        }

    }
}
