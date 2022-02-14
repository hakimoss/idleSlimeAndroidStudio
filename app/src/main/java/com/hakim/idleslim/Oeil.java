package com.hakim.idleslim;

import static com.hakim.idleslim.GameView.screenRatioX;
import static com.hakim.idleslim.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Oeil extends Personnages{

    public int speed = 20;
    int x, y, width, height;
    Bitmap oeil1, oeil2, oeil3, oeil4;


    public Oeil(int x, int y, Resources res) {
        super(x, y, 25, 32);

        oeil1 = BitmapFactory.decodeResource(res, R.drawable.oeil_1gauche);
        oeil2 = BitmapFactory.decodeResource(res, R.drawable.oeil_2gauche);
        oeil3 = BitmapFactory.decodeResource(res, R.drawable.oeil_3gauche);
        oeil4 = BitmapFactory.decodeResource(res, R.drawable.oeil_4gauche);

        width = oeil1.getWidth();
        height = oeil1.getHeight();

        width *= 1.5;
        height *= 2.5;

        width *= (int) screenRatioX;
        height *= screenRatioY;

        oeil1 = Bitmap.createScaledBitmap(oeil1, width,(int) height, false);
        oeil2 = Bitmap.createScaledBitmap(oeil2, width,(int) height, false);
        oeil3 = Bitmap.createScaledBitmap(oeil3, width,(int) height, false);
        oeil4 = Bitmap.createScaledBitmap(oeil4, width,(int) height, false);

        this.y = y / 2;
        this.x = (int) (64 * screenRatioX);



    }

    Bitmap getOeil() {
        if(compteurMarche < 2) {
            compteurMarche++;
            return oeil1;
        } else if(compteurMarche >= 2 && compteurMarche < 4) {
            compteurMarche++;
            return oeil2;
        } else if(compteurMarche >= 4 && compteurMarche < 6) {
            compteurMarche++;
            return oeil3;
        } else if(compteurMarche >= 6 && compteurMarche < 8) {
            compteurMarche++;
            if(compteurMarche == 7) {
                compteurMarche = 0;
            }
            return oeil4;
        } else {
            return null;
        }


    }

    Rect getCollisionShape() {
        System.out.println("oeil contact : " + x);
        return new Rect(x, y, x+width, y+height);
    }

}
