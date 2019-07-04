package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.HomeResult.Category;
import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ReviewsViewHolder> {

    private Context context;
    private ArrayList<Category> categories;
    private List<com.example.myticket.Model.Network.DataModel.Search.Category> categorySearch;
    private ArrayList<Result> searchResults;
    private ResultsAdapter resultsAdapter;
    private Typeface myfont;

//    private int itemSelectedPrevios;
//    private String previousCategory;
//    private RecyclerView mRecyclerList;

    public CategoryAdapter(Context context, ArrayList<Category> categories, List<com.example.myticket.Model.Network.DataModel.Search.Category> category, ArrayList<Result> searchResults, ResultsAdapter resultsAdapter, RecyclerView filtersRV) {
        this.context = context;
        this.categories = categories;
        this.categorySearch = category;
        this.searchResults = searchResults;
        this.resultsAdapter = resultsAdapter;
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

        //     this.mRecyclerList = filtersRV;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_list_item,viewGroup,false);
        return new ReviewsViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i) {
        if (categories!= null){
            Category category = categories.get(i);
            reviewsViewHolder.categoryText.setText(category.getCategory());
        }
        else if (categorySearch != null){

            com.example.myticket.Model.Network.DataModel.Search.Category category = categorySearch.get(i);
            reviewsViewHolder.categoryText.setText(category.getCategory());
        }
        else{
            reviewsViewHolder.categoryText.setText(context.getString(R.string.near_by));
        }
    }

    @Override
    public int getItemCount() {
        if (categories != null)
        return categories.size();
        else if (categorySearch != null)
            return categorySearch.size();
        else return 1;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView categoryText;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.category_text);
            categoryText.setTypeface(myfont);
            itemView.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            if (searchResults != null){
            //    TextView previousTextView;
                int position = getAdapterPosition();
                int positionID = position+1;
             //   categoryText.setBackgroundColor(context.getColor(R.color.gray));
                if (categorySearch != null) {
                    resultsAdapter.updateMovies(positionID);
                }

//                if (mRecyclerList.findViewHolderForAdapterPosition(itemSelectedPrevios) != null) {
//                    View view =mRecyclerList.findViewHolderForAdapterPosition(itemSelectedPrevios).itemView;
//
//                    previousTextView = view.findViewById(R.id.category_text);
//                    previousTextView.setBackground(context.getDrawable(R.drawable.white_rect_bk));
//                }
//
//                itemSelectedPrevios = position;





//                String category = categorySearch.get(position);
                //newSearchResults = new ArrayList<>();
//                for (Result result: searchResults){
//                    searchCategories = (ArrayList<com.example.myticket.Model.Network.DataModel.Search.Category>) result.getCategory();
//                    for (com.example.myticket.Model.Network.DataModel.Search.Category cat : searchCategories){
//                        int id = cat.getId();
//
//                        if (id == positionID){
//                            newSearchResults.add(result);
//                            resultsAdapter.update(newSearchResults);
//                        }
//                        else
//                        {
//                            resultsAdapter.update(newSearchResults);
//                        }
//                    }
//                }
            }
        }
    }
}
