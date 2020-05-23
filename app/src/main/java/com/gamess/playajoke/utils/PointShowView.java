package com.gamess.playajoke.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PointShowView extends View {
    private List<Float> xs = new ArrayList<>();
    private List<Float> ys;


    public PointShowView(Context context) {
        super(context);
    }

    public PointShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(10);
        for (int i = 0; i < xs.size(); i++) {
            canvas.drawPoint(xs.get(i), ys.get(i), p);
        }
    }

    public PointShowView setXX(float xx, int index){
        this.xs.set(index, xx);
        return this;
    }

    public PointShowView setYY(float yy, int index){
        this.ys.set(index, yy);
        return this;
    }

    public void show(){
        invalidate();
    }

    public void setInit(List<Float> xs, List<Float> ys) {
        this.xs = xs;
        this.ys = ys;
    }
}
