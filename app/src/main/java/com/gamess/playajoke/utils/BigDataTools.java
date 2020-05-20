package com.gamess.playajoke.utils;

import android.util.Log;

/**
 * ASSIC码 数字范围48~57
 * 大写字母范围65~90
 * 小写字母范围97~122
 * 进制规定 1000=1k 1000k=1m 1000m=1aa..1000zz=1AA..
 * 输出规定 要求输出结果保留2位小数，如果有零则舍去
 */

//将大数每3位存于一个数组内
public class BigDataTools {
    private int[] bigbuff;
    private int point;
    public BigDataTools setData(String value) {//4423758534451353843253144343215474331353843253144
        bigbuff = new int[100];
        for (int i = 0; i < bigbuff.length; i++) {
            bigbuff[i] = -1;
        }
        int maxI = value.length() / 3;
        if (value.length() % 3 == 0) maxI -= 1;
        for (int i = 0; i <= maxI; i++) {
            if (value.length() - (i + 1) * 3 < 0) {
                bigbuff[99 - i] = Integer.parseInt(value.substring(0, value.length() - i * 3));
            } else {
                bigbuff[99 - i] = Integer.parseInt(value.substring(value.length() - (i + 1) * 3, value.length() - i * 3));
            }
        }
        return this;
    }

    //大数进行乘法  先进行分组的相乘并记录，再进行依次满100进位
    public BigDataTools mult(int value) {//2~9
        //对位相乘
        for (int i = 99; bigbuff[i] != -1; i--) {
            bigbuff[i] *= value;
        }
        //整理数据进位 需要先乘后进位
        for (int i = 99; bigbuff[i] != -1; i--) {
            addData(i, bigbuff[i]);
        }
        return this;
    }


    //循环进位
    private void addData(int i, int temp) {
        if (temp > 999) {
            bigbuff[i] = temp % 1000;
            if (bigbuff[i - 1] == -1) {
                temp = temp / 1000;
            } else {
                temp = bigbuff[i - 1] + temp / 1000;
            }
            addData(i - 1, temp);
        } else {
            bigbuff[i] = temp;
        }
    }

    //输出计算后的结果
    public void printResult() {
        StringBuilder sb = new StringBuilder();
        boolean firstIndex = true;
        for (int i = 0; i < bigbuff.length; i++) {
            if (bigbuff[i] != -1) {
                //除第一位不需要添加0，后面位置需要补0
                if (firstIndex) {
                    sb.append(bigbuff[i]);
                    firstIndex = false;
                } else {
                    if (bigbuff[i] < 10) {
                        sb.append("00").append(bigbuff[i]);
                    } else if (bigbuff[i] < 100) {
                        sb.append("0").append(bigbuff[i]);
                    } else if (bigbuff[i] == 0) {
                        sb.append("000");
                    } else {
                        sb.append(bigbuff[i]);
                    }
                }
            }
        }
        if(point != 0) {
            String result = sb.toString();
            String result2 = result.substring(0, result.length()-point);
            String result3 = result.substring(result.length()-point);
            int sign = 0;
            for(int i = point-1; i >= 0; i--){
                if(result3.charAt(i) == '0'){
                    sign++;
                }else{
                    break;
                }
            }
            if(sign == point){
                Log.e("bigdata", "result: " + result2);
            }else {
                String result4 = result3.substring(0, point-sign);
                Log.e("bigdata", "result: " + result2+"."+result4);
            }

            point = 0;
        }else{
            Log.e("bigdata", "result: " + sb.toString());
        }
    }

    public BigDataTools add(int value) {
        return add(String.valueOf(value));
    }

    public BigDataTools add(String val) {
        //初始化传入数值
        int[] bigbufff = new int[100];
        for (int i = 0; i < 100; i++) {
            bigbufff[i] = -1;
        }
        int maxI = val.length() / 3;
        if (val.length() % 3 == 0) maxI -= 1;
        for (int i = 0; i <= maxI; i++) {
            if (val.length() - (i + 1) * 3 < 0) {
                bigbufff[99 - i] = Integer.parseInt(val.substring(0, val.length() - i * 3));
            } else {
                bigbufff[99 - i] = Integer.parseInt(val.substring(val.length() - (i + 1) * 3, val.length() - i * 3));
            }
        }

        //进行对位相加
        for (int i = 99; i >= 0; i--) {
            if (bigbufff[i] == -1 && bigbuff[i] == -1) {
                break;
            }
            if (bigbuff[i] == -1) {
                bigbuff[i] = bigbufff[i];
                continue;
            } else if (bigbufff[i] == -1) {
                continue;
            }
            bigbuff[i] += bigbufff[i];
            addData(i, bigbuff[i]);
        }

        //整理数据进位
//        for (int i = 99; bigbuff[i] != -1; i--) {
//            addData(i, bigbuff[i]);
//        }

        return this;
    }

    public BigDataTools sub(int value) {
        return sub(String.valueOf(value));
    }

    private BigDataTools sub(String val) {
        //初始化传入数值
        int[] bigbufff = new int[100];
        for (int i = 0; i < 100; i++) {
            bigbufff[i] = -1;
        }
        int maxI = val.length() / 3;
        if (val.length() % 3 == 0) maxI -= 1;
        for (int i = 0; i <= maxI; i++) {
            if (val.length() - (i + 1) * 3 < 0) {
                bigbufff[99 - i] = Integer.parseInt(val.substring(0, val.length() - i * 3));
            } else {
                bigbufff[99 - i] = Integer.parseInt(val.substring(val.length() - (i + 1) * 3, val.length() - i * 3));
            }
        }

        //对位相减
        for (int i = 99; i >= 0; i--) {
            if (bigbufff[i] == -1 && bigbuff[i] == -1) {
                break;
            }
            if (bigbufff[i] == -1) {
                continue;
            }
            bigbuff[i] -= bigbufff[i];

            //整理数据借位
            subData(i, bigbuff[i]);
        }

        return this;
    }

    private void subData(int i, int temp) {
        if (temp < 0) {
            bigbuff[i] += 1000;
            bigbuff[i - 1] -= 1;
            temp = bigbuff[i - 1];
            subData(i - 1, temp);
        } else {
            bigbuff[i] = temp;
        }
    }

    public BigDataTools divide(int value){
        String val = String.valueOf(1.0/value);
        Log.e("lmy", "divide: " + val);
        try {
            point = val.split("\\.")[1].length();
        }catch (Exception e){

        }
        int dd = 1;
        for(int i = 1; i <= point; i++){
            dd *= 10;
        }
        int realVal = (int) (Float.parseFloat(val) * dd);
        mult(realVal);
        return this;
    }
}
