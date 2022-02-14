package com.hakim.idleslim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private Background background1, background2;
    private Menu menu1;
    private Paint paint;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;

    private Random random;
    private Hero hero;
    private Oeil[] oeils;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        background2.x = screenX;

        menu1 = new Menu(screenX, screenY, getResources());

        hero = new Hero(screenX, screenY, getResources());

        paint = new Paint();

        oeils = new Oeil[10];


        for(int i = 0; i <  10; i++) {

            Oeil oeil = new Oeil(0, 0, getResources());
            oeil.x+=1000;
            oeils[i] = oeil;

        }
    }

    @Override
    public void run() {
        while(isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        background1.x -= (int) (5 * screenRatioX);
        background2.x -= (int) (5 * screenRatioX);

        if(background1.x + background1.background.getWidth() < 0) {background1.x = screenX;}
        if(background2.x + background2.background.getWidth() < 0) {background2.x = screenX;}

        for(Oeil oeil:oeils) {

            oeil.x -= oeil.speed;


            if(oeil.x + oeil.width < 0) {
                oeil.x = screenX;
            }

            if(Rect.intersects(oeil.getCollisionShape(), hero.getCollisionShape())) {
                System.out.println("contact !!!");
                Log.d("DEBUG", "contact !!!");
                screenRatioX = 0;
            }
            if(hero.x + hero.width >= oeil.x) {
                System.out.println("contact !!!");
                Log.d("DEBUG", "contact !!!");
                screenRatioX = 0;
                oeil.speed = 0;
            }
        }
    }

    private void draw() {

        if(getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            //paint background
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
            //paint menu
            canvas.drawBitmap(menu1.menu1, menu1.x, background1.height, paint);
            //paint oeil
            for(Oeil oeil: oeils) {
                canvas.drawBitmap(oeil.getOeil(), oeil.x, background1.height - hero.height*2, paint);
            }

            // paint hero
            canvas.drawBitmap(hero.getHero(), hero.width - hero.x, background1.height - hero.height*2, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    private void sleep() {
        try {
            thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
