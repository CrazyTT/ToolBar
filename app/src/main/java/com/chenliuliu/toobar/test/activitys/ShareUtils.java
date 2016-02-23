package com.chenliuliu.toobar.test.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import com.chenliuliu.toobar.test.R;
import com.chenliuliu.toobar.test.widgets.Constants;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class ShareUtils {

    private static IWXAPI api;

    public static void shareToSina(String msg, Context context) {
        Uri uri = Uri.parse("" + msg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param title
     * @param description
     * @param weburl
     * @param isTimeLine
     * @param bitmap
     */
    public static void shareToWeChat(Context context, String title, String description, String weburl, boolean isTimeLine, Bitmap bitmap) {
        if (getIWXPI(context, Constants.APP_ID).isWXAppInstalled()) {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = weburl;
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = title;
            msg.description = description;
            msg.setThumbImage(bitmap);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = "webpage" + System.currentTimeMillis();
            req.message = msg;
            req.scene = isTimeLine ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
            getIWXPI(context, Constants.APP_ID).sendReq(req);
        } else {
            Toast.makeText(context, R.string.no_wechat, Toast.LENGTH_LONG).show();
        }
    }

    public static void shareToFacebook(String msg, Context context) {
        Uri uri = Uri.parse("" + msg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public static void shareToTwitter(String msg, Context context) {
        Uri uri = Uri.parse("" + msg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public static void shareToLine() {

    }

    public static IWXAPI getIWXPI(Context context, String appId) {
        if (api == null) {
            synchronized (ShareUtils.class) {
                if (api == null) {
                    api = WXAPIFactory.createWXAPI(context, appId, true);
                    api.registerApp(appId);
                    return api;
                }
            }
        }
        return api;
    }
}
