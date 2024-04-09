package com.example.appenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.R;
import com.example.appenglish.models.CardModel;

import java.util.ArrayList;

public class SelectWordAdapter extends RecyclerView.Adapter<SelectWordAdapter.ViewHolder> {
    Context context;
    ArrayList<CardModel> arWords;
    public OnItemClickListener listener;

    public SelectWordAdapter(ArrayList<CardModel> arWords, OnItemClickListener listener) {
        this.arWords = arWords;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_practice_word, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardModel cm = arWords.get(position);
        holder.btnCardWord.setText(cm.getTxtCard_2());
        holder.btnCardWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView btnCardWord;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnCardWord = (TextView) itemView.findViewById(R.id.btnCardWord);

        }
    }

    // Interface để xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
