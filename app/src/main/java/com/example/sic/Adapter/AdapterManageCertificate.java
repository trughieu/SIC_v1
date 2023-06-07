package com.example.sic.Adapter;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sic.Activity.Setting_Help.Setting_Detail.ManageCertificate.Certificates.CertificateDetail;
import com.example.sic.Activity.Setting_Help.Setting_Detail.ManageCertificate.ChangeEmail.ChangeEmailStep1;
import com.example.sic.Activity.Setting_Help.Setting_Detail.ManageCertificate.ChangeScal;
import com.example.sic.Activity.Setting_Help.Setting_Detail.ManageCertificate.GoHistory;
import com.example.sic.Activity.Setting_Help.Setting_Detail.ManageCertificate.PassPhrase.ChangePassphrase;
import com.example.sic.Activity.Setting_Help.Setting_Detail.ManageCertificate.PassPhrase.ForgetPassphrase;
import com.example.sic.Activity.Setting_Help.Setting_Detail.ManageCertificate.Renew.RenewStep1;
import com.example.sic.R;
import com.example.sic.model.ManageCertificate;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class AdapterManageCertificate extends RecyclerView.Adapter<AdapterManageCertificate.viewHolder> {

    Activity activity;
    TextView change_passphrase, forget_passphrase, change_email, change_scal, renew, history;
    Intent intent;
    int row_index;

    AppCompatCheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;
    boolean checked1, checked2, checked3, checked4, checked5, checked6;

    ArrayList<ManageCertificate> manageCertificateArrayList;

    public AdapterManageCertificate(Activity activity, ArrayList<ManageCertificate> manageCertificateArrayList) {
        this.activity = activity;
        this.manageCertificateArrayList = manageCertificateArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.layout_item_manage_certificate, parent, false);
        return new viewHolder(view);
    }


    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ManageCertificate manage_certificate = manageCertificateArrayList.get(position);
        holder.tv_certificate.setText(manage_certificate.getCNSubjectDN());
        holder.tv_issue_by.setText(activity.getResources().getString(R.string.orders_prefix_issued_by) + " " + manage_certificate.getCNIssuerDN());

        holder.tv_date.setText(manage_certificate.getValidTo());

//
//        if (!manage_certificate.isKakChange()) {
//            holder.option.setEnabled(false);
//            holder.ic_option.setImageResource(R.drawable.ic_sync);
//            holder.select_item.setOnClickListener(view -> {
//                intent = new Intent(activity, CertificateDetail.class);
//                intent.putExtra("kakChanged", false);
//                intent.putExtra("id", manage_certificate.getCredentialID());
//                
//                activity.startActivity(intent);
//            });
//        } else if (manage_certificate.isKakChange()) {
//            holder.select_item.setOnClickListener(view -> {
//                intent = new Intent(activity, CertificateDetail.class);
//                intent.putExtra("id", manage_certificate.getCredentialID());
//                
//                activity.startActivity(intent);
//            });
//        }


//
//        manageCertificateModule = ManageCertificateModule.createModule(activity);
//        manageCertificateModule.setResponseCredentialsList(new HttpRequest.AsyncResponse() {
//            @Override
//            public void process(boolean b, Response response) {
//                CredentListResponse credentListResponse = response.getCredentListResponse();
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        boolean kak = credentListResponse.certs.get(position).kakChanged;
//                        if (!kak) {
//                            holder.ic_option.setImageResource(R.drawable.ic_sync);
//                            holder.option.setEnabled(false);
//                            holder.select_item.setOnClickListener(view -> {
//                                intent = new Intent(activity, CertificateDetail.class);
//                                intent.putExtra("kakChanged", false);
//                                intent.putExtra("id", manage_certificate.getCredentialID());
//                                
//                                activity.startActivity(intent);
//                            });
//                        } else {
//                            holder.select_item.setOnClickListener(view -> {
//                                intent = new Intent(activity, CertificateDetail.class);
//                                intent.putExtra("id", manage_certificate.getCredentialID());
//                                
//                                activity.startActivity(intent);
//                            });
//                        }
//                    }
//                });
//            }
//        }).credentialsList();

        if (position == 0) {
            if (manage_certificate.isKakChange()) {
                if (manage_certificate.isKakChange()) {
                    holder.date.setImageResource(R.drawable.date_img_blue);
                    holder.background.setBackgroundResource(R.drawable.layout_item_history);
                    holder.tv_certificate.setTextColor(Color.parseColor("#0070F4"));
                    holder.tv_date.setTextColor(Color.parseColor("#0070F4"));
                    holder.tv_issue_by.setTextColor(Color.parseColor("#0070F4"));
                    holder.ic_option.setImageResource(R.drawable.union_blue);
                    Log.d("TAG", "onBindViewHolder: "+position);
                    holder.select_item.setOnClickListener(view -> {
                        intent = new Intent(activity, CertificateDetail.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id", manage_certificate.getCredentialID());
                        activity.startActivity(intent);
                    });
                } else if (!manage_certificate.getStatus().equalsIgnoreCase("valid")) {
                    holder.option.setVisibility(View.GONE);
                    holder.date.setImageResource(R.drawable.date_img_blue);
                    holder.background.setBackgroundResource(R.drawable.layout_item_history);
                    holder.tv_certificate.setTextColor(Color.parseColor("#0070F4"));
                    holder.tv_date.setTextColor(Color.parseColor("#0070F4"));
                    holder.tv_issue_by.setTextColor(Color.parseColor("#0070F4"));
                    Log.d("TAG", "onBindViewHolder: "+position);
                    holder.select_item.setOnClickListener(view -> {
                        intent = new Intent(activity, CertificateDetail.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id", manage_certificate.getCredentialID());
                        activity.startActivity(intent);
                    });
                }
            } else {
                holder.option.setEnabled(false);
                holder.ic_option.setImageResource(R.drawable.ic_sync);
                holder.date.setImageResource(R.drawable.date_img_blue);
                holder.background.setBackgroundResource(R.drawable.layout_item_history);
                holder.tv_certificate.setTextColor(Color.parseColor("#0070F4"));
                holder.tv_date.setTextColor(Color.parseColor("#0070F4"));
                holder.tv_issue_by.setTextColor(Color.parseColor("#0070F4"));
                Log.d("TAG", "onBindViewHolder: "+position);
                holder.select_item.setOnClickListener(view -> {
                    intent = new Intent(activity, CertificateDetail.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("kakChanged", false);
                    intent.putExtra("id", manage_certificate.getCredentialID());
                    activity.startActivity(intent);
                });
            }
        } else {
            if (manage_certificate.isKakChange()) {
                if (manage_certificate.isKakChange() && (!manage_certificate.getStatus().equalsIgnoreCase("valid"))) {
                    holder.option.setVisibility(View.GONE);
                    holder.date.setImageResource(R.drawable.date_img_white);
                    holder.background.setBackgroundResource(R.drawable.layout_item_history_black);
                    holder.tv_certificate.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_date.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_issue_by.setTextColor(Color.parseColor("#FFFFFF"));
                    Log.d("TAG", "onBindViewHolder: "+position);
                    holder.select_item.setOnClickListener(view -> {
                        intent = new Intent(activity, CertificateDetail.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id", manage_certificate.getCredentialID());
                        activity.startActivity(intent);
                    });
                } else if (manage_certificate.isKakChange() && manage_certificate.getStatus().equalsIgnoreCase("valid")) {
                    holder.date.setImageResource(R.drawable.date_img_white);
                    holder.background.setBackgroundResource(R.drawable.layout_item_history_black);
                    holder.tv_certificate.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_date.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_issue_by.setTextColor(Color.parseColor("#FFFFFF"));
                    Log.d("TAG", "onBindViewHolder: "+position);
                    holder.ic_option.setImageResource(R.drawable.union);
                    holder.select_item.setOnClickListener(view -> {
                        intent = new Intent(activity, CertificateDetail.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id", manage_certificate.getCredentialID());
                        activity.startActivity(intent);
                    });
                } else if (manage_certificate.isKakChange()) {
                    holder.date.setImageResource(R.drawable.date_img_white);
                    holder.background.setBackgroundResource(R.drawable.layout_item_history_black);
                    holder.tv_certificate.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_date.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_issue_by.setTextColor(Color.parseColor("#FFFFFF"));
                    Log.d("TAG", "onBindViewHolder: "+position);
                    holder.ic_option.setImageResource(R.drawable.union);
                    holder.select_item.setOnClickListener(view -> {
                        intent = new Intent(activity, CertificateDetail.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id", manage_certificate.getCredentialID());
                        activity.startActivity(intent);
                    });
                } else {
                    holder.date.setImageResource(R.drawable.date_img_white);
                    holder.background.setBackgroundResource(R.drawable.layout_item_history_black);
                    holder.tv_certificate.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_date.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.tv_issue_by.setTextColor(Color.parseColor("#FFFFFF"));
                    Log.d("TAG", "onBindViewHolder: "+position);
                    holder.ic_option.setImageResource(R.drawable.union);
                    holder.select_item.setOnClickListener(view -> {
                        intent = new Intent(activity, CertificateDetail.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id", manage_certificate.getCredentialID());
                        activity.startActivity(intent);

                    });
                }
            } else if (!manage_certificate.isKakChange()){
                holder.option.setEnabled(false);
                holder.ic_option.setImageResource(R.drawable.ic_sync);
                holder.date.setImageResource(R.drawable.date_img_white);
                holder.background.setBackgroundResource(R.drawable.layout_item_history_black);
                holder.tv_certificate.setTextColor(Color.parseColor("#FFFFFF"));
                holder.tv_date.setTextColor(Color.parseColor("#FFFFFF"));
                holder.tv_issue_by.setTextColor(Color.parseColor("#FFFFFF"));
                Log.d("TAG", "onBindViewHolder: "+position);
                holder.select_item.setOnClickListener(view -> {
                    intent = new Intent(activity, CertificateDetail.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("kakChanged", false);
                    intent.putExtra("id", manage_certificate.getCredentialID());
                    activity.startActivity(intent);
                });
            }
        }

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext(),
                        R.style.BottomSheetDialogTheme);

                View view1 = LayoutInflater.from(view.getContext()).
                        inflate(R.layout.bottom_sheet_layout_manage_certificate_state,
                                view.findViewById(R.id.bottom_sheet_manage_certificate));
                change_passphrase = view1.findViewById(R.id.change_passphrase);
                forget_passphrase = view1.findViewById(R.id.forget_passphrase);
                change_email = view1.findViewById(R.id.change_email);
                renew = view1.findViewById(R.id.renew);
                change_scal = view1.findViewById(R.id.change_scal);
                history = view1.findViewById(R.id.history);
                checkBox1 = view1.findViewById(R.id.checkBox1);
                checkBox2 = view1.findViewById(R.id.checkBox2);
                checkBox3 = view1.findViewById(R.id.checkBox3);
                checkBox4 = view1.findViewById(R.id.checkBox4);
                checkBox5 = view1.findViewById(R.id.checkBox5);
                checkBox6 = view1.findViewById(R.id.checkBox6);

                checked1 = PreferenceManager.getDefaultSharedPreferences(activity)
                        .getBoolean("check_manage_certificate_option_1", false);
                checked2 = PreferenceManager.getDefaultSharedPreferences(activity)
                        .getBoolean("check_manage_certificate_option_2", false);
                checked3 = PreferenceManager.getDefaultSharedPreferences(activity)
                        .getBoolean("check_manage_certificate_option_3", false);
                checked4 = PreferenceManager.getDefaultSharedPreferences(activity)
                        .getBoolean("check_manage_certificate_option_4", false);
                                                 checked5 = PreferenceManager.getDefaultSharedPreferences(activity)
                                                         .getBoolean("check_manage_certificate_option_5", false);
                                                 checked6 = PreferenceManager.getDefaultSharedPreferences(activity)
                                                         .getBoolean("check_manage_certificate_option_6", false);
                                                 checkBox1.setChecked(checked1);
                                                 checkBox2.setChecked(checked2);
                                                 checkBox3.setChecked(checked3);
                                                 checkBox4.setChecked(checked4);
                                                 checkBox5.setChecked(checked5);
                                                 checkBox6.setChecked(checked6);
                                                 change_passphrase.setOnClickListener(view2 -> {
                                                     intent = new Intent(activity, ChangePassphrase.class);
                                                     
                                                     intent.putExtra("id", manage_certificate.getCredentialID());
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                     intent.putExtra("certificate",manage_certificate);
                                                     activity.startActivity(intent);
                                                     check_chang_passphrase();
                                                     bottomSheetDialog.dismiss();
                                                 });
                                                 forget_passphrase.setOnClickListener(view2 -> {
                                                     intent = new Intent(activity, ForgetPassphrase.class);
                                                     
                                                     intent.putExtra("id", manage_certificate.getCredentialID());
                                                     intent.putExtra("certificate",manage_certificate);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                     
                                                     activity.startActivity(intent);
                                                     check_forget_passphrase();
                                                     bottomSheetDialog.dismiss();
                                                 });
                                                 change_email.setOnClickListener(view2 -> {
                                                     intent = new Intent(activity, ChangeEmailStep1.class);
                                                     
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                     intent.putExtra("id", manage_certificate.getCredentialID());
                                                     intent.putExtra("certificate",manage_certificate);
                                                     activity.startActivity(intent);
                                                     check_change_email();
                                                     bottomSheetDialog.dismiss();
                                                 });
                                                 renew.setOnClickListener(view2 -> {
                                                     intent = new Intent(activity, RenewStep1.class);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                     intent.putExtra("id", manage_certificate.getCredentialID());
                                                     intent.putExtra("certificate",manage_certificate);
                                                     activity.startActivity(intent);
                                                     check_renew();
                                                     bottomSheetDialog.dismiss();
                                                 });
                                                 change_scal.setOnClickListener(view2 -> {
                                                     intent = new Intent(activity, ChangeScal.class);
                                                     intent.putExtra("id", manage_certificate.getCredentialID());
                                                     
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                     intent.putExtra("certificate",manage_certificate);
                                                     activity.startActivity(intent);
                                                     check_change_Scal();
                                                     bottomSheetDialog.dismiss();
                                                 });
                                                 history.setOnClickListener(view2 -> {
                                                     intent = new Intent(activity, GoHistory.class);
                                                     
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                     intent.putExtra("id", manage_certificate.getCredentialID());
                                                     intent.putExtra("certificate",manage_certificate);
                                                     activity.startActivity(intent);
                                                     check_history();
                                                     bottomSheetDialog.dismiss();
                                                 });
                                                 bottomSheetDialog.setContentView(view1);
                                                 bottomSheetDialog.show();
                                             }
                                         }
        );
    }

    @Override
    public int getItemCount() {
        return manageCertificateArrayList.size();
    }

    private void check_change_Scal() {
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_1", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_2", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_3", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_4", true).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_5", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_6", false).apply();
    }

    private void check_history() {
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_1", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_2", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_3", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_4", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_5", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_6", true).apply();
    }

    private void check_chang_passphrase() {
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_1", true).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_2", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_3", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_4", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_5", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_6", false).apply();
    }

    private void check_change_email() {
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_1", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_2", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_3", true).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_4", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_5", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_6", false).apply();
    }

    private void check_renew() {
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_1", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_2", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_3", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_4", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_5", true).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_6", false).apply();
    }

    private void check_forget_passphrase() {
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_1", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_2", true).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_3", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_4", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_5", false).apply();
        PreferenceManager.getDefaultSharedPreferences(activity).edit()
                .putBoolean("check_manage_certificate_option_6", false).apply();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView tv_certificate, tv_issue_by, tv_date;
        ImageView ic_option, date;
        LinearLayout background, select_item;
        FrameLayout option;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tv_certificate = itemView.findViewById(R.id.tv_Certificate);
            tv_issue_by = itemView.findViewById(R.id.tv_issue_by);
            tv_date = itemView.findViewById(R.id.tv_date);
            date = itemView.findViewById(R.id.date);
            ic_option = itemView.findViewById(R.id.ic_option);
            background = itemView.findViewById(R.id.background);
            select_item = itemView.findViewById(R.id.select_item);
            option = itemView.findViewById(R.id.option);
        }
    }
}