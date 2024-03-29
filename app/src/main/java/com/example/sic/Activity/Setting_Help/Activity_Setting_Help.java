package com.example.sic.Activity.Setting_Help;

import static com.example.sic.Encrypt.decrypt;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.sic.Activity.Home.HomePage;
import com.example.sic.Activity.Setting_Help.Setting_Detail.Activity_Setting_Detail;
import com.example.sic.DefaultActivity;
import com.example.sic.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.concurrent.Executor;

import vn.mobileid.tse.model.database.SettingData;

public class Activity_Setting_Help extends DefaultActivity implements View.OnClickListener {

    LinearLayout btn_settings, btn_help, btn_terms, btn_privacy, btn_languages, btn_send_support;

    TextView tv_English, tv_Vietnamese;

    ImageView btn_Close;

    FrameLayout btnBack;
    boolean checked1, checked2;
    AppCompatCheckBox checkBox1, checkBox2;
    TextView Continue, btnClose;
    AppCompatButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, Key_delete;
    EditText txt_pin_view1, txt_pin_view2, txt_pin_view3, txt_pin_view4, txt_pin_view5, txt_pin_view6, pinValue;
    String text;
    Dialog dialog;
    SharedPreferences sharedPreferences;
    int check;

    SharedPreferences.Editor editor;
    private final TextWatcher textWatcher = new TextWatcher() {
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
                    txt_pin_view1.setText("");
                    txt_pin_view2.setText("");
                    txt_pin_view3.setText("");
                    txt_pin_view4.setText("");
                    txt_pin_view5.setText("");
                    txt_pin_view6.setText("");
                case 1:
                    text = text.substring(0);
                    txt_pin_view1.setText(text);
                    txt_pin_view2.setText("");
                    txt_pin_view3.setText("");
                    txt_pin_view4.setText("");
                    txt_pin_view5.setText("");
                    txt_pin_view6.setText("");
                    break;
                case 2:
                    text = text.substring(1, 2);
                    txt_pin_view2.setText(text);
                    txt_pin_view3.setText("");
                    txt_pin_view4.setText("");
                    txt_pin_view5.setText("");
                    txt_pin_view6.setText("");
                    break;
                case 3:
                    text = text.substring(2, 3);
                    txt_pin_view3.setText(text);
                    txt_pin_view4.setText("");
                    txt_pin_view5.setText("");
                    txt_pin_view6.setText("");
                    break;
                case 4:
                    text = text.substring(3, 4);
                    txt_pin_view4.setText(text);
                    txt_pin_view5.setText("");
                    txt_pin_view6.setText("");
                    break;
                case 5:
                    text = text.substring(4, 5);
                    txt_pin_view5.setText(text);
                    txt_pin_view6.setText("");
                    break;
                case 6:
                    text = text.substring(5, 6);
                    txt_pin_view6.setText(text);
                    if (pinValue.getText().toString().equals(digit)) {
                        Dialog dialog1 = new Dialog(Activity_Setting_Help.this);
                        dialog1.setContentView(R.layout.dialog_success);
                        editor = getSharedPreferences("AppSecurity", MODE_PRIVATE).edit();
                        editor.putInt("check", 2);
                        editor.apply();
                        dialog.dismiss();
                        dialog1.getWindow().setBackgroundDrawableResource(R.color.transparent);
                        dialog1.show();
                        Intent i = new Intent(Activity_Setting_Help.this, Activity_Setting_Detail.class);
                        startActivity(i);
                    } else {
                        Dialog dialog = new Dialog(Activity_Setting_Help.this);
                        dialog.setContentView(R.layout.dialog_fail_wrong_pin_code);
                        btnClose = dialog.findViewById(R.id.btn_Close);
                        btnClose.setOnClickListener(view -> {
                            txt_pin_view1.setText("");
                            txt_pin_view2.setText("");
                            txt_pin_view3.setText("");
                            txt_pin_view4.setText("");
                            txt_pin_view5.setText("");
                            txt_pin_view6.setText("");
                            text = text.substring(0, text.length() - 1);
                            pinValue.setText(text);
                            Log.d("text", "afterTextChanged: " + text);
                            dialog.dismiss();

                        });
                        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);
                    }
            }
        }
    };
    View.OnClickListener numKey = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text = pinValue.getText().toString();
            TextView button = (TextView) v;
            text = text + button.getText().toString();
            pinValue.setText(text);
            Log.d("123", "value:" + pinValue.getText().toString());
            Log.d("lenght", "lenght: " + pinValue.length());
        }
    };
    private String digit_6, digit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_help);
        btn_settings = findViewById(R.id.btn_settings);
        btn_help = findViewById(R.id.btn_help);
        btn_terms = findViewById(R.id.btn_terms);
        btn_privacy = findViewById(R.id.btn_privacy);
        btn_languages = findViewById(R.id.btn_language);
        btn_send_support = findViewById(R.id.btn_send_support);
        btnBack = findViewById(R.id.btnBack);

        btn_settings.setOnClickListener(this);
        btn_help.setOnClickListener(this);
        btn_terms.setOnClickListener(this);
        btn_privacy.setOnClickListener(this);
        btn_languages.setOnClickListener(this);
        btn_send_support.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        SharedPreferences my_6_digit = getSharedPreferences("MY_6_DIGIT", MODE_PRIVATE);
        digit_6 = my_6_digit.getString("my_6_digit", null);

        try {
            digit = decrypt(getApplicationContext(), digit_6);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sharedPreferences = getSharedPreferences("AppSecurity", MODE_PRIVATE);
        check = sharedPreferences.getInt("check", 0);
    }

    private void Biometric() {
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                editor = getSharedPreferences("AppSecurity", MODE_PRIVATE).edit();
                editor.putInt("check", 2);
                editor.apply();

                Intent i = new Intent(Activity_Setting_Help.this, Activity_Setting_Detail.class);
                startActivity(i);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_settings:
                if (check == 0) {
                    intent = new Intent(Activity_Setting_Help.this, Activity_Setting_Detail.class);
                    startActivity(intent);
                }
                if (check == 1) {
                    Dialog_pin();
                } else if (check == 2) {
                    Biometric();
                    biometricPrompt.authenticate(promptInfo);
                }

                break;
            case R.id.btn_help:
                intent = new Intent(Activity_Setting_Help.this, Activity_Help_Detail.class);
                startActivity(intent);
                break;
            case R.id.btn_terms:
                intent = new Intent(Activity_Setting_Help.this, Activity_Term_Detail.class);
                startActivity(intent);
                break;
            case R.id.btn_privacy:
                intent = new Intent(Activity_Setting_Help.this, Activity_Privacy_Detail.class);
                startActivity(intent);
                break;
            case R.id.btn_language:

                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getBaseContext()).inflate(R.layout.bottom_layout_languages,
                        findViewById(R.id.bottom_layout_languages));

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

                tv_English = bottomSheetView.findViewById(R.id.tv_English);
                tv_Vietnamese = bottomSheetView.findViewById(R.id.tv_Vietnamese);
                btn_Close = bottomSheetView.findViewById(R.id.close);

                checkBox1 = bottomSheetView.findViewById(R.id.checkBox1);
                checkBox2 = bottomSheetView.findViewById(R.id.checkBox2);
                checked1 = PreferenceManager.getDefaultSharedPreferences(Activity_Setting_Help.this)
                        .getBoolean("check_setting_help_1", false);
                checked2 = PreferenceManager.getDefaultSharedPreferences(Activity_Setting_Help.this)
                        .getBoolean("check_setting_help_2", false);
                checkBox1.setChecked(checked1);
                checkBox2.setChecked(checked2);

                tv_English.setOnClickListener(view1 -> {
                    SettingData.updateLanguage(getBaseContext(), "en");
                    Dialog dialog = new Dialog(Activity_Setting_Help.this);
                    dialog.setContentView(R.layout.dialog_notification_change_languages);
                    dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                    Continue = dialog.findViewById(R.id.Continue);
                    Continue.setOnClickListener(view2 -> {
                        Intent i = new Intent(Activity_Setting_Help.this, HomePage.class);
                        startActivity(i);
                    });
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(false);

                    PreferenceManager.getDefaultSharedPreferences(Activity_Setting_Help.this).edit()
                            .putBoolean("check_setting_help_1", true).apply();
                    PreferenceManager.getDefaultSharedPreferences(Activity_Setting_Help.this).edit()
                            .putBoolean("check_setting_help_2", false).apply();
                    bottomSheetDialog.dismiss();
                });

                tv_Vietnamese.setOnClickListener(view1 -> {
                    SettingData.updateLanguage(getBaseContext(), "vi");
                    Dialog dialog = new Dialog(Activity_Setting_Help.this);
                    dialog.setContentView(R.layout.dialog_notification_change_languages);
                    dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                    Continue = dialog.findViewById(R.id.Continue);
                    Continue.setOnClickListener(view2 -> {
                        Intent i = new Intent(Activity_Setting_Help.this, HomePage.class);
                        startActivity(i);
                    });
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(true);
                    PreferenceManager.getDefaultSharedPreferences(Activity_Setting_Help.this).edit()
                            .putBoolean("check_setting_help_1", false).apply();
                    PreferenceManager.getDefaultSharedPreferences(Activity_Setting_Help.this).edit()
                            .putBoolean("check_setting_help_2", true).apply();
                    bottomSheetDialog.dismiss();
                });

                btn_Close.setOnClickListener(view1 -> {
                    bottomSheetDialog.dismiss();
                });


                break;
            case R.id.btn_send_support:
                intent = new Intent(Activity_Setting_Help.this, Activity_Send_Support_Detail.class);
                startActivity(intent);
                break;
            case R.id.btnBack:
                intent = new Intent(Activity_Setting_Help.this, HomePage.class);
                startActivity(intent);
        }
    }

    private void Dialog_pin() {
        dialog = new Dialog(Activity_Setting_Help.this);
        dialog.setContentView(R.layout.layout_enter_pin_code_not_your_trans);
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

        txt_pin_view1 = dialog.findViewById(R.id.txt_pin_view_1);
        txt_pin_view2 = dialog.findViewById(R.id.txt_pin_view_2);
        txt_pin_view3 = dialog.findViewById(R.id.txt_pin_view_3);
        txt_pin_view4 = dialog.findViewById(R.id.txt_pin_view_4);
        txt_pin_view5 = dialog.findViewById(R.id.txt_pin_view_5);
        txt_pin_view6 = dialog.findViewById(R.id.txt_pin_view_6);

        bt1 = dialog.findViewById(R.id.btn1);
        bt2 = dialog.findViewById(R.id.btn2);
        bt3 = dialog.findViewById(R.id.btn3);
        bt4 = dialog.findViewById(R.id.btn4);
        bt5 = dialog.findViewById(R.id.btn5);
        bt6 = dialog.findViewById(R.id.btn6);
        bt7 = dialog.findViewById(R.id.btn7);
        bt8 = dialog.findViewById(R.id.btn8);
        bt9 = dialog.findViewById(R.id.btn9);
        bt0 = dialog.findViewById(R.id.btn0);
        Key_delete = dialog.findViewById(R.id.Key_delete);
        pinValue = dialog.findViewById(R.id.pin6_dialog);
        pinValue.addTextChangedListener(textWatcher);

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
                if (text.length() <= 6) {
                    if (text.length() != 0) {
                        text = text.substring(0, text.length() - 1);
                    }
                    pinValue.setText(text);
                    Log.d("abc", "value " + pinValue.getText().toString());
                }
            }
        });
    }
}