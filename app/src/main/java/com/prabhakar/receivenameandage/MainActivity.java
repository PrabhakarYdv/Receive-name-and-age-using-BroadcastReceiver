package com.prabhakar.receivenameandage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {
    private TextView name;
    private TextView age;
    private Button update;
    private LocalBroadcastManager localBroadcastManager;
    private ReceiveBroadcast receiveBroadcast;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        update = findViewById(R.id.updateBtn);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("Action.programmer");
                intent.putExtra("name", "Prabhakar Yadav");
                intent.putExtra("age", "20");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        broadcastReceiver();
    }

    private void broadcastReceiver() {
        receiveBroadcast = new ReceiveBroadcast();
        IntentFilter intentFilter = new IntentFilter("Action.programmer");
        localBroadcastManager.registerReceiver(receiveBroadcast, intentFilter);
    }

    private class ReceiveBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                name.setText(intent.getStringExtra("name"));
                age.setText(intent.getStringExtra("age"));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(receiveBroadcast);
    }
}