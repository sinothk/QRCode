package com.sinothk.QRCode.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.sinothk.QRCode.ScanerCode.ActivityScanerCode;
import com.sinothk.QRCode.demo.create.QrCodeCreateDemoMainActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_QR_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.createCode_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityCreateQRCode.class);
                startActivity(i);
            }
        });

        findViewById(R.id.createCode_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QrCodeCreateDemoMainActivity.class);
                startActivity(i);
            }
        });

        final Button qrCodeBtn = (Button) findViewById(R.id.qrcode_btn);
        qrCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityScanerCode.class);
                MainActivity.this.startActivityForResult(i, REQUEST_QR_CODE);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK
//                && requestCode == REQUEST_QR_CODE
//                && data != null) {
//            String result = data.getStringExtra("result");
//            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//        }
//    }

}
