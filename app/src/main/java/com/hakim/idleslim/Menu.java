package com.hakim.idleslim;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Menu {

    public int x=0, y=0;

    Bitmap menu1,menu2,menu3,menu4;

    Menu(int screenX, int screenY, Resources res) {

        menu1 = BitmapFactory.decodeResource(res, R.drawable.menu1);
        menu1 = Bitmap.createScaledBitmap(menu1, screenX, (screenY/3) * 2, false);
    }



}
