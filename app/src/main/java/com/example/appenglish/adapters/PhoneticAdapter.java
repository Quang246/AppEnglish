package com.example.appenglish.adapters;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.R;
import com.example.appenglish.models.Phonetic;

import java.util.ArrayList;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticAdapter.ViewHolder> {
    Context context;
    ArrayList<Phonetic> phonetics;

    public PhoneticAdapter(Context context, ArrayList<Phonetic> phonetics) {
        this.context = context;
        this.phonetics = phonetics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ipa, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phonetic p = phonetics.get(position);

        holder.txtIPA.setText(p.getText());
//        holder.imgAudio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    MediaPlayer mediaPlayer = new MediaPlayer();
//                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                            .setUsage(AudioAttributes.USAGE_MEDIA)  // phát lại audio
//                            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)    // nội dung audio
//                            .build();
//
////                  đặt thuộc tính âm thanh cho mediaplayer
//                    mediaPlayer.setDataSource(p.getAudio());
//                    mediaPlayer.setAudioAttributes(audioAttributes);
//                    mediaPlayer.prepareAsync();
//                    mediaPlayer.start();
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        holder.imgAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(p.getAudio());

                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });

                    mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            mp.release();
                            return false;
                        }
                    });

                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                            .build();

                    mediaPlayer.setAudioAttributes(audioAttributes);
                    mediaPlayer.prepareAsync();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return phonetics.size();
    }

//    public String getAu() {
//        return phonetics.get(0).getAudio();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIPA;
        ImageButton imgAudio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIPA = (TextView) itemView.findViewById(R.id.txtIPA);
            imgAudio = (ImageButton) itemView.findViewById(R.id.imgAudio);

        }
    }
}
