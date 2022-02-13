package com.example.parking;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private View View;
    public LinearLayout account;
    public TextView txv_account,txv_notice;
    public RadioGroup radioGroup;
    public RadioButton rb1,rb2;
    public SharedPreferences sp;
    public String hold,member;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_setting, container, false);
        // UI元件的取得及設定
        account=View.findViewById(R.id.lin_account);
        txv_account=View.findViewById(R.id.txv_account);
        txv_notice=View.findViewById(R.id.txv_notice);
        sp = this.getActivity().getSharedPreferences("member",MODE_PRIVATE);
        hold = sp.getString("hold","");
        member = sp.getString("account","");
        if (hold.equals("1")){
            txv_account.setText(member);
            account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    sp.edit().clear().commit();
                    Toast.makeText(getActivity(), "已登出",
                            Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
            });
            txv_notice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_setting_notice);//指定自定義layout

                    LinearLayout ll = dialog.findViewById(R.id.lldialog);
                    ll.getLayoutParams().width=720;

                    radioGroup = (RadioGroup)ll.findViewById(R.id.rg_1);
                    rb1 = (RadioButton) ll.findViewById(R.id.rb_1);
                    rb2 = (RadioButton) ll.findViewById(R.id.rb_2);

                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(checkedId==rb1.getId()){
                                txv_notice.setText("通知開");
                                dialog.dismiss();
                            }
                            else if(checkedId==rb2.getId()){
                                txv_notice.setText("通知關");
                                dialog.dismiss();
                            }
                        }
                    });

                    dialog.show();
                }
            });
            return View;
        }else{
            account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    Intent intent= new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            });
            txv_notice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_setting_notice);//指定自定義layout

                    LinearLayout ll = dialog.findViewById(R.id.lldialog);
                    ll.getLayoutParams().width=720;

                    radioGroup = (RadioGroup)ll.findViewById(R.id.rg_1);
                    rb1 = (RadioButton) ll.findViewById(R.id.rb_1);
                    rb2 = (RadioButton) ll.findViewById(R.id.rb_2);

                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(checkedId==rb1.getId()){
                                txv_notice.setText("通知開");
                                dialog.dismiss();
                            }
                            else if(checkedId==rb2.getId()){
                                txv_notice.setText("通知關");
                                dialog.dismiss();
                            }
                        }
                    });

                    dialog.show();
                }
            });
            return View;
        }
    }

}
