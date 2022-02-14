package com.hakim.idleslim;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {

    int x=0, y=0;
    Bitmap background;
    public float height;

    Background(int screenX, int screenY, Resources res) {

        height = screenY/3;
        background = BitmapFactory.decodeResource(res, R.drawable.stage_forest);
        background = Bitmap.createScaledBitmap(background, screenX, screenY/3, false);

    }
}
