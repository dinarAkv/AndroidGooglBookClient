package com.bignerdranch.android.googlebookclient.Helper;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by usr43387 on 19.12.2016.
 *
 * This class solve problems connected with different size of devises.
 *
 */

public class ViewSize
{

    /**
     * Function return width of screen in dp.
     * @param context - view context.
     * @return - width of screen in dp.
     */
    public static float getWidthOfDevice(Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        return dpWidth;
    }






}
