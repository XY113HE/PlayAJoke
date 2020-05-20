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

        BigDataTools bigDataTool = new BigDataTools();
        //乘法(大数乘以小数)
        bigDataTool
                .setData("132321283918642891794247109270391723491027401973143222315332546548523784982341451521214311")
                .mult(20)
                .printResult();
        //加法(大数加上小数，大数加上大数)
        bigDataTool
                .setData("8238791")
                .add(1761209)
                .printResult();
        //减法(大数减去小数， 大数减去相对小的大数)
        bigDataTool
                .setData("8238791")
                .sub(238792)
                .printResult();
        //除法(大数除以小数(只针对可被1整除不循环的小数))
        bigDataTool
                .setData("12512512525")
                .divide(5)
                .printResult();
        bigDataTool
                .setData("12512512525")
                .divide(50)
                .printResult();
        bigDataTool
                .setData("12512512525")
                .divide(8)
                .printResult();

    }
}
