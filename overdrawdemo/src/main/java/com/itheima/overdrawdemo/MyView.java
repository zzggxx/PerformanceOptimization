package com.itheima.overdrawdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 自定义控件过渡绘制案例
 */

public class MyView extends FrameLayout {
    private int[] ids = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};
    private Bitmap[] imgs = new Bitmap[6];

    private Paint paint;


    public MyView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        for (int i = 0; i < 6; i++) {
            imgs[i] = BitmapFactory.decodeResource(getResources(), ids[i]);
        }

        paint = new Paint();
        paint.setAntiAlias(true);

        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 相邻两张牌错开20像素

        /*for (int i = 0; i < 6; i++) {
            canvas.drawBitmap(imgs[i],i*20,0,paint);
        }*/

        // Canvas clipRect
        // 仅绘制一张牌
//        canvas.clipRect(0,0,20,imgs[0].getHeight());// 裁剪出一块矩形区域，对于用户来说，大王这张牌仅能看到这块区域
//        canvas.drawBitmap(imgs[0],0,0,paint);

        for (int i = 0; i < 6; i++) {
            canvas.save();
            if(i<5) {
                canvas.clipRect(i * 20, 0, (i + 1) * 20, imgs[i].getHeight());
            }else if(i==5){
                canvas.clipRect(i * 20, 0, i * 20+imgs[i].getWidth(), imgs[i].getHeight());
            }
            canvas.drawBitmap(imgs[i],i*20,0,paint);
            canvas.restore();
        }

    }
}
