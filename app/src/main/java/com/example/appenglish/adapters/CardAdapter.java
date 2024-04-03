package com.example.appenglish.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.R;
import com.example.appenglish.models.CardModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    Context context;
    ArrayList<CardModel> cardModels;

    public CardAdapter(Context context, ArrayList<CardModel> cardModels) {
        this.context = context;
        this.cardModels = cardModels;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        CardModel c = cardModels.get(position);

        holder.txtCard_1.setText(c.getTxtCard_1());
        holder.txtCard_2.setText(c.getTxtCard_2());

    }

    @Override
    public int getItemCount() {
        return cardModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCard_1, txtCard_2;
        ImageView btnVolume, btnHeart, btnBaCham;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCard_1 = (TextView) itemView.findViewById(R.id.txtCard_1);
            txtCard_2 = (TextView) itemView.findViewById(R.id.txtCard_2);
            btnVolume = (ImageView) itemView.findViewById(R.id.btnVolume);
            btnHeart = (ImageView) itemView.findViewById(R.id.heartBorder);
            btnBaCham = (ImageView) itemView.findViewById(R.id.btnBaCham);

        }
    }
}
