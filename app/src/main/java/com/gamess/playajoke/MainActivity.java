package com.gamess.playajoke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gamess.playajoke.utils.BigDataTools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test() {
        new BigDataTools().setData("4423758534451353843253144343215474331353843253144");
    }
}
