package naotou.io.guessmusic.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Jack_Cooper on 2014/11/5.
 */
public class MyUtils {
    public static View getView(Context context,int layoutId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutId, null);
        return view;
    }
}
