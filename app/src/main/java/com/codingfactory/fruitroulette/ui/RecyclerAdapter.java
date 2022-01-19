package com.codingfactory.fruitroulette.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingfactory.fruitroulette.Fruits.Fruit;
import com.codingfactory.fruitroulette.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<ArrayList<Fruit>> guesses = new ArrayList<>();

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
        ImageView[] imgs = {holder.firstImg, holder.secondImg, holder.thirdImg, holder.fourthImg};

        holder.firstImg.setImageResource(context.getResources().getIdentifier(guesses.get(position).get(0).getImg(), "drawable", context.getPackageName()));
        holder.secondImg.setImageResource(context.getResources().getIdentifier(guesses.get(position).get(1).getImg(), "drawable", context.getPackageName()));
        holder.thirdImg.setImageResource(context.getResources().getIdentifier(guesses.get(position).get(2).getImg(), "drawable", context.getPackageName()));
        holder.fourthImg.setImageResource(context.getResources().getIdentifier(guesses.get(position).get(3).getImg(), "drawable", context.getPackageName()));

        // Test if banana not on the list:
//        for (int i = 0; i < 4; i++) {
//            if (guesses.get(position)[i].equals("ic_banana")) {
//                imgs[i].setColorFilter(Color.LTGRAY);
//                break;
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return this.guesses.size();
    }

    public void setGuesses(ArrayList<ArrayList<Fruit>> guesses) {
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
