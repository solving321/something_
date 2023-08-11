package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    private DevicePolicyManager devicePolicyManager;
    private ComponentName deviceAdminComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        deviceAdminComponent = new ComponentName(this, YourDeviceAdminReceiver.class);

        Button helloButton = findViewById(R.id.helloButton);
        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hello Alert")
                        .setMessage("Hello World")
                        .setPositiveButton("OK", null)
                        .show();

                toggleUsbDataSignaling();
            }
        });
    }

    private void toggleUsbDataSignaling() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Hello Alert")
                .setMessage("Hello World again")
                .setPositiveButton("OK", null)
                .show();

        boolean isEnabled = devicePolicyManager.isUsbDataSignalingEnabled(); // API >= 31
        // Android 11 supports up to API 30
        devicePolicyManager.setUsbDataSignalingEnabled(!isEnabled);
    }
}