package com.example.sic.Activity.Login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.sic.Activity.Home.HomePage;
import com.example.sic.Dev_activity;
import com.example.sic.R;

import vn.mobileid.tse.model.client.HttpRequest;
import vn.mobileid.tse.model.client.activate.ActivateModule;
import vn.mobileid.tse.model.connector.plugin.Response;

public class RC6DigitNumber extends Dev_activity {
    ImageView logo, img_corner;
    FrameLayout btnBack;
    EditText pinValue;

    AppCompatButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, Key_delete;

    String text, firebaseID;


    LinearLayout keyboard, pin;
    TextView title, txtTitle;
    FrameLayout digit_number;
    View.OnClickListener numKey = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (pinValue.length() <= 6) {
                text = pinValue.getText().toString();
                TextView button = (TextView) v;
                if (text.length() == 3) {
                    text += "-";
                }
                text = text + button.getText().toString();
                pinValue.setText(text);
            }
            Log.d("pinvalue", "onClick: " + pinValue.length());
            Log.d("text", "onClick: " + pinValue.getText().toString());

        }
    };


    EditText[] otpEdt = new EditText[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc6_digit_number);
        anh_xa();

        SharedPreferences pref = getSharedPreferences("FirebaseID", MODE_PRIVATE);
        firebaseID = pref.getString("firebaseID", null);
        bt1.setOnClickListener(numKey);
        bt2.setOnClickListener(numKey);
        bt3.setOnClickListener(numKey);
        bt4.setOnClickListener(numKey);
        bt5.setOnClickListener(numKey);
        bt6.setOnClickListener(numKey);
        bt7.setOnClickListener(numKey);
        bt8.setOnClickListener(numKey);
        bt9.setOnClickListener(numKey);
        bt0.setOnClickListener(numKey);


        Key_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = pinValue.getText().toString();
                if (text.length() <= 8) {
                    if (text.length() != 0) {
                        text = text.substring(0, text.length() - 1);
                    }
                    pinValue.setText(text);
                }
            }
        });

        digit_number.setOnClickListener(view -> {
            Intent intent = new Intent(this, RC12DigitNumber.class);
            startActivity(intent);
            finish();
        });
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ConfirmPin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });


        pinValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                switch (pinValue.length()) {
                    case 0:
                        otpEdt[0].setText("");
                        otpEdt[1].setText("");
                        otpEdt[2].setText("");
                        otpEdt[3].setText("");
                        otpEdt[4].setText("");
                        otpEdt[5].setText("");
                    case 1:
                        text = text.substring(0);
                        otpEdt[0].setText(text);
                        otpEdt[1].setText("");
                        otpEdt[2].setText("");
                        otpEdt[3].setText("");
                        otpEdt[4].setText("");
                        otpEdt[5].setText("");
                        break;
                    case 2:
                        text = text.substring(1, 2);
                        otpEdt[1].setText(text);
                        otpEdt[2].setText("");
                        otpEdt[3].setText("");
                        otpEdt[4].setText("");
                        otpEdt[5].setText("");
                        break;
                    case 3:
                        text = text.substring(2, 3);
                        otpEdt[2].setText(text);
                        otpEdt[3].setText("");
                        otpEdt[4].setText("");
                        otpEdt[5].setText("");
                        break;
                    case 4:
                        text = text.substring(3, 4);
                        otpEdt[3].setText("");
                        otpEdt[4].setText("");
                        otpEdt[5].setText("");
                        break;
                    case 5:
                        text = text.substring(4, 5);
                        otpEdt[3].setText(text);
                        otpEdt[4].setText("");
                        otpEdt[5].setText("");
                        break;
                    case 6:
                        text = text.substring(5, 6);
                        otpEdt[4].setText(text);
                        otpEdt[5].setText("");
                        break;
                    case 7:
                        text = text.substring(6, 7);
                        otpEdt[5].setText(text);

                        try {
                            ActivateModule.createModule(RC6DigitNumber.this).setResponseSetRecoveryCode(new HttpRequest.AsyncResponse() {
                                @Override
                                public void process(boolean b, Response response) {
                                    if (response.getError() == 0) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Dialog();
                                            }
                                        });
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.d("Error", "run: "+response.getErrorDescription());
                                                DialogFail();
                                            }
                                        });
                                    }
                                }
                            }).setRecoveryCode(pinValue.getText().toString(), firebaseID, true);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                }
            }
        });
    }

    private void Dialog() {
        Dialog dialog = new Dialog(RC6DigitNumber.this);
        dialog.setContentView(R.layout.dialog_success_active);
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Intent intent = new Intent(RC6DigitNumber.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void DialogFail() {
        Dialog dialog = new Dialog(RC6DigitNumber.this);
        dialog.setContentView(R.layout.dialog_fail_recovery);
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

        TextView btnClose = dialog.findViewById(R.id.btn_Close);
        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
            for (EditText editText : otpEdt) {
                editText.getText().clear();
            }
            text = "";
            otpEdt[0].requestFocus();
            pinValue.getText().clear();

        });
    }

    private void anh_xa() {
        otpEdt[0] = findViewById(R.id.txt_pin_view_1);
        otpEdt[1] = findViewById(R.id.txt_pin_view_2);
        otpEdt[2] = findViewById(R.id.txt_pin_view_3);
        otpEdt[3] = findViewById(R.id.txt_pin_view_4);
        otpEdt[4] = findViewById(R.id.txt_pin_view_5);
        otpEdt[5] = findViewById(R.id.txt_pin_view_6);
        keyboard = findViewById(R.id.keyboard);
        btnBack = findViewById(R.id.btnBack);
        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);
        bt3 = findViewById(R.id.btn3);
        bt4 = findViewById(R.id.btn4);
        bt5 = findViewById(R.id.btn5);
        bt6 = findViewById(R.id.btn6);
        bt7 = findViewById(R.id.btn7);
        bt8 = findViewById(R.id.btn8);
        bt9 = findViewById(R.id.btn9);
        bt0 = findViewById(R.id.btn0);
        Key_delete = findViewById(R.id.Key_delete);

        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        pin = findViewById(R.id.pin);
        txtTitle = findViewById(R.id.txtTitle);

        img_corner = findViewById(R.id.img_corner);
        pinValue = findViewById(R.id.pin6_dialog);
        digit_number = findViewById(R.id.digit_number);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}