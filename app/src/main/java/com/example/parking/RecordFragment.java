package com.example.parking;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {

    private View View;
    public TextView edt_apartment;
    public TextView edt_floor;
    public EditText edt_car;
    public RadioGroup radioGroup;
    public RadioButton rb1,rb2,rb3;
    public Button btn_clear,btn_check;
    public SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_record, container, false);
        // UI元件的取得及設定
        edt_apartment=View.findViewById(R.id.edt_apartment);
        edt_floor=View.findViewById(R.id.edt_floor);
        edt_car=View.findViewById(R.id.edt_car);
        btn_clear=View.findViewById(R.id.clear);
        btn_check=View.findViewById(R.id.check);
        sp = this.getActivity().getSharedPreferences("record",Context.MODE_PRIVATE);
        edt_apartment.setText(sp.getString("apartment",""));
        edt_floor.setText(sp.getString("floor",""));
        edt_car.setText(sp.getString("car",""));
        edt_car.setRawInputType(Configuration.KEYBOARD_QWERTY);
        edt_apartment.setOnClickListener(apartmentls);
        edt_floor.setOnClickListener(floorls);
        btn_clear.setOnClickListener(clear);
        btn_check.setOnClickListener(check);
        return View;
    }
    android.view.View.OnClickListener apartmentls = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_record_apartment);//指定自定義layout

            LinearLayout ll = dialog.findViewById(R.id.lldialog);
            ll.getLayoutParams().width=720;

            radioGroup = (RadioGroup)ll.findViewById(R.id.rg_1);
            rb1 = (RadioButton) ll.findViewById(R.id.rb_1);
            rb2 = (RadioButton) ll.findViewById(R.id.rb_2);
            rb3 = (RadioButton) ll.findViewById(R.id.rb_3);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId==rb1.getId()){
                        edt_apartment.setText("中正停車場");
                        dialog.dismiss();
                    }
                    else if(checkedId==rb2.getId()){
                        edt_apartment.setText("中商停車場");
                        dialog.dismiss();
                    }
                    else if(checkedId==rb3.getId()){
                        edt_apartment.setText("中技停車場");
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
        }
    };
    android.view.View.OnClickListener floorls = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_record_floor1);//指定自定義layout

            LinearLayout ll = dialog.findViewById(R.id.lldialog);
            ll.getLayoutParams().width=720;

            radioGroup = (RadioGroup)ll.findViewById(R.id.rg_1);
            rb1 = (RadioButton) ll.findViewById(R.id.rb_1);
            rb2 = (RadioButton) ll.findViewById(R.id.rb_2);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId==rb1.getId()){
                        edt_floor.setText("B1");
                        dialog.dismiss();
                    }
                    else if(checkedId==rb2.getId()){
                        edt_floor.setText("B2");
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
        }
    };
    android.view.View.OnClickListener clear = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            edt_apartment.setText("");
            edt_floor.setText("");
            edt_car.setText("");
            sp.edit().clear().commit();
        }
    };
    android.view.View.OnClickListener check = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            sp.edit().putString("apartment",edt_apartment.getText().toString()).commit();
            sp.edit().putString("floor",edt_floor.getText().toString()).commit();
            sp.edit().putString("car",edt_car.getText().toString()).commit();
        }
    };
}
