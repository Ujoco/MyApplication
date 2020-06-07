package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.BreakIterator;

public class Main2Activity extends AppCompatActivity {

    private TextView text;
    private Mhandler mHandler = new Mhandler(this);
    private Message msg;

    public TextView getText() {
        return text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = findViewById(R.id.text);

        msg = Message.obtain();
        msg.obj = 10;
        mHandler.sendMessageDelayed(msg, 2000);
    }

    public static class Mhandler extends Handler {
        public final WeakReference<Main2Activity> main2ActivityWeakReference;
        public Mhandler(Main2Activity activity) {
            main2ActivityWeakReference = new WeakReference<Main2Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Main2Activity activity = main2ActivityWeakReference.get();
            int value = (int) msg.obj;
            activity.getText().setText(value+"");
            msg = Message.obtain();
            msg.obj = value - 1;
            
            if (value > 0){
                sendMessageDelayed(msg,1000);
            }
        }

    }


}
