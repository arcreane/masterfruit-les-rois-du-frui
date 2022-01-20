package com.codingfactory.fruitroulette.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.logic.GameSequence;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Integer[]> guesses = new ArrayList<>();
    private Context context;
    private GameSequence game;

    public RecyclerAdapter(Context context, GameSequence game) {
        this.context = context;
        this.game = game;
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
//        String firstDrawable =  game.getPossibleFruit().get(guesses.get(position)[0]).getImg();
//        String secondDrawable =  game.getPossibleFruit().get(guesses.get(position)[1]).getImg();
//        String thirdDrawable =  game.getPossibleFruit().get(guesses.get(position)[2]).getImg();
//        String fourthDrawable =  game.getPossibleFruit().get(guesses.get(position)[3]).getImg();

        ImageView[] imgs = {holder.firstImg, holder.secondImg, holder.thirdImg, holder.fourthImg};
        holder.firstImg.setImageResource(context.getResources().getIdentifier(game.getPossibleFruit().get(guesses.get(position)[0]).getImg(), "drawable", context.getPackageName()));
        holder.secondImg.setImageResource(context.getResources().getIdentifier(game.getPossibleFruit().get(guesses.get(position)[1]).getImg(), "drawable", context.getPackageName()));
        holder.thirdImg.setImageResource(context.getResources().getIdentifier(game.getPossibleFruit().get(guesses.get(position)[2]).getImg(), "drawable", context.getPackageName()));
        holder.fourthImg.setImageResource(context.getResources().getIdentifier(game.getPossibleFruit().get(guesses.get(position)[3]).getImg(), "drawable", context.getPackageName()));

        for (int i = 0; i < 4; i++) {
            switch (game.didIGuess(i)) {
                case 0:
                    imgs[i].getDrawable().setTint(Color.GRAY);
                    break;
                case 1:
                    imgs[i].getDrawable().setTint(Color.GREEN);
                    break;
                case 2:
                    imgs[i].getDrawable().setTint(Color.YELLOW);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.guesses.size();
    }

    public void setGuesses(ArrayList<Integer[]> guesses) {
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
