package com.huawei.systemmanager.emui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class EmuiBackgroundLinearLayout extends LinearLayout {
    private static final String SELECTOR_BACKGROUND = "@*androidhwext:drawable/item_background_emui";

    public EmuiBackgroundLinearLayout(Context context) {
        super(context);
        setStyleForEmui();
    }

    public EmuiBackgroundLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStyleForEmui();
    }

    public EmuiBackgroundLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setStyleForEmui();
    }

    private void setStyleForEmui() {
        Resources resources = getResources();
        if (resources != null) {
            int themeID = resources.getIdentifier("@*androidhwext:drawable/item_background_emui", null, null);
            if (themeID != 0) {
                setBackgroundResource(themeID);
            }
        }
    }
}
