package com.uta.appeventosuta.control;

import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;

public class Controller {

    public static long eighteenYears = 567648000000L;

    public static SpannableStringBuilder getBigMessage(String message) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(message);
        ssBuilder.setSpan(new RelativeSizeSpan(1.35f), 0, message.length(), 0);
        return ssBuilder;
    }

}
