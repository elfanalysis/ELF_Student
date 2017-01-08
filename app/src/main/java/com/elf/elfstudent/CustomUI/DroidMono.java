package com.elf.elfstudent.CustomUI;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.elf.elfstudent.Utils.FontCache;

/**
 * Created by ELF on 07-01-2017.
 *
 */

public class DroidMono extends TextView {
    public DroidMono(Context context) {
        super(context);
        applyCustomFont(context);


    }

    public DroidMono(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public DroidMono(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }
    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/fall_Sky_cond.otf", context);
        setTypeface(customFont);
    }
}
