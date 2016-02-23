package com.chenliuliu.toobar.test.activitys;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.chenliuliu.toobar.test.R;
import com.chenliuliu.toobar.test.widgets.BrowserLayout;
import com.chenliuliu.toobar.test.widgets.ShareDialog;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.umeng.analytics.MobclickAgent;


public class MainActivity extends ActionBarActivity {
    BrowserLayout mBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mBrowser = (BrowserLayout) findViewById(R.id.brower);
        setSupportActionBar(toolbar);
        //屏蔽系统的title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.mine);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "我是陈柳柳", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("我的微博", "我的github", "我的android巴士");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (position == 0) {
                    mBrowser.loadUrl("http://weibo.com/u/2097805097");
                } else if (position == 1) {
                    mBrowser.loadUrl("https://github.com/CrazyTT");
                } else if (position == 2) {
                    mBrowser.loadUrl("http://www.apkbus.com/forum.php?mod=viewthread&tid=160689&highlight=一个demo搞定所有控件");
                }
            }
        });
        mBrowser.loadUrl("http://weibo.com/u/2097805097");
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_share:
                    ShareDialog shareDialog = new ShareDialog(MainActivity.this, R.style.shareDialog, new ShareDialog.ShareDialogCallBack() {
                        @Override
                        public void shareTowexin() {
                            ShareUtils.shareToWeChat(MainActivity.this, MainActivity.this.getString(R.string.string_share_weixin_title), MainActivity.this.getString(R.string.string_share_weixin_content), getString(R.string.shareUrl), false, BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.launcher));
                        }

                        @Override
                        public void shareToTime() {
                            ShareUtils.shareToWeChat(MainActivity.this, MainActivity.this.getString(R.string.string_share_weixin_title), MainActivity.this.getString(R.string.string_share_weixin_content), getString(R.string.shareUrl), true, BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.launcher));
                        }
                    }, true);
                    Window w = shareDialog.getWindow();
                    WindowManager.LayoutParams lp = w.getAttributes();
                    lp.x = 0;
                    final int cMakeBottom = -1000;
                    lp.y = cMakeBottom;
                    lp.gravity = Gravity.BOTTOM;
                    shareDialog.onWindowAttributesChanged(lp);
                    shareDialog.setCanceledOnTouchOutside(true);
                    shareDialog.show();
                    break;
                case R.id.action_about:
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.action_logout:
                    MainActivity.this.finish();
                    break;
            }
            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }
}
