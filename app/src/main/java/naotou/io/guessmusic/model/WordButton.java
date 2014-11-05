package naotou.io.guessmusic.model;

import android.widget.Button;

/**
 * Created by Jack_Cooper on 2014/11/5.
 * 文字按钮
 */
public class WordButton {

    private int mIndex;//文字的索引
    private String mWordString;//按钮当前的文字
    private boolean mIsVisible; //按钮显示或者隐藏

    private Button mViewButton;  //按钮

    public void WordButton(){
        mIsVisible=true;
        mWordString = "";


    }
}
