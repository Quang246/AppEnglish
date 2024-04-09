package com.example.appenglish.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.R;
import com.example.appenglish.models.CardWord;

import java.util.ArrayList;

import Database.CreateDatabase;

public class PracticeWordAdapter extends RecyclerView.Adapter<PracticeWordAdapter.ViewHolder> {
    Context context;
    ArrayList<Integer> arRemove = new ArrayList<>();
    ArrayList<String> arRemoveStr = new ArrayList<>();
    ArrayList<CardWord> arWords;
    CreateDatabase db;

    public PracticeWordAdapter(Context context, ArrayList<CardWord> arWords) {
        this.context = context;
        this.arWords = arWords;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_practice_word, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        db = new CreateDatabase(context);
        CardWord cw = arWords.get(position);

        holder.btnCardWord.setText(cw.getWord());
        holder.btnCardWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                arWords.remove(tmp);
//                // Thông báo adapter về việc dữ liệu đã thay đổi
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, arWords.size());
//                Toast.makeText(context, holder.btnCardWord.getText(), Toast.LENGTH_SHORT).show();
                arRemove.add(position);
                arRemoveStr.add(holder.btnCardWord.getText().toString());

//              set color ...
//                holder.btnCardWord.setBackgroundColor(context.getResources().getColor(R.color.grey_press));
                
                if (arRemove.size() == 2) {
                    int firstPosition = arRemove.get(0);
                    int secondPosition = arRemove.get(1);

                    if ((db.checkWordDes(arRemoveStr.get(0), arRemoveStr.get(1)) == true) || (db.checkWordDes(arRemoveStr.get(1), arRemoveStr.get(0)) == true)) {
//                        holder.btnCardWord.setBackgroundColor(context.getResources().getColor(R.color.green_clean));
                        arWords.remove(firstPosition);
                        arWords.remove(secondPosition > firstPosition ? secondPosition - 1 : secondPosition);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Correct !!", Toast.LENGTH_SHORT).show();

                        if (arWords.size() == 0) {
                            final Dialog dialog = new Dialog(context);
                            dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
                            dialog.setContentView(R.layout.dialog_complete);

                            Window window = dialog.getWindow();
                            if (window == null) {
                                return;
                            }
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            WindowManager.LayoutParams windowAttribute = window.getAttributes();
                            windowAttribute.gravity = Gravity.CENTER;

                            window.setAttributes(windowAttribute);

//                          ấn ra khoảng trống sẽ tắt dialog
                            dialog.setCancelable(true);
                            dialog.findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            dialog.show();
                        }
                    } else {
                        Toast.makeText(context, "Incorrect !!", Toast.LENGTH_SHORT).show();
//                        holder.btnCardWord.setBackgroundColor(context.getResources().getColor(R.color.grey_press));

                    }

                    arRemove.clear();
                    arRemoveStr.clear();
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
}
