package com.example.pdf_reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this, "Volume UP clicked", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this, "Volume DOWN clicked", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_HOME:
                Toast.makeText(this, "HOME button clicked", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_MENU:
                Toast.makeText(this, "MENU button clicked", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "BACK button clicked", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_POWER:
                Toast.makeText(this, "POWER button clicked", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onAttachedToWindow() {
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }
}
