package naotou.io.guessmusic.model;

import android.widget.Button;

/**
 * Created by Jack_Cooper on 2014/11/5.
 * 文字按钮
 */
public class WordButton {

    public int mIndex;//文字的索引
    public String mWordString;//按钮当前的文字
    public boolean mIsVisible; //按钮显示或者隐藏

    public Button mViewButton;  //按钮

    public void WordButton(){
        mIsVisible=true;
        mWordString = "";
    }
}
