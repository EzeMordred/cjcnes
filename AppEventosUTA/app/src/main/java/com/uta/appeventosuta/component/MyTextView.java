package com.uta.appeventosuta.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        this.setText(Html.fromHtml(this.getText().toString()), TextView.BufferType.SPANNABLE);
    }

}
