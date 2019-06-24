package com.example.myticket.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.CategoryAdapter;
import com.example.myticket.View.Adapter.HomeMovieAdapter;
import com.example.myticket.View.Adapter.ResultsAdapter;
import com.example.myticket.View.Adapter.SearchAdapter;
import com.example.myticket.helper.Variables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchResults extends AppCompatActivity {
    private ArrayList<Result> searchResults;
    private ArrayList<String> categoriesFilter;
    private Variables variables = new Variables();
    private ResultsAdapter searchAdapter;
    private CategoryAdapter categoryAdapter;
    private RecyclerView resultsRv;
    private RecyclerView filtersRV;
    private Button moviesBtn;
    private Button cinemasBtn;
    private TextView filterResultText;
    private TextView searchResultsText;
    private  ArrayList<Result> filteredList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        resultsRv = findViewById(R.id.all_results_search);
        moviesBtn = findViewById(R.id.movies_btn);
        cinemasBtn = findViewById(R.id.cinemas_btn);
        filtersRV = findViewById(R.id.filters_rv);
        filterResultText = findViewById(R.id.filter_result_text);
        searchResultsText = findViewById(R.id.results_title);
        filteredList = new ArrayList<>();
        resultsRv.setLayoutManager(new GridLayoutManager(this,3));
        filtersRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Intent intent = getIntent();
        if (intent.getData() != null) {
            String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Result[] results  = gson.fromJson(stringData,Result[].class);
            searchResults = new ArrayList<>(Arrays.asList(results));
            searchAdapter = new ResultsAdapter(this,searchResults);
            resultsRv.setAdapter(searchAdapter);

        }
        setFilters();
    }

    private void setFilters() {
        moviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesBtn.setVisibility(View.GONE);
                cinemasBtn.setVisibility(View.GONE);
                filterResultText.setVisibility(View.VISIBLE);
                for (Result result : searchResults){
                    if (result.getType().equals("1")){
                        filteredList.add(result);
                    }
                }
                searchResultsText.setText("Movies");
                variables.setCategories();
                categoriesFilter = variables.getCategories();
                filtersRV.setVisibility(View.VISIBLE);
                categoryAdapter = new CategoryAdapter(SearchResults.this,null,categoriesFilter);
                filtersRV.setAdapter(categoryAdapter);
                searchAdapter.update(filteredList);

            }
        });

        cinemasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesBtn.setVisibility(View.GONE);
                cinemasBtn.setVisibility(View.GONE);
                filterResultText.setVisibility(View.VISIBLE);
                filterResultText.setText("Cinemas");
                searchResultsText.setText("Cinemas");
                for (Result result : searchResults){
                    if (result.getType().equals("2")){
                        filteredList.add(result);
                    }
                }
                searchResultsText.setText("Cinemas");
                variables.setCinemasCategories();
                categoriesFilter = variables.getCinemasCategories();
                filtersRV.setVisibility(View.VISIBLE);
                categoryAdapter = new CategoryAdapter(SearchResults.this,null,categoriesFilter);
                filtersRV.setAdapter(categoryAdapter);
                searchAdapter.update(filteredList);

            }
        });
    }
}
