package com.app.resume;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MovingGraph extends View {
    private int width;
    private int height;

    public MovingGraph(Context context) {
        super(context);
    }

    public MovingGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    Paint PAINT_WIDTH_LINE = new Paint();
    Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = canvas.getWidth();
        height = canvas.getHeight();
        PAINT_WIDTH_LINE.setColor(Color.BLACK);
        int SW = width / SECTOR_WIDTH;
        int PART = SW;
        int s = SW;
        for (int i = 0; i <= SW; i++) {
            canvas.drawLine(width - PART, 0, width - PART, height, PAINT_WIDTH_LINE);
            PART = PART + s;
        }

        int SH = height / SECTOR_HEIGHT - 10;
        int PART1 = SH;
        int s1 = SH;
        for (int i = 0; i <= SH; i++) {
            canvas.drawLine(0, PART1, width, PART1, PAINT_WIDTH_LINE);
            PART1 = PART1 + s1;
        }
        Path path = new Path();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        path.moveTo(34, 259);
        path.cubicTo(68, 151, 286, 350, 336, 252);
        canvas.drawPath(path, paint);
    }


    private int SECTOR_WIDTH = 15;
    private int SECTOR_HEIGHT = 6;
}
