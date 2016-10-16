package com.barayuda.simple_intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnActivitySub1, btnActivitySub2, btnActivityDial;
    private String strIntent;
    private EditText txtIntent;
    private final int CALL_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Simple Intent & Extra Data");

        btnActivitySub1 = (Button)findViewById(R.id.btnActivitySub1);
        btnActivitySub2 = (Button)findViewById(R.id.btnActivitySub2);
        btnActivityDial = (Button)findViewById(R.id.btnActivityDial);

        txtIntent = (EditText)findViewById(R.id.text_Intent);

        btnActivitySub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSub1 = new Intent(MainActivity.this, Sub1Activity.class);
                startActivity(intentSub1);
            }
        });

        btnActivitySub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strIntent = txtIntent.getText().toString();
                Intent intentSub2 = new Intent(MainActivity.this, Sub2Activity.class);
                intentSub2.putExtra(Sub2Activity.KEY_DATA, strIntent);
                startActivityForResult(intentSub2, 0);
            }
        });

        btnActivityDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isCallPermissionAllowed()) {
                    Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 081805434715"));
                    startActivity(intentDial);
                } else {
                    requestCallPermission();
                }
            }
        });
    }

    private boolean isCallPermissionAllowed() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                Manifest.permission.CALL_PHONE)) {
            Toast.makeText(MainActivity.this, "Akses Telepon Diperlukan Untuk Aksi Tombol Ini!", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == CALL_PERMISSION_CODE) {
            // permission granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Terima kasih sudah memberikan akses telepon", Toast.LENGTH_LONG).show();
                btnActivityDial.callOnClick();
            } else {
                Toast.makeText(MainActivity.this, "Akses Telepon Diperlukan Untuk Aksi Tombol Ini!", Toast.LENGTH_LONG).show();
                btnActivityDial.callOnClick();
            }
        }
    }

}
