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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadDetails;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumListMain;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.MatchesAdapter;
import com.example.myticket.View.Adapter.StadiumsAdapter;

import java.util.ArrayList;

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
    private ProgressBar progressBar;
    private Button retry;
    private String query;
    private String name = "";
    private TextView noResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stad_main_search);

        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");

        intent = getIntent();
        if (intent.hasExtra("tag") && intent.getStringExtra("tag").equals("stadiums")) {
            tag = intent.getStringExtra("tag");

        }
        if (intent.hasExtra("name")){
            name = intent.getStringExtra("name");
        }
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");

        apiCalling = new ApiCalling(this);
        mSearchLiveo = findViewById(R.id.search_liveo);

        autoCompleteRv = findViewById(R.id.search_rv);
        seeAll = findViewById(R.id.seeAll_search);
        seeAll.setTypeface(myfont);
        progressBar = findViewById(R.id.slider_stad_pb);
        retry = findViewById(R.id.retry_btn_match_details);
        retry.setTypeface(myfont);
        noResults = findViewById(R.id.no_results);
        noResults.setTypeface(myfont);
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

    @Override
    public void onBackPressed() {
        goBack();
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
                goBack();
            }
        });
    }

    private void goBack() {
        if (name.equals("MyTickets")){
            Intent intent = new Intent(StadMainSearch.this,HomeStadBottomNav.class);
            intent.putExtra("name", "MyTickets");
            startActivity(intent);
        }
        else
            finish();
    }

    @Override
    public void changedSearch(CharSequence charSequence) {
        query = charSequence.toString();
        doSearch(query);

    }

    private void doSearch(String query){
        setSearchToolbar();
        Log.e("changed", query);
        if (!query.equals("")) {
            if (tag != null) {
                apiCalling.getStadiumsSerachResults(query, this);
                progressBar.setVisibility(View.VISIBLE);

            } else {

                apiCalling.getTeamSearchResults(query, this);
                progressBar.setVisibility(View.VISIBLE);
            }
        }

    }

    private void setSearchToolbar() {
        mSearchLiveo.hideVoice();
        mSearchLiveo.show();
        if (tag != null){
            mSearchLiveo.hint(getResources().getString(R.string.search_stadiums));
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
        progressBar.setVisibility(View.GONE);
        noResults.setVisibility(View.GONE);
        if (tApiResponse instanceof StadiumListMain){
            StadiumListMain stadiumListMain = (StadiumListMain) tApiResponse;
            ArrayList<StadDetails> stadDetails = (ArrayList<StadDetails>) stadiumListMain.getStadDetails();
            if (stadDetails.size() == 0){
                noResults.setVisibility(View.VISIBLE);
            }
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
            if (matches.size() == 0){
                noResults.setVisibility(View.VISIBLE);
            }
            autoCompleteRv.setAdapter(new MatchesAdapter(this,matches, R.layout.btola_rv_item));
//            if (matches.size() <= 5){
//                seeAll.setVisibility(View.GONE);
//            }
//            else {
//                seeAll.setVisibility(View.VISIBLE);
//            }
        }
        else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            Toast.makeText(this,getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tag != null) {
                        apiCalling.getStadiumsSerachResults(query, StadMainSearch.this::getApiResponse);
                        progressBar.setVisibility(View.VISIBLE);

                    } else {

                        apiCalling.getTeamSearchResults(query, StadMainSearch.this::getApiResponse);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }
}
