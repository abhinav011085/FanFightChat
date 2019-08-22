package com.example.fanfightchat.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanfightchat.Helper.RoundLinearLayout;
import com.example.fanfightchat.Helper.Utility;
import com.example.fanfightchat.Model.ChatData;
import com.example.fanfightchat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.Holder> {
    Context context;
    List<ChatData> chatDataList;

    public AdapterChat(Context context, List<ChatData> chatDataList) {
        this.chatDataList = chatDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ChatData data = chatDataList.get(position);
        holder.chatInfo.setText(data.getName() + ", " + data.getDateTime());
        holder.message.setText(data.getText());
        holder.profileText.setTextColor(Color.parseColor(data.getProfileTextColor()));
        if (data.isMyText()) {
            holder.chatInfo.setGravity(Gravity.END);
            holder.ivReact.setVisibility(View.GONE);
            holder.message.setTextColor(Color.BLACK);
            holder.profileBg.setVisibility(View.GONE);
            holder.rlMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_mine));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.END;
            params.leftMargin = Utility.dp2px(context, 100);

            holder.itemView.setLayoutParams(params);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = Utility.dp2px(context, 100);

            holder.itemView.setLayoutParams(params);

            holder.profileIcon.setColorFilter(Color.parseColor(data.getBgColor()));
            holder.rlMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_others));
            String[] name = data.getName().split(" ");
            holder.profileText.setText(String.valueOf(name[0].charAt(0)).toUpperCase() + String.valueOf(name[1].charAt(0)).toUpperCase());
        }

        holder.ivReact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> reactionList = new ArrayList<>();
                reactionList.add(R.drawable.ic_angry_face);
                reactionList.add(R.drawable.ic_haha_emoji);
                reactionList.add(R.drawable.ic_like);
                reactionList.add(R.drawable.ic_love);
                reactionList.add(R.drawable.ic_sad_emoji);
                reactionList.add(R.drawable.ic_wow);
                View popUpView = LayoutInflater.from(context).inflate(R.layout.popup_reaction,
                        null);

                PopupWindow mpopup = new PopupWindow(popUpView, LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, true);

                RecyclerView recyclerView = popUpView.findViewById(R.id.reactions_rec_view);
                LinearLayout llMain = popUpView.findViewById(R.id.ll_main);

                recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

                recyclerView.setAdapter(new RecyclerView.Adapter() {

                    class MyHolder extends RecyclerView.ViewHolder {
                        ImageView imageView;

                        MyHolder(View view) {
                            super(view);
                            imageView = view.findViewById(R.id.image);
                        }
                    }

                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view1 = LayoutInflater.from(context).inflate(R.layout.item_reaction, parent, false);
                        return new MyHolder(view1);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                        ((MyHolder) holder).imageView.setImageResource(reactionList.get(position));
                        ((MyHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mpopup.dismiss();
                            }
                        });
                    }

                    @Override
                    public int getItemCount() {
                        return reactionList.size();
                    }
                });

                llMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mpopup.dismiss();
                    }
                });
                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
                mpopup.setBackgroundDrawable(new ColorDrawable());
                mpopup.setContentView(popUpView);
                mpopup.showAsDropDown(holder.ivReact, 0,
                        -Utility.dp2px(context, 70) - holder.ivReact.getMeasuredHeight());
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatDataList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_text)
        TextView profileText;
        @BindView(R.id.profile_icon)
        ImageView profileIcon;
        @BindView(R.id.profile_bg)
        FrameLayout profileBg;
        @BindView(R.id.chat_info)
        TextView chatInfo;
        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.iv_react)
        ImageView ivReact;
        @BindView(R.id.rl_message)
        LinearLayout rlMessage;

        Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
