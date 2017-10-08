package com.example.elad.pingpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by elad on 14/06/2017.
 */

public class board {
    float x;
    float y;
    float length;

    public board() {

    }

    public board(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void DrawBoard (Canvas canvas) {
        Paint pn1 = new Paint();
        pn1.setColor(Color.BLACK);
        pn1.setStrokeWidth(30);
        canvas.drawLine(x-length/2,y,x+length/2,y,pn1);
    }
}
