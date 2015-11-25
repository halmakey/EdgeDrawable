package jp.rubi3.edgedrawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by kikuchi on 2015/11/25.
 */
public class EdgeDrawable extends Drawable {
    private Bitmap bitmap;
    private Paint paint;
    private Rect edgeInset;
    private Rect[] srcs;
    private Rect[] dsts;

    public EdgeDrawable(@NonNull Bitmap bitmap) {
        this.bitmap = bitmap;
        this.paint = new Paint();
        setEdgeInset(new Rect(0, 0, 0, 0));
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < 9; i++) {
            canvas.drawBitmap(bitmap, srcs[i], dsts[i], paint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return paint.getAlpha();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public ColorFilter getColorFilter() {
        return paint.getColorFilter();
    }

    @Override
    public int getOpacity() {
        return (bitmap.hasAlpha() || paint.getAlpha() < 255) ?
                PixelFormat.TRANSLUCENT : PixelFormat.OPAQUE;    }

    public Rect getEdgeInset() {
        return this.edgeInset;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        dsts = generateRects(
                bounds.left, bounds.left + edgeInset.left, bounds.right - edgeInset.right, bounds.right,
                bounds.top, bounds.top + edgeInset.top, bounds.bottom - edgeInset.bottom, bounds.bottom
        );
    }

    public void setEdgeInset(Rect edgeInset) {
        this.edgeInset = edgeInset;
        srcs = generateRects(
                0, edgeInset.left, bitmap.getWidth() - edgeInset.right, bitmap.getWidth(),
                0, edgeInset.top, bitmap.getHeight() - edgeInset.bottom, bitmap.getHeight());
        this.onBoundsChange(this.getBounds());
        invalidateSelf();
    }

    private Rect[] generateRects(int... points) {
        Rect[] results = new Rect[9];
        for (int i = 0; i < 9; i++) {
            int x = i % 3, y = i / 3;
            results[i] = new Rect(points[x], points[y + 4], points[x + 1], points[y + 5]);
        }
        return results;
    }

    @Override
    public int getIntrinsicWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return bitmap.getHeight();
    }
}
