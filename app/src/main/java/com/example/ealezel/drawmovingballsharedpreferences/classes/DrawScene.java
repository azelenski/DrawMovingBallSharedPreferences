package com.example.ealezel.drawmovingballsharedpreferences.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.ealezel.drawmovingballsharedpreferences.R;

/**
 * Created by ealezel on 28.10.2015.
 */
public class DrawScene extends View {


    private Paint canvasPaint;

    private int x;
    private int y;
    private int fixed_x;
    private int fixed_y;
    private int circleRadius;

    private int dx = 10;
    private int dy = 10;
    private final int FRAME_RATE = 30;

    private int scene_width;
    private int scene_height;

    Bitmap ballImage;
    Bitmap scaledBitmap;

    private Handler h;

    public static final String PREFS_NAME = "BallSharedPrefsFile";

    public DrawScene(Context context, int start_x, int start_y) {
        super(context);
        fixed_x = start_x;
        fixed_y = start_y;
        x = start_x;
        y = start_y;


        canvasPaint = new Paint();
        canvasPaint.setStyle(Paint.Style.FILL);
        canvasPaint.setColor(Color.YELLOW);

        ballImage = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        scaledBitmap = Bitmap.createScaledBitmap(ballImage, 100, 100, false);

        h = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    @Override
    protected void onDraw(Canvas sceneCanvas) {
        super.onDraw(sceneCanvas);

        sceneCanvas.drawPaint(canvasPaint);
        scene_width = this.getWidth();
        scene_height = this.getHeight();

        circleRadius = scaledBitmap.getWidth() / 2;
        sceneCanvas.drawBitmap(scaledBitmap, x - circleRadius, y - circleRadius, null);


        x += dx;
        y += dy;

        if ((x > scene_width - circleRadius) || (x < circleRadius)) {
            dx = dx * -1;
        }

        if ((y > scene_height - circleRadius) || (y < circleRadius)) {
            dy = dy * -1;
        }

        h.postDelayed(r, FRAME_RATE);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int)event.getX();
        y = (int) event.getY();
        return true;
    }


    public int getPositionX()
    {
        return x;
    }
    public int getPositionY()
    {
        return y;
    }
}


