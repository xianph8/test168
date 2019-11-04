package com.test.test168.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.test.test168.R;
import com.test.test168.adapter.TestCustomBehaviorAdapter;
import com.xian.common.utils.StatusBarUtil;

import java.util.ArrayList;

public class TestCustomBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_behavior);
        RecyclerView r = findViewById(R.id.recyclerView);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(new TestCustomBehaviorAdapter(new ArrayList<String>() {
            {
                add("test1");
                add("test2");
                add("test1");
                add("test4");
                add("tes541");
                add("t5t1");
                add("te5t1");
                add("tes34");
                add("test1");
                add("test6");
                add("test7");
                add("tes31");
                add("test7");
                add("test1");
                add("test8");
                add("test1");
                add("last7");
                add("last8");
                add("last9");
                add("last10");
            }
        }));
        StatusBarUtil.transparencyBar(this);
        StatusBarUtil.statusBarLightMode(this);

        StatusBarUtil.setContentSystemBarPaddingTop(findViewById(R.id.cl_header_layout));

        ImageView targetImage = (ImageView) findViewById(R.id.iv_header_bg);

        //headerLayout.setBackgroundColor(ContextCompat.getColor(headerLayout.getContext(), R.color.def_bg));


        //ImageLoader.display(this, R.drawable.national_day, findViewById(R.id.iv_index_header_bg));
//        ImageLoader.glide(this).load(R.drawable.national_day)
//                .addListener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable drawable, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        int dwidth = drawable.getIntrinsicWidth();
//                        int dheight = drawable.getIntrinsicHeight();
//                        int vwidth = targetImage.getWidth();
//                        int vheight = targetImage.getHeight();
//                        float scale = 1.0f;
//                        float dx = 0, dy = 0;
//                        if (dwidth * vheight > vwidth * dheight) {
//                            scale = (float) vheight / (float) dheight;
//                            dx = (vwidth - dwidth * scale) * 0.5f + 0.5f;
//                        } else {
//                            scale = (float) vwidth / (float) dwidth;
//                            dy = (vheight - dheight * scale) * 0.5f + 0.5f;
//                        }
//                        if (dwidth * scale < 2 * vwidth) {
//                            scale = (float) vwidth / (2.0f * dwidth);
//                            dx = -dwidth / 2;
//                            dy = (vheight - dheight * scale) * 0.5f + 0.5f;
//                        }
//                        Matrix matrix = new Matrix();
//                        matrix.setScale(scale, scale);
//                        matrix.postTranslate(dx, dy);
//                        targetImage.setImageMatrix(matrix);
//                        targetImage.setImageDrawable(drawable);
//                        targetImage.setVisibility(View.VISIBLE);
//                        return false;
//                    }
//                })
//                .into(targetImage);
    }
}
