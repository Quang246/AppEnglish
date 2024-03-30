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

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {
    Context context;
    ArrayList<Definition> definitions;

    public ExampleAdapter(Context context, ArrayList<Definition> definitions) {
        this.context = context;
        this.definitions = definitions;
    }

    @NonNull
    @Override
    public ExampleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_exam, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter.ViewHolder holder, int position) {
        Definition d = definitions.get(position);

        if (d.getExample() != null) {
            holder.txtVlExam.setText("-> " + d.getExample());
        } else {
            holder.txtVlExam.setText("Not example !!");
        }
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVlExam;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtVlExam = (TextView) itemView.findViewById(R.id.txtVlExam);

        }
    }
}
