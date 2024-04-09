package com.example.appenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.R;
import com.example.appenglish.models.Meaning;

import java.util.ArrayList;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.ViewHolder> {

    Context context;
    ArrayList<Meaning> meanings;
    DefinitionAdapter definitionAdapter;
    ExampleAdapter exampleAdapter;

    public MeaningAdapter(Context context, ArrayList<Meaning> meanings) {
        this.context = context;
        this.meanings = meanings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mean_activity, parent, false));
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mean_activity, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meaning m = meanings.get(position);

        holder.txtTypeWord.setText(m.getPartOfSpeech());

//      definition
        RecyclerView.LayoutManager m1LayoutManager = new GridLayoutManager(context, 1);
        definitionAdapter = new DefinitionAdapter(context, m.getDefinitions());
        holder.rvDefinition.setLayoutManager(m1LayoutManager);
        holder.rvDefinition.setAdapter(definitionAdapter);

//      example
        RecyclerView.LayoutManager m2LayoutManager = new GridLayoutManager(context, 1);
        exampleAdapter = new ExampleAdapter(context, m.getDefinitions());
        holder.rvEx.setLayoutManager(m2LayoutManager);
        holder.rvEx.setAdapter(exampleAdapter);

//      synonym
        holder.vlSyn.setText(m.getSynonyms());
        holder.vlAnto.setText(m.getAntonym());
    }

    @Override
    public int getItemCount() {
        return meanings.size();
    }

//    public String getDt() {
//        return meanings.get(0).partOfSpeech;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTypeWord, txtDefinition, txtSyn, txtAnto, txtEx, vlSyn, vlAnto;
        RecyclerView rvDefinition, rvEx;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            txtTypeWord = (TextView) itemView.findViewById(R.id.txtTypeWord);
            txtDefinition = (TextView) itemView.findViewById(R.id.txtDefinition);
            rvDefinition = (RecyclerView) itemView.findViewById(R.id.rvDefinition);
            txtSyn = (TextView) itemView.findViewById(R.id.txtSyn);
            txtAnto = (TextView) itemView.findViewById(R.id.txtAnto);
            vlSyn = (TextView) itemView.findViewById(R.id.vlSyn);
            vlAnto = (TextView) itemView.findViewById(R.id.vlAnto);
            txtEx = (TextView) itemView.findViewById(R.id.txtEx);
            rvEx = (RecyclerView) itemView.findViewById(R.id.rvEx);

        }
    }
}
