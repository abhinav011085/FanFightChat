package com.example.fanfightchat;

import android.content.Context;
import android.os.Bundle;

import com.example.fanfightchat.Adapters.AdapterChat;
import com.example.fanfightchat.Model.ChatData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    RecyclerView chatRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatRecView = findViewById(R.id.chat_rec_view);
        chatRecView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        List<ChatData> chatDataList = new ArrayList<>();
        chatDataList.add(new ChatData("", "11:01 AM", "#FFD0D0D0",
                "This is a long message where TS will come in second line", true, "#FFFFFF"));
        chatDataList.add(new ChatData("Bhavesh Mehra", "11:01 AM", "#D0D0D0",
                "This is very very looooooooooooooooooooooooooooooooooooooo", false, "#000000"));
        chatDataList.add(new ChatData("", "11:01 AM", "#FFDC644D",
                "This is a long message where TS will come in second line", true, "#FFFFFF"));
        chatDataList.add(new ChatData("Bhavesh Mehra", "11:01 AM", "#FFE6644D",
                "This is a very very loooooooooooooooooooooooooooooooo", false, "#FFFFFF"));

        chatRecView.setAdapter(new AdapterChat(context, chatDataList));
    }
}
