package com.example.myticket.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.CommentsModel.Result;
import com.example.myticket.Model.Network.DataModel.HomeResult.Category;
import com.example.myticket.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ReviewsViewHolder> {

    private Context context;
    private ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_list_item,viewGroup,false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i) {
        if (categories.size() != 0){
            Category category = categories.get(i);
            reviewsViewHolder.categoryText.setText(category.getCategory());
        }
    }

    @Override
    public int getItemCount() {
        if (categories != null)
        return categories.size();
        else return 0;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryText;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.category_text);
        }
    }
}