package com.example.appenglish.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.R;
import com.example.appenglish.models.CardModel;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardModel c = cardModels.get(position);

        holder.txtCard_1.setText(c.getTxtCard_1());
        holder.txtCard_2.setText(c.getTxtCard_2());
        holder.btnBaCham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(context, holder.btnBaCham);
                menu.inflate(R.menu.menu_card);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                        delete
                        if (item.getItemId() == R.id.delete) {
//                            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();

                            final Dialog dialog = new Dialog(context);
                            dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
                            dialog.setContentView(R.layout.dialog_delete);

                            Window window = dialog.getWindow();
                            if (window == null) {
                                return false;
                            }
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            WindowManager.LayoutParams windowAttribute = window.getAttributes();
                            windowAttribute.gravity = Gravity.CENTER;

                            window.setAttributes(windowAttribute);

//                          ấn ra khoảng trống sẽ tắt dialog
                            dialog.setCancelable(true);
                            dialog.findViewById(R.id.btnCan).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.findViewById(R.id.btnDe).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CreateDatabase db = new CreateDatabase(context);
                                    db.deleteWord(c.getTxtCard_1(), c.getTxtCard_2());
                                    cardModels.remove(position);
                                    notifyItemRemoved(position);
                                    dialog.dismiss();
                                }
                            });

                            dialog.show();
                            return true;
                        }
//                        update
                        else if (item.getItemId() == R.id.update) {

                            String oldWord = c.getTxtCard_1();
                            String olDes = c.getTxtCard_2();

                            final Dialog dialog = new Dialog(context);
                            dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
                            dialog.setContentView(R.layout.dialog_update_flashcard);

                            Window window = dialog.getWindow();
                            if (window == null) {
                                return false;
                            }
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            WindowManager.LayoutParams windowAttribute = window.getAttributes();
                            windowAttribute.gravity = Gravity.CENTER;

                            window.setAttributes(windowAttribute);

//                          ấn ra khoảng trống sẽ tắt dialog
                            dialog.setCancelable(true);

                            EditText editWord = dialog.findViewById(R.id.editWord);
                            EditText editDescription = dialog.findViewById(R.id.editDescription);
                            Button btnCancle = dialog.findViewById(R.id.btnCancel);
                            Button btnOk = dialog.findViewById(R.id.btnOk);

                            editWord.setText(oldWord);
                            editDescription.setText(olDes);

                            btnCancle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            btnOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String newWord = editWord.getText().toString();
                                    String newDescription = editDescription.getText().toString();

                                    Toast.makeText(context, "Success !!", Toast.LENGTH_SHORT).show();

//                                  update word
                                    CreateDatabase db = new CreateDatabase(context);
                                    db.updateWord(oldWord, olDes, newWord, newDescription);

                                    // Cập nhật lại dữ liệu của item trong RecyclerView tại vị trí position
                                    cardModels.get(position).setTxtCard_1(newWord);
                                    cardModels.get(position).setTxtCard_2(newDescription);

                                    notifyItemChanged(position);
//                                    notifyDataSetChanged();


                                    dialog.dismiss();
                                }
                            });
//                          show dialog
                            dialog.show();
                        }
                        return false;
                    }

//                  ...
//                    public void getInit(String email) {
//                        ArrayList<CardModel> arCards = db.getAllCards(email);
//
//                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
//                        rvCard.setLayoutManager(mLayoutManager);
//
//                        CardAdapter cardAdapter = new CardAdapter(getContext(), arCards);
//                        rvCard.setAdapter(cardAdapter);
//
//                        txtSaveWord.setText("ĐÃ GHI NHỚ: " + arCards.size());
//
//                    }

                });
                //displaying the popup
                menu.show();
            }
        });
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
