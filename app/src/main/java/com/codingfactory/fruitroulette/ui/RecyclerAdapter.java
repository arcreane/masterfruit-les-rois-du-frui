package com.codingfactory.fruitroulette.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.logic.GameSequence;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String[]> guesses = new ArrayList<>();
    private ArrayList<String[]> positions = new ArrayList<>();
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
//            ImageView[] imgs = {holder.firstImg, holder.secondImg, holder.thirdImg, holder.fourthImg};
        holder.firstImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[0], "drawable", context.getPackageName()));
        holder.secondImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[1], "drawable", context.getPackageName()));
        holder.thirdImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[2], "drawable", context.getPackageName()));
        holder.fourthImg.setImageResource(context.getResources().getIdentifier(guesses.get(position)[3], "drawable", context.getPackageName()));
        holder.rightIndicator.setText(positions.get(position)[0]);
        holder.wrongIndicator.setText(positions.get(position)[1]);



    }

    @Override
    public int getItemCount() {
        return this.guesses.size();
    }

    public void addPositions(int right, int wrong) {
        this.positions.add(new String[] {String.valueOf(right), String.valueOf(wrong)});
    }

    public void newLine(String[] guesses) {
        this.guesses.add(guesses);
        notifyDataSetChanged();
    }

    public void clear() {
        this.guesses.clear();
        this.positions.clear();
        notifyItemRangeRemoved(0, this.guesses.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView firstImg;
        private ImageView secondImg;
        private ImageView thirdImg;
        private ImageView fourthImg;
        private TextView rightIndicator;
        private TextView wrongIndicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstImg = itemView.findViewById(R.id.firstGuess);
            secondImg = itemView.findViewById(R.id.secondGuess);
            thirdImg = itemView.findViewById(R.id.thirdGuess);
            fourthImg = itemView.findViewById(R.id.fourthGuess);
            rightIndicator = itemView.findViewById(R.id.rightIndicator);
            wrongIndicator = itemView.findViewById(R.id.wrongIndicator);

        }
    }
}
