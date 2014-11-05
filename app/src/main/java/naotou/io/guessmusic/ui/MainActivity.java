package naotou.io.guessmusic.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import naotou.io.guessmusic.R;
import naotou.io.guessmusic.model.WordButton;
import naotou.io.guessmusic.myui.MyGridView;

public class MainActivity extends Activity {
    //盘片相关动画
    private Animation mPanAnim;
    private LinearInterpolator mPanLin; //LinearInterpolator是线性的控制动画速度的类
    //竿开始
    private Animation mBarInAnim;
    private LinearInterpolator mBarInLin;
    //竿结束
    private Animation mBarOutAnim;
    private LinearInterpolator mBarOutLin;

    //play按键处理
    private ImageButton mBtnPlayStart;
    private ImageView mViewPan;
    private ImageView mViewBar;
    private boolean isRunning = false;

    //文字框的容器
    private ArrayList<WordButton> mAllWords;
    private MyGridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        mBtnPlayStart = (ImageButton) findViewById(R.id.btn_play_start);
        mViewPan = (ImageView) findViewById(R.id.imageView1);
        mViewBar = (ImageView) findViewById(R.id.imageView2);
        mGridView = (MyGridView) findViewById(R.id.gridView);
        initAnimation();
        //初始化游戏数据
        initCurrentStateData();
    }

    private void initAnimation() {
        //初始化盘片动画
        mPanAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mPanLin = new LinearInterpolator();
        mPanAnim.setInterpolator(mPanLin);
        //监听盘片动画
        mPanAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewBar.setAnimation(mBarOutAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //初始化摇杆开始的动画
        mBarInAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_45);
        mBarInLin = new LinearInterpolator();
        mBarInAnim.setFillAfter(true);//动画结束的时候保持结束的位置
        mBarInAnim.setInterpolator(mBarInLin);
        //监听摇杆进去的动画
        mBarInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewPan.setAnimation(mPanAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //初始化摇杆结束的动画
        mBarOutAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_45_d);
        mBarOutLin = new LinearInterpolator();
        mBarOutAnim.setFillAfter(true); //动画结束的时候保持结束的位置
        mBarOutAnim.setInterpolator(mBarOutLin);
        //监听摇杆回去的动画
        mBarOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //当摇杆回去的时候 就代表一首歌播放完毕 就可以再次点击了.
                isRunning = false;
                mBtnPlayStart.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        mBtnPlayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlayButton();
            }
        });
    }

    /**
     * 这个方法是处理点击play的事件.
     */
    private void handlePlayButton() {
        //先竿子过去,然后唱片旋转,然后竿子回来,这就涉及到动画播放顺序的问题,所以得监听每一个动画
        if (mViewBar != null) {//防止空指针异常.
            if (!isRunning) {
                mViewBar.startAnimation(mBarInAnim);
                isRunning = true;
                mBtnPlayStart.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 当用户点击后退按键的时候 把所有声音 动画什么的都关闭掉.
     */
    @Override
    protected void onPause() {
        mViewPan.clearAnimation();
        mViewBar.clearAnimation();
        super.onPause();
    }
    private void initCurrentStateData(){
        // 获得数据
        mAllWords = initAllWord();
        // 更新数据 MyGridView
        mGridView.updateData(mAllWords);
    }
    private ArrayList<WordButton> initAllWord(){
        ArrayList<WordButton> list = new ArrayList<WordButton>();
        //todo 或者所有待选文字
        for (int i = 0; i < MyGridView.COUNT_WORD; i++) {
            WordButton button = new WordButton();
            button.mWordString = "威";
            list.add(button);
        }
        return list;
    }
}
