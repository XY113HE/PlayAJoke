package com.gamess.playajoke.utils;

import android.util.Log;

/**
 * ASSIC码 数字范围48~57
 *         大写字母范围65~90
 *         小写字母范围97~122
 * 进制规定 1000=1k 1000k=1m 1000m=1aa..1000zz=1AA..
 * 输出规定 要求输出结果保留2位小数，如果有零则舍去
 */

//将大数每3位存于一个数组内
public class BigDataTools {
    private int[] bigbuff;
    public BigDataTools setData(String value){//4423758534451353843253144343215474331353843253144
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
        return this;
    }

    //大数进行乘法  先进行分组的相乘并记录，再进行依次满100进位
    public BigDataTools mult(int value){//2~9
        for(int i = 99; bigbuff[i] != -1; i--){
            bigbuff[i] *= value;
        }
        for(int i = 99; bigbuff[i] != -1; i--){
            addData(i, bigbuff[i]);
        }
        return this;
    }


    //循环进位
    private void addData(int i, int temp){
        if(temp > 999){
            bigbuff[i] = temp%1000;
            if(bigbuff[i-1] == -1) {
                temp = temp/1000;
            }else {
                temp = bigbuff[i - 1] + temp / 1000;
            }
            addData(i-1, temp);
        }else{
            bigbuff[i] = temp;
        }
    }

    //输出计算后的结果
    public void printResult(){
        StringBuilder sb = new StringBuilder();
        boolean firstIndex = true;
        for (int i = 0; i < bigbuff.length; i++){
            if(bigbuff[i] != -1) {
                //除第一位不需要添加0，后面位置需要补0
                if(firstIndex) {
                    sb.append(bigbuff[i]);
                    firstIndex = false;
                }else{
                    if(bigbuff[i] < 10){
                        sb.append("00").append(bigbuff[i]);
                    }else if(bigbuff[i] < 100){
                        sb.append("0").append(bigbuff[i]);
                    }else if(bigbuff[i] == 0){
                        sb.append("000");
                    }else{
                        sb.append(bigbuff[i]);
                    }
                }
            }
        }
        Log.e("bigdata", "result: " + sb.toString());
    }
}
