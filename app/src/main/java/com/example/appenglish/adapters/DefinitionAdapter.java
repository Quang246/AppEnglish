package com.example.appenglish.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.R;
import com.example.appenglish.models.Definition;

import java.util.ArrayList;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.ViewHolder> {
    Context context;
    ArrayList<Definition> definitions;

    public DefinitionAdapter(Context context, ArrayList<Definition> definitions) {
        this.context = context;
        this.definitions = definitions;
    }

    @NonNull
    @Override
    public DefinitionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_definition, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionAdapter.ViewHolder holder, int position) {
        Definition d = definitions.get(position);

        holder.txtVlDefinition.setText("-> " + d.definition);

    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVlDefinition;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtVlDefinition = (TextView) itemView.findViewById(R.id.txtVlDefinition);

        }
    }
}
