package com.gamess.playajoke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.gamess.playajoke.utils.AESUtils;
import com.gamess.playajoke.utils.BigDataTools;
import com.gamess.playajoke.utils.RSAUtils;

import java.security.KeyPair;

public class MainActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
//        tv4 = findViewById(R.id.tv4);
//        tv5 = findViewById(R.id.tv5);
//        tv6 = findViewById(R.id.tv6);

        //test();
        //test2();
        test3();

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
