package jp.rubi3.edgedrawable.app;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jp.rubi3.edgedrawable.EdgeDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeDrawable drawable = new EdgeDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.pattern));
        drawable.setEdgeInset(new Rect(
                getResources().getDimensionPixelSize(R.dimen.pattern_inset_left),
                getResources().getDimensionPixelSize(R.dimen.pattern_inset_top),
                getResources().getDimensionPixelSize(R.dimen.pattern_inset_right),
                getResources().getDimensionPixelSize(R.dimen.pattern_inset_bottom)));
        findViewById(R.id.imageView).setBackgroundDrawable(drawable);
        findViewById(R.id.imageView2).setBackgroundDrawable(drawable);
        findViewById(R.id.imageView3).setBackgroundDrawable(drawable);
    }
}
