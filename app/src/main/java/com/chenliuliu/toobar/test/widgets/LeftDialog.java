package com.chenliuliu.toobar.test.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.chenliuliu.toobar.test.R;


/**
 * Created by liuliuchen on 15/12/7.
 */
public class LeftDialog extends Dialog {

    public LeftDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_left_dialog);
    }

}
