package com.elf.elfstudent.CustomUI;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.elf.elfstudent.Utils.FontCache;

/**
 * Created by ELF on 04-01-2017.
 *
 * The Title Textview which is present in Toolbar
 */

public class titleTextview extends TextView {
    public titleTextview(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public titleTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public titleTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/grand_hotel.otf", context);
        setTypeface(customFont);
    }
}
