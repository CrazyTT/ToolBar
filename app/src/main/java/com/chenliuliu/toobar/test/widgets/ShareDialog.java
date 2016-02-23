package com.chenliuliu.toobar.test.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chenliuliu.toobar.test.R;


/**
 * Created by liuliuchen on 15/12/7.
 */
public class ShareDialog extends Dialog {
    TextView mTxtWeixin;
    TextView mTxtTimeLine;
    private ShareDialogCallBack mShareDialogCallBack;
    boolean isShare = false;

    public ShareDialog(Context context, int themeResId, ShareDialogCallBack shareDialogCallBack, boolean isShare) {
        super(context, themeResId);
        this.mShareDialogCallBack = shareDialogCallBack;
        this.isShare = isShare;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_share_dialog);
        mTxtWeixin = (TextView) findViewById(R.id.txt_weixin);
        mTxtTimeLine = (TextView) findViewById(R.id.txt_time_line);


        mTxtWeixin = (TextView) findViewById(R.id.txt_weixin);
        mTxtTimeLine = (TextView) findViewById(R.id.txt_time_line);

        if (!isShare) {
            mTxtWeixin.setText("微信");
            mTxtTimeLine.setText("朋友圈");
        }


        findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog.this.dismiss();
            }
        });
        findViewById(R.id.share_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareDialogCallBack.shareTowexin();
                ShareDialog.this.dismiss();
            }
        });
        findViewById(R.id.share_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareDialogCallBack.shareToTime();
                ShareDialog.this.dismiss();
            }
        });
    }

    public interface ShareDialogCallBack {
        void shareTowexin();

        void shareToTime();
    }
}
