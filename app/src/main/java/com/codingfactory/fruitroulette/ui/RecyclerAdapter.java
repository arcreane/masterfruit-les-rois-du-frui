package com.codingfactory.fruitroulette.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingfactory.fruitroulette.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String[]> guesses = new ArrayList<>();

    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guess_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[0], "drawable", context.getPackageName()));
        holder.secondImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[1], "drawable", context.getPackageName()));
        holder.thirdImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[2], "drawable", context.getPackageName()));
        holder.fourthImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[3], "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return this.guesses.size();
    }

    public void setGuesses(ArrayList<String[]> guesses) {
        this.guesses = guesses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView firstImg;
        private ImageView secondImg;
        private ImageView thirdImg;
        private ImageView fourthImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstImg = itemView.findViewById(R.id.firstGuess);
            secondImg = itemView.findViewById(R.id.secondGuess);
            thirdImg = itemView.findViewById(R.id.thirdGuess);
            fourthImg = itemView.findViewById(R.id.fourthGuess);
        }
    }
}
