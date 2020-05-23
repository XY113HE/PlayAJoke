package com.gamess.playajoke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gamess.playajoke.utils.AESUtils;
import com.gamess.playajoke.utils.BigDataTools;
import com.gamess.playajoke.utils.PointShowView;
import com.gamess.playajoke.utils.RSAUtils;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;
    private PointShowView pointShowView;
    private EditText count;
    private TextView countAllTv;
    private List<Float> xs = new ArrayList<>();
    private List<Float> ys = new ArrayList<>();
    private float maxX, maxY;
    private List<Boolean> dirXs = new ArrayList<>(); //true正
    private List<Boolean> dirYs = new ArrayList<>(); //true正
    private List<Integer> speedXs = new ArrayList<>();
    private List<Integer> speedYs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.test_layout);
        pointShowView = findViewById(R.id.point_view);
        count = findViewById(R.id.count);
        countAllTv = findViewById(R.id.countall_tv);
//        tv1 = findViewById(R.id.tv1);
//        tv2 = findViewById(R.id.tv2);
//        tv3 = findViewById(R.id.tv3);
//        tv4 = findViewById(R.id.tv4);
//        tv5 = findViewById(R.id.tv5);
//        tv6 = findViewById(R.id.tv6);

        //大数值计算
//        test();
        //RSA加密
//        test2();
        //AES加密
//        test3();


    }

    @Override
    protected void onResume() {
        super.onResume();


        //换了test布局，使用PointShowView
        test4();
    }

    private void init4() {
        addPoint(1);

        pointShowView.setInit(xs, ys);

        pointShowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int c = Integer.parseInt(count.getText().toString().trim());
                    addPoint(c);
                }catch (Exception e){

                }
            }
        });
    }

    private void addPoint(int countI) {
        countAllTv.setText(Integer.parseInt(countAllTv.getText().toString().trim())+countI+"");
        Random random = new Random();
        for (int i = 0; i < countI; i++) {
            xs.add(random.nextInt((int) maxX) + 0f);
            ys.add(random.nextInt((int) maxY) + 0f);
            dirXs.add(true);
            dirYs.add(true);
            speedXs.add(random.nextInt(20));
            speedYs.add(random.nextInt(20));
        }
    }


    private void test4() {
        //获取density
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        maxX = dm.density * 200;         // 屏幕宽度（像素）
        maxY = dm.density * 500;       // 屏幕高度（像素）
        //初始化小球弹View信息
        init4();
        //开启线程让小球跑动
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    SystemClock.sleep(50);
                    setPoint();
                    pointShowView.show();
                }
            }
        }).start();
    }

    private void setPoint() {
        for (int i = 0; i < xs.size(); i++) {
            if (dirXs.get(i)) {
                xs.set(i, xs.get(i) + speedXs.get(i));
                pointShowView.setXX(xs.get(i), i);
            } else {
                xs.set(i, xs.get(i) - speedXs.get(i));
                pointShowView.setXX(xs.get(i), i);
            }
            if (dirYs.get(i)) {
                ys.set(i, ys.get(i) + speedYs.get(i));
                pointShowView.setYY(ys.get(i), i);
            } else {
                ys.set(i, ys.get(i) - speedYs.get(i));
                pointShowView.setYY(ys.get(i), i);
            }
            if (xs.get(i) >= maxX) {
                dirXs.set(i, false);
            }
            if (xs.get(i) <= i) {
                dirXs.set(i, true);
            }
            if (ys.get(i) >= maxY) {
                dirYs.set(i, false);
            }
            if (ys.get(i) <= i) {
                dirYs.set(i, true);
            }
        }
    }

    private void test3() {
        String orginData = "hdagsd71928dgh8913gdiuwhdh328gtdtg3giuwhd901y238dtgegfciu321gd";
        String data1 = AESUtils.encrypt(orginData, "jsghfduiwehfoweijhfpowe");
        tv1.setText(orginData);
        tv2.setText(data1);
        String data2 = AESUtils.decrypt(data1, "jsghfduiwehfoweijhfpowe");
        tv3.setText(data2);
    }

    private void test2() {
        KeyPair keyPair = RSAUtils.generateRSAKeyPair(2048);
        String orginData = "ihaveadreweuhr91h3092h3f9234hj1sdgf872g3dh2ehfd9872gt39f18h32d894gy78dg3189hd189g3298f23g4grd238hfd01i932hd01h3029hd24uhjcd1o2hnrdi2o39hf982hfd8u3y4gvf92iuam";
        tv1.setText(orginData);
        String data1 = RSAUtils.encryptDataByPublicKey(orginData.getBytes(), keyPair.getPublic());
        Log.e("lmy", "test2: " + data1);
        tv2.setText(data1);
        String data2 = RSAUtils.decryptToStrByPrivate(data1, keyPair.getPrivate());
        tv3.setText(data2);
        Log.e("lmy", "test2: " + Base64.encodeToString(keyPair.getPublic().getEncoded(), Base64.DEFAULT));
        Log.e("lmy", "test2: " + Base64.encodeToString(keyPair.getPrivate().getEncoded(), Base64.DEFAULT));
//        tv5.setText(new String(keyPair.getPublic().getEncoded()));
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
