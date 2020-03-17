package com.sinothk.QRCode.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinothk.QRCode.demo.tool.RxBarCode;
import com.sinothk.QRCode.demo.tool.RxQRCode;

/**
 * @author vondear
 */
public class ActivityCreateQRCode extends ActivityBase implements View.OnClickListener {

    private static android.os.Handler Handler = new Handler();
    private static Runnable mRunnable = null;

    ImageView mIvLinecode;
    ImageView mIvCode;
    ImageView mImageView1;
    TextView mTextView2;
    TextView mTvTimeSecond;
    LinearLayout mLlRefresh;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 60000:
                    initData();
                    break;
                default:
                    break;
            }
        }
    };
    private int time_second = 0;
    private int second = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qrcode);

        mIvLinecode = findViewById(R.id.iv_linecode);
        mIvCode = findViewById(R.id.iv_code);
        mImageView1 = findViewById(R.id.imageView1);
        mTextView2 = findViewById(R.id.textView2);
        mTvTimeSecond = findViewById(R.id.tv_time_second);
        mLlRefresh = findViewById(R.id.ll_refresh);

        mLlRefresh.setOnClickListener(this);

        initData();

        AuthCode(mTvTimeSecond, second);
    }

    private void AuthCode(final TextView view, final int timeSecond) {

        mRunnable = new Runnable() {
            int mSumNum = timeSecond;

            @Override
            public void run() {
                Handler.postDelayed(mRunnable, 1000);
                view.setText(mSumNum + "");
                view.setEnabled(false);
                mSumNum--;
                if (mSumNum < 0) {
                    view.setText(0 + "");
                    view.setEnabled(true);
                    Message message = new Message();
                    message.what = 60000;
                    mHandler.sendMessage(message);
                    // 干掉这个定时器，下次减不会累加
                    Handler.removeCallbacks(mRunnable);
                    AuthCode(mTvTimeSecond, second);
                }
            }

        };
        Handler.postDelayed(mRunnable, 1000);
    }

    private void initData() {
        // 生成二维码
        RxQRCode.createQRCode("时间戳:" + System.currentTimeMillis(), 800, 800, mIvCode);
        // 生成条形码
        mIvLinecode.setImageBitmap(RxBarCode.createBarCode("" + System.currentTimeMillis(), 1000, 300));
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.ll_refresh:
                Handler.removeCallbacks(mRunnable);
                initData();
                mTvTimeSecond.setText(second + "");
                AuthCode(mTvTimeSecond, second);
                break;
            default:
                break;
        }
    }
}
