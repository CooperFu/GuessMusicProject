package naotou.io.guessmusic.myui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import naotou.io.guessmusic.model.WordButton;

/**
 * Created by Jack_Cooper on 2014/11/5.
 * 自定义gridView分析:
 *  要实现的功能是,在猜歌下面自定义一个三行八列的gridView
 *  一共24个button,上面的文字是动态生成的.
 */
public class MyGridView extends GridView{

    private ArrayList<WordButton> mArrayList = new ArrayList<WordButton>();
    private MyGridAdapter myGridAdapter;
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //关联自定义adapter.
        myGridAdapter = new MyGridAdapter();
        this.setAdapter(myGridAdapter);
    }
    class MyGridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }


}
