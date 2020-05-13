package com.gamess.playajoke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.gamess.playajoke.utils.BigDataTools;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test() {
        new BigDataTools().setData("132321283918642891794247109270391723491027401973143222315332546548523784982341451521214311").mult(20).printResult();
    }
}
