package com.gamess.playajoke.utils;

import android.util.Log;

/**
 * ASSIC码 数字范围48~57
 *         大写字母范围65~90
 *         小写字母范围97~122
 * 进制规定 1000=1k 1000k=1m 1000m=1aa..1000zz=1AA..
 * 输出规定 要求输出结果保留2位小数，如果有零则舍去
 */

public class BigDataTools {
    private int[] bigbuff;
    public void setData(String value){//4423758534451353843253144343215474331353843253144
        bigbuff = new int[100];
        for (int i = 0; i < bigbuff.length; i++){
            bigbuff[i] = -1;
        }
        int maxI = value.length()/3;
        if(value.length()%3 == 0) maxI-=1;
        for(int i = 0; i <= maxI; i++){
            if(value.length()-(i+1)*3 < 0){
                bigbuff[99 - i] = Integer.parseInt(value.substring(0, value.length() - i * 3));
            }else {
                bigbuff[99 - i] = Integer.parseInt(value.substring(value.length() - (i + 1) * 3, value.length() - i * 3));
            }
        }

//        StringBuilder testsb = new StringBuilder();
//        for (int i = 0; i < bigbuff.length; i++){
//            if(bigbuff[i] != -1) {
//                testsb.append(bigbuff[i]);
//            }
//        }
//        Log.e("bigdata", "setData: " + testsb.toString());
    }

    public String mult(int value){//2~9
        StringBuilder sb = new StringBuilder();
        for(int i = 99; bigbuff[i] != -1; i--){
            int temp = bigbuff[i]*value;
            bigbuff[i] = temp%1000;
            addData();
        }
        return sb.toString();
    }


    public void addData(){

    }
}
