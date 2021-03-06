package com.android.deskclock.widgetlayout;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.android.deskclock.DeskClockApplication;
import com.android.deskclock.R;
import com.android.util.Utils;
import com.huawei.android.app.WallpaperManagerEx;
import java.lang.ref.WeakReference;

public class BlurBitmapLinearLayout extends LinearLayout {
    private Drawable mDrawable;
    private LocalHandler mHandler;
    private WallpaperManager mWallpaperManager;

    static class LocalHandler extends Handler {
        private WeakReference<BlurBitmapLinearLayout> mContextWR;

        public LocalHandler(BlurBitmapLinearLayout context) {
            this.mContextWR = new WeakReference(context);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BlurBitmapLinearLayout bLayout = (BlurBitmapLinearLayout) this.mContextWR.get();
            if (bLayout != null) {
                switch (msg.what) {
                    case 1:
                        bLayout.setBlurWallpaperBackground();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public BlurBitmapLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlurBitmapLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        this.mWallpaperManager = (WallpaperManager) getContext().getSystemService("wallpaper");
        this.mHandler = new LocalHandler(this);
    }

    private void setBlurWallpaperBackground() {
        int[] loc = new int[2];
        getLocationOnScreen(loc);
        Rect rect = new Rect(loc[0], loc[1], loc[0] + getWidth(), loc[1] + getHeight());
        if (this.mWallpaperManager == null || rect.left < getMinimumWidth() / 2) {
            if (rect.left < 0) {
            }
            if (this.mWallpaperManager != null && rect.width() > 0 && rect.height() > 0) {
                recycleBackBitmap();
                Bitmap bm = WallpaperManagerEx.getBlurBitmap(this.mWallpaperManager, rect);
                if (bm != null) {
                    this.mDrawable = new BitmapDrawable(ImageToneUtils.handleImage(bm, ImageToneUtils.calculateSaturation(177)));
                    this.mDrawable.setColorFilter(getResources().getColor(R.color.deskclock_background), Mode.DARKEN);
                    this.mDrawable = new BitmapDrawable(ImageToneUtils.coverImage(((BitmapDrawable) this.mDrawable).getBitmap(), Utils.isLandScreen(DeskClockApplication.getDeskClockApplication())));
                    setBackgroundDrawable(this.mDrawable);
                    return;
                }
                return;
            }
            return;
        }
        rect.left = 0;
        rect.right = getWidth();
        if (this.mWallpaperManager != null) {
        }
    }

    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mDrawable != null) {
            this.mDrawable.setCallback(null);
        }
        setBackgroundDrawable(null);
        recycleBackBitmap();
    }

    private void recycleBackBitmap() {
        if (this.mDrawable != null) {
            this.mDrawable.clearColorFilter();
            Bitmap bitmap = ((BitmapDrawable) this.mDrawable).getBitmap();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
            this.mDrawable = null;
        }
    }
}
