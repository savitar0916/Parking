package com.example.parking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public String[] account={"1110334002","1110334009","1110334033","1110334049"};
    public String[] password={"mimi","jj","old","ns"};
    public String[] carnum={"mi4002","jj4009","864033","ns4049"};
    public EditText edt_account,edt_password,edt_carnum;
    public Button btn_login,btn_cancel;
    public Intent intent;
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_account=findViewById(R.id.edt_account);
        edt_password=findViewById(R.id.edt_password);
        edt_carnum=findViewById(R.id.edt_carnum);
        btn_login=findViewById(R.id.btn_login);
        btn_cancel=findViewById(R.id.btn_cancel);
        sp = getSharedPreferences("member",MODE_PRIVATE);
    }
    public void login(View v){
        if(!edt_account.getText().toString().equals("") && !edt_password.getText().toString().equals("") &&!edt_carnum.getText().toString().equals("")) {
            for (int i = 0; i < account.length; i++) {
                if (account[i].equals(edt_account.getText().toString())) {
                    if (password[i].equals(edt_password.getText().toString())) {
                        if (carnum[i].equals(edt_carnum.getText().toString())) {
                            Toast.makeText(this, "登入成功",
                                    Toast.LENGTH_SHORT).show();
                            sp.edit()
                                    .putString("hold","1")
                                    .putString("account",account[i])
                                    .commit();
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        } else {
                            Toast.makeText(this, "車牌錯誤",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "密碼錯誤",
                                Toast.LENGTH_SHORT).show();
                    }
                }else if(i==(account.length-1)){
                    Toast.makeText(this, "帳號錯誤",
                        Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            Toast.makeText(this, "請輸入完全",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void cancel(View v){
        sp.edit().clear().commit();
        LoginActivity.this.finish();
    }
}

